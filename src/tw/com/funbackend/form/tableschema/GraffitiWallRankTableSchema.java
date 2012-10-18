package tw.com.funbackend.form.tableschema;

public class GraffitiWallRankTableSchema {
	
	static public String[] MapColumns = {
		///"",
		"posterName",
		"posterDisplayName",
		"totalCount",
		"postMessage",
		"wantTo",
		"checkIn",
		//""
		};
	
	private String posterId;
	private String posterName;
	private String posterDisplayName;
	private int totalCount;
	private int postMessage;
	private int wantTo;
	private int checkIn;
	
	/**
	 * @return the posterId
	 */
	public String getPosterId() {
		return posterId;
	}
	/**
	 * @param posterId the posterId to set
	 */
	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}
	/**
	 * @return the posterName
	 */
	public String getPosterName() {
		return posterName;
	}
	/**
	 * @param posterName the posterName to set
	 */
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the postMessage
	 */
	public int getPostMessage() {
		return postMessage;
	}
	/**
	 * @param postMessage the postMessage to set
	 */
	public void setPostMessage(int postMessage) {
		this.postMessage = postMessage;
	}
	/**
	 * @return the wantTo
	 */
	public int getWantTo() {
		return wantTo;
	}
	/**
	 * @param wantTo the wantTo to set
	 */
	public void setWantTo(int wantTo) {
		this.wantTo = wantTo;
	}
	/**
	 * @return the checkIn
	 */
	public int getCheckIn() {
		return checkIn;
	}
	/**
	 * @param checkIn the checkIn to set
	 */
	public void setCheckIn(int checkIn) {
		this.checkIn = checkIn;
	}
	public String getPosterDisplayName() {
		return posterDisplayName;
	}
	public void setPosterDisplayName(String posterDisplayName) {
		this.posterDisplayName = posterDisplayName;
	}
	
	
}
