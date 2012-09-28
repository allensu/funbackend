package tw.com.funbackend.form.querycond;

public class ChatroomMessageRecordCondition {
	
	/**
	 * 聊天室類別
	 */
	private String chatRoomStyleQ;
	/**
	 * 帳號名稱
	 */
	private String userNameQ;
	/**
	 * 經度
	 */
	private String longitudeQ;
	/**
	 * 緯度
	 */
	private String latitudeQ;
	/**
	 * 公里
	 */
	private String kilometerQ;
	
	
	/**
	 * 經度
	 * @return the longitude
	 */
	public String getLongitudeQ() {
		return longitudeQ;
	}

	/**
	 * 經度
	 * @param longitude the longitude to set
	 */
	public void setLongitudeQ(String longitudeQ) {
		this.longitudeQ = longitudeQ;
	}

	/**
	 * 緯度
	 * @return the latitude
	 */
	public String getLatitudeQ() {
		return latitudeQ;
	}

	/**
	 * 緯度
	 * @param latitude the latitude to set
	 */
	public void setLatitudeQ(String latitudeQ) {
		this.latitudeQ = latitudeQ;
	}

	/**
	 * 公里
	 * @return the kilometer
	 */
	public String getKilometerQ() {
		return kilometerQ;
	}

	/**
	 * 公里
	 * @param kilometer the kilometer to set
	 */
	public void setKilometerQ(String kilometerQ) {
		this.kilometerQ = kilometerQ;
	}

	/**
	 * 類別
	 * @return
	 */
	public String getChatRoomStyleQ() {
		return chatRoomStyleQ;
	}

	/**
	 * 類別
	 * @param chatRoomStyleQ
	 */
	public void setChatRoomStyleQ(String chatRoomStyleQ) {
		this.chatRoomStyleQ = chatRoomStyleQ;
	}

	/**
	 * @return 使用者名稱
	 */
	public String getUserNameQ() {
		return userNameQ;
	}

	/**
	 * @param 使用者名稱
	 */
	public void setUserNameQ(String userNameQ) {
		this.userNameQ = userNameQ;
	}



	
	
}
