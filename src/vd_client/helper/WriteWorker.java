/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vd_client.helper;

import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.io.IOException;
import vd_client.helper.objects.FileHeader;

/**
 *
 * @author pouya
 */
public class WriteWorker implements CompletionHandler<Integer, AsynchronousFileChannel> {

    // need to keep track of the next position.
    final int BUFFER_SIZE = 512000;
    int pos = 0;
    //  byte retransmit_flag = 0;
    byte[] fileKey;
    AsynchronousFileChannel channel = null;
    AsynchronousSocketChannel socketchannel = null;
    ByteBuffer buffer = null;
    int total = 1;
    CompletionHandler<Integer, AsynchronousFileChannel> current;

    byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public int getWritePercent() {
        int res = (int) (((float) pos / (float) total) * 100);
        return res;
    }

    public void completed(final Integer read_result, AsynchronousFileChannel attachment) {
        // if result is -1 means nothing was read.
        if (read_result != -1) {
            buffer.flip();
            if (pos == total) {
                System.out.println("%" + ((float) pos / (float) total) * 100);
            }

            socketchannel.write(buffer, socketchannel, new CompletionHandler<Integer, AsynchronousSocketChannel>() {
                @Override
                public void completed(Integer write_result, AsynchronousSocketChannel channel) {
                    if (write_result != -1) {
                        pos += write_result;
                        buffer.clear();

                        attachment.read(buffer, pos, attachment, current);
                    }
                }

                @Override
                public void failed(Throwable exc, AsynchronousSocketChannel channel) {
                    System.out.println("Fail to write the message to server");
                }
            });

        }

        // initiate another asynchronous read, with this.
    }

    public void failed(Throwable exc,
            AsynchronousFileChannel attachment) {
        System.err.println("Error!");
        exc.printStackTrace();
    }

    public void doit(FileHeader flh, AsynchronousSocketChannel socketChannel) {

        Path file = Paths.get(flh.getFullpath());
      

        AsynchronousFileChannel channel = null;
        this.socketchannel = socketChannel;

        try {
            channel = AsynchronousFileChannel.open(file);
        } catch (IOException e) {
            System.err.println("Could not open file: " + file.toString());

        }
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
        total = flh.getFileSize();

        current = this;
        // start off the asynch read.
        channel.read(buffer, pos, channel, this);
        // this method now exits, thread returns to main and waits for user input.
    }

}
