/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vd_client.helper.objects;


/**
 * Created by pouya on 1/15/17.
 */
public class FileDownload {

    private int _fileSize = 0;
    private String _filename;
    private byte _filebyte[];
    private int _writtenbyte = 0;
    private int _lastWrite = 0;
    private boolean completed = false;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public FileDownload() {


    }
    public FileDownload(int _fileSize, String _filename) {
        this._fileSize = _fileSize;
        this._filename = _filename;
        this._filebyte = new byte[_fileSize];
    }

    public void clear(){
        this._fileSize = 0;
        this._filename = null;
        this._filebyte = null;
        this._writtenbyte = 0;
        this._lastWrite = 0;
    }
    public int get_fileSize() {
        return _fileSize;
    }

    public void set_fileSize(int _fileSize) {
        this._fileSize = _fileSize;
        this._filebyte = new byte[_fileSize];
    }

    public String get_filename() {
        return _filename;
    }

    public void set_filename(String _filename) {
        this._filename = _filename;
    }

    public void append_byte(byte[] current){
        try {
            System.arraycopy(current, 0, this._filebyte, this._writtenbyte, current.length);
        }
        catch (Exception e){
            System.out.println("Error! " +  this._filebyte.length + " , " + this._writtenbyte + " , " + current.length);
        }


        this._lastWrite = current.length;
        this._writtenbyte += current.length;
    }

    public int get_currentSize() {
        return _writtenbyte;
    }

    public byte[] get_filebyte() {
        return _filebyte;
    }

    public  void fix_lastwrite(){
        System.arraycopy(this._filebyte, 0, this._filebyte, 0 ,this._writtenbyte - this._lastWrite);
        this._writtenbyte -= this._lastWrite;
    }
}
