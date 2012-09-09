package tw.com.funbackend.form;

public class MemberDataQueryDataTable {

	private String itemCheckCol;
	
	/**
	 * ID
	 */
	private String id;
	/**
	 * 帳號名稱
	 */
	private String userName;
	/**
	 * 顯示名稱
	 */
	private String displayName;
	/**
	 * 性別
	 */
	private String gender;
	/**
	 * 國別
	 */
	private String countryCode;
	/**
	 * 是否在線上
	 */
	private boolean isOnline;
	/**
	 * 假帳號
	 */
	private boolean isFake;
	/**
	 * 被按贊數
	 */
	private int numOfLikes;
	/**
	 * 月得分
	 */
	private int monthScore;
	/**
	 * 總得分
	 */
	private int totalScore;
	/**
	 * 名次
	 */
	private int ranking;
	
	private String detailBtnCol;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public boolean isFake() {
		return isFake;
	}
	public void setFake(boolean isFake) {
		this.isFake = isFake;
	}
	public int getNumOfLikes() {
		return numOfLikes;
	}
	public void setNumOfLikes(int numOfLikes) {
		this.numOfLikes = numOfLikes;
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
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemCheckCol() {
		return itemCheckCol;
	}
	public void setItemCheckCol(String itemCheckCol) {
		this.itemCheckCol = itemCheckCol;
	}
	public String getDetailBtnCol() {
		return detailBtnCol;
	}
	public void setDetailBtnCol(String detailBtnCol) {
		this.detailBtnCol = detailBtnCol;
	}
	
}
