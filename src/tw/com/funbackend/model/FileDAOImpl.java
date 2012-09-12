package tw.com.funbackend.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
public class FileDAOImpl implements FileDAO {
	Logger log = Logger.getLogger(FileDAOImpl.class);
	
	@Autowired
	Mongo mongo;
	
	@Override
	public void createByByte(String filename, byte[] file) {
		try {
			DB db = mongo.getDB("partyon");
			// create a "photo" namespace
			GridFS gfsPhoto = new GridFS(db, "filestore");
			GridFSInputFile gfsFile = gfsPhoto.createFile(file);
			gfsFile.setFilename(filename);
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(filename);
			gfsFile.setContentType(contentType);
			gfsFile.save();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createByFile(String filename, File file) {
		try {
			DB db = mongo.getDB("partyon");
			// create a "photo" namespace
			GridFS gfsPhoto = new GridFS(db, "filestore");
			GridFSInputFile gfsFile = null;
			try {
				gfsFile = gfsPhoto.createFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(filename);
			
			/*if (filename.lastIndexOf(".") != -1)
				contentType = filename.substring(filename.lastIndexOf(".") + 1);*/
			gfsFile.setFilename(filename);
			gfsFile.setContentType(contentType);
			gfsFile.save();
		} catch (MongoException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void get(String filename, OutputStream out) {
		try {
			DB db = mongo.getDB("partyon");
			GridFS gfsPhoto = new GridFS(db, "filestore");
			log.info("get1 filename = " + filename);
			GridFSDBFile imageForOutput = gfsPhoto.findOne(filename);
			if(imageForOutput != null && imageForOutput.getLength() > 0)
				imageForOutput.writeTo(out);			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public GridFSDBFile get(String filename) {
		GridFSDBFile imageForOutput = null;
		try {
			DB db = mongo.getDB("partyon");
			GridFS gfsPhoto = new GridFS(db, "filestore");
			log.debug("get filename = " + filename);
			imageForOutput = gfsPhoto.findOne(filename);
		} catch (MongoException e) {
			e.printStackTrace();
			return null;
		} 
		return imageForOutput;
	}
	
	@Override
	public InputStream getInputStream(String filename) {
		GridFSDBFile imageForOutput = null;
		try {
			DB db = mongo.getDB("partyon");
			GridFS gfsPhoto = new GridFS(db, "filestore");
			log.debug("getInputStream filename = " + filename);
			imageForOutput = gfsPhoto.findOne(filename);
		} catch (MongoException e) {
			e.printStackTrace();
			return null;
		} 
		if(imageForOutput != null)
			return imageForOutput.getInputStream();
		else
			return null;
	}

}
