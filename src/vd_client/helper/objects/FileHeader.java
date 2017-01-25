/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vd_client.helper.objects;

/**
 *
 * @author pouya
 */
public class FileHeader {
    
    final private int Object_ID = 1;
    private int _fileSize;
    private String _filename;
    private transient String _fullpath;
    private transient byte _fileKey[];
    
    
    public FileHeader(int _fileSize, String _filename,String _fullpath) {
        this._fileSize = _fileSize;
        this._filename = _filename;
        this._fullpath = _fullpath;
    }

    public String getFullpath() {
        return _fullpath;
    }

    public byte[] getFileKey() {
        return _fileKey;
    }

    public void setFileKey(byte[] _fileKey) {
        this._fileKey = _fileKey;
    }

    public int getFileSize() {
        return _fileSize;
    }
    
    
    

}
