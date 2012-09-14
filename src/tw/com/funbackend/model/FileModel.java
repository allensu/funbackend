package tw.com.funbackend.model;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface FileModel {
	
	/**
	 * create file from byte array
	 * @param filename file name
	 * @param file byte array
	 */
	public void createByByte(String filename, byte[] file);
	/**
	 * create file from file
	 * @param filename file name
	 * @param file
	 */
	public void createByFile(String filename, File file);
	public void get(String filename, OutputStream out);
	public GridFSDBFile get(String filename);
	public InputStream getInputStream(String filename);
}
