package tw.com.funbackend.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;

public class ChatroomDataTableSchema {
	static public String[] MapColumns = {
		"","chatRoomStyle","numOfUser","users",""
		};
	
	private String itemCheckCol;
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 類別
	 */
	private String chatRoomStyle;
	
	/**
	 * 人數
	 */
	private int numOfUser;
	
	/**
	 * 參與人員
	 */
	private List<String> users = new ArrayList<String>();
	
	/**
	 * 離開人員
	 */
	private List<String> leaveUsers = new ArrayList<String>();
	
	/**
	 * 位置
	 */
	private Map<String, Double> location;
	
	private String detailBtnCol;
	
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

	public String getChatRoomStyle() {
		return chatRoomStyle;
	}

	public void setChatRoomStyle(String chatRoomStyle) {
		this.chatRoomStyle = chatRoomStyle;
	}

	public int getNumOfUser() {
		return numOfUser;
	}

	public void setNumOfUser(int numOfUser) {
		this.numOfUser = numOfUser;
	}

	public String getDetailBtnCol() {
		return detailBtnCol;
	}

	public void setDetailBtnCol(String detailBtnCol) {
		this.detailBtnCol = detailBtnCol;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public List<String> getLeaveUsers() {
		return leaveUsers;
	}

	public void setLeaveUsers(List<String> leaveUsers) {
		this.leaveUsers = leaveUsers;
	}

	public Map<String, Double> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}

	
	
}
