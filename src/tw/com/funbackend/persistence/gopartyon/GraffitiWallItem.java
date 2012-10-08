package tw.com.funbackend.persistence.gopartyon;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class GraffitiWallItem {
	@Transient
	Logger log = Logger.getLogger(FriendShip.class);
	
	@Transient
	public final static String POST_MESSAGE = "post-message";
	
	@Transient
	public final static String WANT_TO = "want-to";
	
	@Transient
	public final static String CHECK_IN = "check-in";
	
	@Id
	private String id;
	
	@Indexed
	@DBRef
	private User wallOwner;
	
	
	@Indexed
	@DBRef
	private User poster;
	
	@Indexed
	private long timeStamp;
	
	@Indexed
	private String category;
	
	private String message;
	
	@GeoSpatialIndexed
	private Map<String, Double> location = new HashMap<String, Double>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Double> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}

	public User getWallOwner() {
		return wallOwner;
	}

	public void setWallOwner(User wallOwner) {
		this.wallOwner = wallOwner;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

}
