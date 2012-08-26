package tw.com.funbackend.persistence.gopartyon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 好友關係
 * @author allensu
 *
 */
@Document
public class FriendShip {
	@Transient
	Logger log = Logger.getLogger(FriendShip.class);
	
	@Transient
	public final static String INVITED_NORMAL = "normal";
	
	@Transient
	public final static String INVITED_QR = "QR";
	
	@Transient
	public final static String RELATIONSHIP_FRIEND = "friend";
	
	@Transient
	public final static String RELATIONSHIP_STRANGER = "stranger";
	
	
	@Id
	private String id;
	
	/**
	 * 使用者
	 */
	@Indexed
	@DBRef
	private User user;
	
	/**
	 * 好友清單
	 */
	@DBRef
	private Set<User> friendList = new HashSet<User>();

	/**
	 * 可能成為好友清單
	 */
	@DBRef
	private Set<User> maybeFriends = new HashSet<User>();

	/**
	 * 拜訪的好友清單
	 */
	@DBRef
	@Indexed
	private Set<User> invitedFriends = new HashSet<User>(); 

	/**
	 * 好友數量
	 */
	private int numOfFriends;

	/**
	 * 可能成為好友數量
	 */
	private int numOfMayBeFriends;
	
	/**
	 * 配對的好友清單
	 */
	@DBRef
	@Indexed
	private Set<User> matchFriends = new HashSet<User>();
	
	/**
	 * 國碼
	 */
	private String countryCode;
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Set<User> getMatchFriends() {
		if (matchFriends == null)
			matchFriends = new HashSet<User>();
		return matchFriends;
	}

	public void setMatchFriends(Set<User> matchFriends) {
		this.matchFriends = matchFriends;
	}

	public Set<User> getInvitedFriends() {
		if (invitedFriends == null)
			invitedFriends = new HashSet<User>();
		return invitedFriends;
	}

	public void setInvitedFriends(Set<User> invitedFriends) {
		this.invitedFriends = invitedFriends;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getFriendList() {
		if (friendList == null)
			friendList = new HashSet<User>();
		return friendList;
	}

	public void setFriendList(Set<User> friendList) {
		this.friendList = friendList;
	}

	public Set<User> getMaybeFriends() {
		if (maybeFriends == null)
			new HashSet<User>();
		return maybeFriends;
	}

	public void setMaybeFriends(Set<User> maybeFriends) {
		this.maybeFriends = maybeFriends;
	}

	public int getNumOfFriends() {
		return numOfFriends;
	}

	public void setNumOfFriends(int numOfFriends) {
		this.numOfFriends = numOfFriends;
	}

	public int getNumOfMayBeFriends() {
		return numOfMayBeFriends;
	}

	public void setNumOfMayBeFriends(int numOfMayBeFriends) {
		this.numOfMayBeFriends = numOfMayBeFriends;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean addFriend(User friend) {
		boolean isAdd = false;
		if (!isExistFriendRelationShip(friend)) {
			getFriendList().add(friend);
			isAdd = true;
			numOfFriends++;
		}
		return isAdd;
	}


	public boolean isExistFriendRelationShip(User friend) {
		boolean isExist = false;
		Set<User> friends = getFriendList();
		if (friends.contains(friend))
			isExist = true;

		return isExist;
	}
	
	public List<String> getFriendUserNameList(){
		Set<User> friends = getFriendList();
		List<String> userNames = new ArrayList<String>(friends.size());
		for(User user: friends){
			String userName = user.getUserName();
			userNames.add(userName);
		}
		return userNames;
	}

}
