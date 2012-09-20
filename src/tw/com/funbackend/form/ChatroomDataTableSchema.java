package tw.com.funbackend.form;

public class ChatroomDataTableSchema {
	static public String[] MapColumns = {
		"","chatRoomStyle","numOfUser",""
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

	private String detailBtnCol;
	
}
