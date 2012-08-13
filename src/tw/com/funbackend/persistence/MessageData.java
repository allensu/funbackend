package tw.com.funbackend.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MessageData {
	
	/**
	 * Mongo Id
	 */
	@Id
	private String id;
	
	/** 
	 * 流水號
	 */
	private int serial;
	
	/**
	 * 發送目標
	 */
	private String target;
	
	/**
	 * 發送訊息
	 */
	private String message;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
