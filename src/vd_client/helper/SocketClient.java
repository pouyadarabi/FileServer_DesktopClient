/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vd_client.helper;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import javax.swing.JFrame;
import vd_client.helper.objects.FileDownload;
import vd_client.helper.objects.FileHeader;

/**
 *
 * @author pouya
 */
public class SocketClient {

    private FileHeader lastFile;
    private AsynchronousSocketChannel sockChannel;
    private WriteWorker current_worker;
    private boolean _isConnected = false;
    private JFrame active_form;
    private HashMap<Integer, String> jobQueue = new HashMap<>();
    private FileDownload fld;

    public boolean isConnected() {
        return _isConnected;
    }

    public JFrame getActive_form() {
        return active_form;
    }

    public void setFileDownload(FileDownload filedownload) {
        this.fld = filedownload;
    }

    public void setActive_form(JFrame active_form) {
        this.active_form = active_form;
    }

    public SocketClient(String host, int port, JFrame active_form, final String callback) throws IOException {
        current_worker = new WriteWorker();
        this.active_form = active_form;
        sockChannel = AsynchronousSocketChannel.open();
        fld = new FileDownload();
        sockChannel.connect(new InetSocketAddress(host, port), sockChannel, new CompletionHandler<Void, AsynchronousSocketChannel>() {
            @Override
            public void completed(Void result, AsynchronousSocketChannel channel) {
                //start to read message

                _isConnected = true;

                try {
                    Method method = active_form.getClass().getMethod(callback, boolean.class);
                    method.invoke(active_form, _isConnected);
                } catch (Exception e) {

                }

                final ByteBuffer buf = ByteBuffer.allocate(512000);
                startRead(buf);
                //write an message to server side
                //  startWrite();
            }

            @Override
            public void failed(Throwable exc, AsynchronousSocketChannel channel) {
                _isConnected = false;
                try {
                    Method method = active_form.getClass().getMethod(callback, boolean.class);
                    method.invoke(active_form, _isConnected);
                } catch (Exception e) {

                }

            }

        });
    }

    private void startRead(final ByteBuffer buf) {

        sockChannel.read(buf, sockChannel, new CompletionHandler<Integer, AsynchronousSocketChannel>() {

            @Override
            public void completed(Integer result, AsynchronousSocketChannel channel) {
                //message is read from server
                //   messageRead.getAndIncrement();
                if (result != -1) {

                    System.out.println("Incomming data!");

                    buf.flip();
                    int limits = buf.limit();
                    byte bytes[] = new byte[limits];
                    buf.get(bytes, 0, limits);

                    Charset cs = Charset.forName("UTF-8");
                    String msg = new String(bytes, cs);

                    boolean flag = false;

                   
                    if (msg.startsWith("ERR|") || msg.startsWith("OK|")) {
                         System.out.println(msg);
                        flag = true;
                        String[] splited = msg.split("\\|");

                        boolean success = false;
                        if (msg.startsWith("OK|")) {
                            success = true;

                        }

                        Integer responseKey = Integer.parseInt(splited[1]);
                        String responseMessage = splited[2];
                        if (jobQueue.containsKey(responseKey)) {
                            try {
                                Method method = active_form.getClass().getMethod(jobQueue.get(responseKey), boolean.class, String.class);
                                jobQueue.remove(responseKey);
                                method.invoke(active_form, success, responseMessage);
                            } catch (Exception e) {
                                System.err.println(e.getStackTrace());
                            }
                        }

                    }
                    if (flag == false && fld.get_fileSize() > 0) {

                        fld.append_byte(bytes);
                        Integer current_size = fld.get_currentSize();
                        if (current_size == fld.get_fileSize()) {

                            String userFolder = "files/" + File.separator;

                            Path path = Paths.get(userFolder);
                            if (!Files.exists(path)) {
                                try {
                                    Files.createDirectories(path);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            path = Paths.get(userFolder + fld.get_filename());
                            if (Files.exists(path)) {
                                path = Paths.get(userFolder + System.nanoTime() + "_" + fld.get_filename());
                            }
                            try {
                                Files.write(path, fld.get_filebyte());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            fld.setCompleted(true);
                            fld.clear();
                        }

                    }
                    buf.clear();
                    startRead(buf);
                }
            }

            @Override
            public void failed(Throwable exc, AsynchronousSocketChannel channel) {
                System.out.println("fail to read message from server");
            }

        });

    }

    public void UploadFileHeader(final String fileAddr, Integer key, String callback_name) {

        jobQueue.put(key, callback_name);

        final File myFile = new File(fileAddr);
        FileHeader flh = new FileHeader((int) myFile.length(), myFile.getName(), fileAddr);
        Gson gson = new Gson();
        String objectInGson = gson.toJson(flh);

        ByteBuffer buf = ByteBuffer.allocate(8192);
        buf.put(objectInGson.getBytes());
        buf.flip();

        sockChannel.write(buf, sockChannel, new CompletionHandler<Integer, AsynchronousSocketChannel>() {
            @Override
            public void completed(Integer result, AsynchronousSocketChannel channel) {

                lastFile = flh;
                //    current_worker.doit(flh, channel);
            }

            @Override
            public void failed(Throwable exc, AsynchronousSocketChannel channel) {
                System.out.println("Fail to write the message to server");
            }
        });

    }

    public void UploadSelectedFile() {
        current_worker.doit(this.lastFile, this.sockChannel);
    }

    public void SendData(final String Data, Integer key, String callback_name) {

        if (!jobQueue.containsKey(key)) {
            jobQueue.put(key, callback_name);
        }

        ByteBuffer buf = ByteBuffer.allocate(8192);
        buf.put(Data.getBytes());
        buf.flip();

        sockChannel.write(buf, sockChannel, new CompletionHandler<Integer, AsynchronousSocketChannel>() {
            @Override
            public void completed(Integer result, AsynchronousSocketChannel channel) {
                System.out.println("Send Compeleted!");

            }

            @Override
            public void failed(Throwable exc, AsynchronousSocketChannel channel) {
                 try {
                    Method method = active_form.getClass().getMethod("disconnect_callback");
                    method.invoke(active_form);
                } catch (Exception e) {

                }
            }
        });

    }

    public void close() throws IOException {
        sockChannel.close();
    }

    public int getWriteStatus() {
        if (current_worker != null) {
            return current_worker.getWritePercent();
        } else {
            return 0;
        }
    }

    public int getDownloadStatus() {
        if (fld.get_fileSize() != 0) {
            Integer fileSize = fld.get_fileSize();
            Integer Total = fld.get_fileSize();
            Integer res = (int) (((float) fileSize / (float) Total) * 100);
            fld.setCompleted(false);
            return res;
        } else {
            if(fld.isCompleted()){
                fld.setCompleted(false);
                return 100;
            }
            return 0;
        }
    }

}
