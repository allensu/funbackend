package tw.com.funbackend.persistence.gopartyon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import tw.com.funbackend.persistence.gopartyon.User;

/**
 * Gmail使用者資料
 * @author allensu
 *
 */
@Document
public class GmailUser {
	
	@Indexed
	private String id;
	
	@DBRef
	@Indexed
	private User user;
	
	private String name;
	
	//private List<String> email;
	
	private String email;
	
	private String phoneNo;
	
	public List<Map> friends = new ArrayList<Map>();
	
	public String countryCode;
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<Map> getFriends() {
		return friends;
	}

	public void setFriends(List<Map> friends) {
		this.friends = friends;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUserId() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

//	public void setPhoneNo(String phoneNo) {
//		this.phoneNo = phoneNo;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
