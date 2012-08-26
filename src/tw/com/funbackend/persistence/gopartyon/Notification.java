package tw.com.funbackend.persistence.gopartyon;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notification {
	@Transient
	public static final String CHATMESSAGE = "chatMessage";
	@Transient
	public static final String LEAVECHATROOM = "leaveChatRoom";
	@Transient
	public static final String ADDFRIENDS = "addFriends";
	@Transient
	public static final String RECEIVECHATMESSAGE = "receiveChatMessage";
	@Transient
	public static final String LIKE = "like";
	@Transient
	public static final String FRIENDCLOSE = "friendsClose";
	@Transient
	public static final String NEWUSERINCHATROOM = "newUserInChatRoom";	
	
	@Id
	String id;
	
	@DBRef
	@Indexed
	User user;
	
	/**
	 * 訊息類別
	 * ChatMessage
	 * LeaveChatRoom
	 * AddFriends
	 * ReceiveChatMessage
	 * Like
	 * FriendsClose
	 * NewUserInChatRoom
	 */
	@Indexed
	String category;
	
	Map<String, Object> content = new HashMap<String, Object>();
	
	Date createDateTime;
	
	@Indexed
	Date fetchDateTime;
	
	private String countryCode;
	
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

}
