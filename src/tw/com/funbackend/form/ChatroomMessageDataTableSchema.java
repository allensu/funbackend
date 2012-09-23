package tw.com.funbackend.form;

import java.util.Date;

public class ChatroomMessageDataTableSchema {
	static public String[] MapColumns = {
		"","userName","senderName","type","message","fileName","fileSize","createDateTime"
		};
	
	private String itemCheckCol;
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 收信者
	 */
	private String userName;
	
	/**
	 * 發信者
	 */
	private String senderName;
	
	/**
	 * 訊息類別
	 */
	private String type;
	
	/**
	 * 訊息文字
	 */
	private String message;
	
	/**
	 * 檔案名稱
	 */
	private String fileName;
	
	/**
	 * 檔案大小
	 */
	private String fileSize;
	
	/**
	 * 建立時間
	 */
	private Date createDateTime;
	
	public String getItemCheckCol() {
		return itemCheckCol;
	}

	public void setItemCheckCol(String itemCheckCol) {
		this.itemCheckCol = itemCheckCol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	

}
