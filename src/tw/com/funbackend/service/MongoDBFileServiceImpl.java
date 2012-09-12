package tw.com.funbackend.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.model.FileDAO;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class MongoDBFileServiceImpl implements FileService {
	
	@Autowired
	FileDAO fileDAO;
	
	@Override
	public void save(String inputFileName, byte[] file) {
		fileDAO.createByByte(inputFileName, file);

	}

	@Override
	public void get(String filename, OutputStream out) {
		fileDAO.get(filename, out);
	}

	@Override
	public byte[] get(String fileName) {
		byte[] bytes = null;
		try {
			GridFSDBFile file = fileDAO.get(fileName);
			if(file == null)
				return null;
			bytes = IOUtils.toByteArray(file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bytes;
	}
	
	@Override
	public InputStream getInputStream(String filename){
		return fileDAO.getInputStream(filename);
	}

}
