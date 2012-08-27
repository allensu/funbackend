package tw.com.funbackend.persistence.gopartyon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ChatroomMessage {
	@Transient
	public final static List<String> TYPES = new ArrayList<String>(5);
	static{
		TYPES.add("text");
		TYPES.add("audio");
		TYPES.add("video");
		TYPES.add("photo");
		TYPES.add("location");		
	}
	
	@Id
	private String id;
	
	@Indexed
	private String chatroomId;
	
	@Indexed
	private String userName;
	
	private String senderName;
	
	private String type;
	
	private String message;
	
	private String fileName;
	
	private String fileSize;
	
	private String fileLenTime;
	
	private Map<String, Double> fileLocation;
	
	@Indexed
	private Date createDateTime;
	@Indexed
	private Date fetchDateTime;
	@Indexed
	private String countryCode;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getChatroomId() {
		return chatroomId;
	}

	public void setChatroomId(String chatroomId) {
		this.chatroomId = chatroomId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileLenTime() {
		return fileLenTime;
	}

	public void setFileLenTime(String fileLenTime) {
		this.fileLenTime = fileLenTime;
	}

	public Map<String, Double> getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(Map<String, Double> fileLocation) {
		this.fileLocation = fileLocation;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getFetchDateTime() {
		return fetchDateTime;
	}

	public void setFetchDateTime(Date fetchDateTime) {
		this.fetchDateTime = fetchDateTime;
	}

	public static String findType(String type){
		String findType = null;
		if (TYPES.contains(type)){
			int index = TYPES.indexOf(type);
			findType =  TYPES.get(index);
		}
		return findType;
	}
	
}
