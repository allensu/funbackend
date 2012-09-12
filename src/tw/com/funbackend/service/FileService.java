package tw.com.funbackend.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
	
	/**
	 * Save file to file store
	 * @param inputFileName
	 * @param file byte array
	 */
	public void save(String inputFileName, byte[] file);
	
	/**
	 * Get file by file name
	 * @param filename
	 * @param out output stream
	 */
	public void get(String filename, OutputStream out);
	
	/**
	 * Get file by file name 
	 * @param fileName
	 * @return byte array
	 */
	public byte[] get(String fileName);
	
	public InputStream getInputStream(String filename);
}
