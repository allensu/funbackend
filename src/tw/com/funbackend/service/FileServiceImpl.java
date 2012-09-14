package tw.com.funbackend.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.model.FileModel;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileModel fileModel;
	
	@Override
	public void save(String inputFileName, byte[] file) {
		fileModel.createByByte(inputFileName, file);

	}

	@Override
	public void get(String filename, OutputStream out) {
		fileModel.get(filename, out);
	}

	@Override
	public byte[] get(String fileName) {
		byte[] bytes = null;
		try {
			GridFSDBFile file = fileModel.get(fileName);
			if(file == null)
				return null;
			bytes = IOUtils.toByteArray(file.getInputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return bytes;
	}
	
	@Override
	public InputStream getInputStream(String filename){
		return fileModel.getInputStream(filename);
	}

}
