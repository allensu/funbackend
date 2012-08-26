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

import tw.com.funbackend.persistence.gopartyon.vo.MobileToken;


/**
 * 使用者資料
 * @author allensu
 *
 */
@Document
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
	
	/**
	 * 帳號名稱
	 */
	@Indexed
	private String userName;
	
	/**
	 * 電子信箱
	 */
	@Indexed
	private String email;
	
	/**
	 * 密碼
	 */
	private String password;
	
	/**
	 * 國別
	 */
	@Indexed
	private String countryCode;
	
	/**
	 * 手機號碼
	 */
	@Indexed
	private String phoneNo;
	
	/**
	 * 性別
	 */
	private String gender;
	
	/**
	 * 顯示名稱
	 */
	private String displayName;
	
	
	private int filenameCount;

	/**
	 * 認證碼
	 */
	private MobileToken mobileToken;
	
	/**
	 * 想做什麼事
	 */
	private String wantTo;
	
	/**
	 * 想做什麼事的記錄時間
	 */
	private Date wantToDateTime;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 被按贊數
	 */
	private int numOfLikes;
	
	
	private String album;
	
	/**
	 * 上傳的照片
	 */
	@Indexed
	private List<Map<String, String>> photos = new ArrayList<Map<String, String>>();
	
	/**
	 * 上傳照片數量
	 */
	private int numOfPic;
	
	/**
	 * 排行名次
	 */
	@Indexed
	private int ranking;
	
	/**
	 * 給其它Users贊
	 */
	@Indexed
	private List<Map<String, Object>> likeUsers = new ArrayList<Map<String, Object>>();
	
	/**
	 * 拜訪者
	 */
	@Indexed
	private List<Map<String, Object>> visitors = new ArrayList<Map<String, Object>>();
	
	/**
	 * 黑名單列表
	 */
	@Indexed
	private List<Map<String, Object>> blockUsers = new ArrayList<Map<String, Object>>();
	
	/**
	 * 大頭照
	 */
	@Indexed
	private String pic;
	
	/**
	 * 最後定位點
	 */
	@GeoSpatialIndexed
	private Map<String, Double> location = new HashMap<String, Double>();
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 最後定位時間
	 */
	private Date locationDateTime;
	
	/**
	 * 註冊碼
	 */
	private String registerId;

	/**
	 * 最後打卡地點名稱
	 */
	private String placeName;
	
	/**
	 * 興趣
	 */
	private String interest;
	
	/**
	 * 專長
	 */
	private String profession;
	
	/**
	 * 學校
	 */
	private String school;
	
	private String description;
	
	/**
	 * 更新時間
	 */
	private Date updateTime;
	
	/**
	 * 月得分
	 */
	@Indexed
	private int monthScore;

	/**
	 * 總得分
	 */
	@Indexed
	private int totalScore;
	
	/**
	 * 最後計算排行榜時間
	 */
	public Date latestCalculatedRankingTime;
	
	/**
	 * 排行榜上升或下降
	 */
	public String rankingCompare;
	
	
	private String wantToAddress;
	
	private Date lastBroadcastDateTime;
	
	@Indexed
	private Date keepAliveDateTime;
	
	private Map<String, Double> wantToLocation = new HashMap<String, Double>(); 		
	
	/**
	 * 是否在線上
	 */
	@Indexed
	private boolean isOnline;
	
	private boolean isFake;
	
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	

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

	public int getFilenameCount() {
		return filenameCount;
	}

	public void setFilenameCount(int filenameCount) {
		this.filenameCount = filenameCount;
	}

	public boolean isFake() {
		return isFake;
	}

	public void setFake(boolean isFake) {
		this.isFake = isFake;
	}
}
