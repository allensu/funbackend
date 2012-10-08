package tw.com.funbackend.form.tableschema;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import tw.com.funbackend.persistence.gopartyon.User;

public class GraffitiWallTableSchema {
	static public String[] MapColumns = {
		"",
		"wallOwner",
		"poster",
		"timeStamp",
		"category",
		"message",
		"location"
		};
		
	private String itemCheckCol;
	/**
	 * ID
	 */
	private String id;
	private User wallOwner;
	private User poster;
	private long timeStamp;
	private String category;
	private String message;
	private Map<String, Double> location = new HashMap<String, Double>();
	
	private String detailBtnCol;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the itemCheckCol
	 */
	public String getItemCheckCol() {
		return itemCheckCol;
	}
	/**
	 * @param itemCheckCol the itemCheckCol to set
	 */
	public void setItemCheckCol(String itemCheckCol) {
		this.itemCheckCol = itemCheckCol;
	}
	/**
	 * @return the detailBtnCol
	 */
	public String getDetailBtnCol() {
		return detailBtnCol;
	}
	/**
	 * @param detailBtnCol the detailBtnCol to set
	 */
	public void setDetailBtnCol(String detailBtnCol) {
		this.detailBtnCol = detailBtnCol;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the wallOwner
	 */
	public User getWallOwner() {
		return wallOwner;
	}
	/**
	 * @param wallOwner the wallOwner to set
	 */
	public void setWallOwner(User wallOwner) {
		this.wallOwner = wallOwner;
	}
	/**
	 * @return the poster
	 */
	public User getPoster() {
		return poster;
	}
	/**
	 * @param poster the poster to set
	 */
	public void setPoster(User poster) {
		this.poster = poster;
	}
	/**
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the location
	 */
	public Map<String, Double> getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}

}
