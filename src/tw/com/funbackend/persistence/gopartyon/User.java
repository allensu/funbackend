package tw.com.funbackend.persistence.gopartyon;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gofunfriend.gofuncube.partyon.vo.MobileToken;

import flexjson.JSON;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Document
// @JsonIgnoreProperties(value = {"password","mobileToken","username"})
// @JsonIgnoreProperties(value = {"id","username"})
public class User {
	@Transient
	public static final String PHOTO_ALBUM = "albumPhoto";
	@Transient
	public static final String PHOTO_PIC = "pic";
	@Transient
	public static final String RANKING_RISE = "rise";
	@Transient
	public static final String RANKING_DROPPED = "dropped";
	
	@Id
	private String id;
	@Indexed
	private String userName;
	@Indexed
	private String email;
	private String password;
	@Indexed
	private String countryCode;
	@Indexed
	private String phoneNo;
	private String gender;
	private String displayName;
	private int filenameCount;

	// @Transient
	private MobileToken mobileToken;
	
	private String wantTo;
	
	private Date wantToDateTime;
	
	private String birthday;
	
	private int numOfLikes;
	
	private String album;
	
	@Indexed
	private List<Map<String, String>> photos = new ArrayList<Map<String, String>>();
	
	private int numOfPic;
	
	@Indexed
	private int ranking;
	
	@Indexed
	private List<Map<String, Object>> likeUsers = new ArrayList<Map<String, Object>>();
	
	@Indexed
	private List<Map<String, Object>> visitors = new ArrayList<Map<String, Object>>();
	
	@Indexed
	private List<Map<String, Object>> blockUsers = new ArrayList<Map<String, Object>>();
	
	@Indexed
	private String pic;
	
	@GeoSpatialIndexed
	private Map<String, Double> location = new HashMap<String, Double>();
	
	private String address;
	
	private Date locationDateTime;
	
	private String registerId;
	
	private String placeName;
	
	private String interest;
	
	private String profession;
	
	private String school;
	
	private String description;
	
	private Date updateTime;
	
	@Indexed
	private int monthScore;

	@Indexed
	private int totalScore;
	
	public Date latestCalculatedRankingTime;
	
	public String rankingCompare;
	
	private String wantToAddress;
	
	private Date lastBroadcastDateTime;
	
	@Indexed
	private Date keepAliveDateTime;
	
	@Indexed
	private boolean isOnline;
	
	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Date getKeepAliveDateTime() {
		return keepAliveDateTime;
	}

	public void setKeepAliveDateTime(Date keepAliveDateTime) {
		this.keepAliveDateTime = keepAliveDateTime;
	}

	public Date getLastBroadcastDateTime() {
		return lastBroadcastDateTime;
	}

	public void setLastBroadcastDateTime(Date lastBroadcastDateTime) {
		this.lastBroadcastDateTime = lastBroadcastDateTime;
	}

	private Map<String, Double> wantToLocation = new HashMap<String, Double>(); 		
	
	public Map<String, Double> getWantToLocation() {
		return wantToLocation;
	}

	public void setWantToLocation(Map<String, Double> wantToLocation) {
		this.wantToLocation = wantToLocation;
	}

	public String getWantToAddress() {
		return wantToAddress;
	}

	public void setWantToAddress(String wantToAddress) {
		this.wantToAddress = wantToAddress;
	}

	public Date getLatestCalculatedRankingTime() {
		return latestCalculatedRankingTime;
	}

	public void setLatestCalculatedRankingTime(Date latestCalculatedRankingTime) {
		this.latestCalculatedRankingTime = latestCalculatedRankingTime;
	}

	public String getRankingCompare() {
		return rankingCompare;
	}

	public void setRankingCompare(String rankingCompare) {
		this.rankingCompare = rankingCompare;
	}

	public int getMonthScore() {
		return monthScore;
	}

	public void setMonthScore(int monthScore) {
		this.monthScore = monthScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	@JSON(include=false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean verifyPassword(User checker) {
		return checker.getPassword().equals(this.password);
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@JSON(include=false)
	public String getPassword() {
		return password;
	}
	
	public Map<String, Double> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JSON(include=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Date getLocationDateTime() {
		return locationDateTime;
	}

	public void setLocationDateTime(Date locationDateTime) {
		this.locationDateTime = locationDateTime;
	}

	public String getWantTo() {
		return wantTo;
	}

	public void setWantTo(String wantTo) {
		this.wantTo = wantTo;
	}

	public Date getWantToDateTime() {
		return wantToDateTime;
	}

	public void setWantToDateTime(Date wantToDateTime) {
		this.wantToDateTime = wantToDateTime;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getNumOfLikes() {
		return numOfLikes;
	}

	public void setNumOfLikes(int numOfLikes) {
		this.numOfLikes = numOfLikes;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public List<Map<String, String>> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Map<String, String>> photos) {
		this.photos = photos;
	}

	public int getNumOfPic() {
		return numOfPic;
	}

	public void setNumOfPic(int numOfPic) {
		this.numOfPic = numOfPic;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public List<Map<String, Object>> getLikeUsers() {
		return likeUsers;
	}

	public void setLikeUsers(List<Map<String, Object>> likeUsers) {
		this.likeUsers = likeUsers;
	}

	public List<Map<String, Object>> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Map<String, Object>> visitors) {
		this.visitors = visitors;
	}
	public List<Map<String, Object>> getBlockUsers() {
		return blockUsers;
	}

	public void setBlockUsers(List<Map<String, Object>> blockUsers) {
		this.blockUsers = blockUsers;
	}

	@Override
	public String toString() {
		return "User [Id=" + id + ", email=" + email + ", username=" + userName
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public void setMobileToken(MobileToken mobileToken) {
		this.mobileToken = mobileToken;
	}

	public MobileToken getMobileToken() {
		return mobileToken;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JSON(include=false)
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@JSON(include=false)
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}

	public static User fromJsonToUser(String json) {
		return new JSONDeserializer<User>().use(null, User.class)
				.deserialize(json, User.class);
	}

	public int getFilenameCount() {
		return filenameCount;
	}

	public void setFilenameCount(int filenameCount) {
		this.filenameCount = filenameCount;
	}
}
