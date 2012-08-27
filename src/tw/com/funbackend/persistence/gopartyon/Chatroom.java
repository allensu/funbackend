package tw.com.funbackend.persistence.gopartyon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Chatroom {
	@Transient
	public final static String SINGLE = "single";
	@Transient
	public final static String GROUP = "group";
	@Transient
	public final static String GEO = "geo";
	@Transient
	public final static String BROADCAST = "broadcast";
	@Transient
	public final static double GEOINSTANCE = 50.0;
	
	@Id
	private String id;
	
	@Indexed
	private String chatRoomStyle;
	
	@Indexed
	private int numOfUser;
	
	@GeoSpatialIndexed
	private Map<String, Double> location;

	@Indexed
	private List<String> users = new ArrayList<String>();
	
	//@DBRef
	@Indexed
	private List<String> leaveUsers = new ArrayList<String>();
	
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

	public String getChatRoomStyle() {
		return chatRoomStyle;
	}

	public void setChatRoomStyle(String chatRoomStyle) {
		this.chatRoomStyle = chatRoomStyle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public int getNumOfUser() {
		return numOfUser;
	}

	public void setNumOfUser(int numOfUser) {
		this.numOfUser = numOfUser;
	}

	public Map<String, Double> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}
	
	public void addUser(String userName){
		boolean isSuccess = users.add(userName);
		if (isSuccess){
			numOfUser++;
		}
	}
	
	public void removeUser(String userName){
		if (users.contains(userName)){
			users.remove(userName);
			numOfUser--;
		}
	}

}
