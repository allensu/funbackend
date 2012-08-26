package tw.com.funbackend.persistence.gopartyon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import tw.com.funbackend.persistence.gopartyon.User;

/**
 * Facebook使用者資料
 * @author allensu
 *
 */
@Document
public class FBUser{
	
	@Id
	private String id;
	
	@DBRef
	@Indexed
	private User user;

	/**
	 * Facebook Id
	 */
	private String fbId;
	private String name;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String link;
	private String username;
	private String birthday;
	private String gender;
	private String locale;
	private List<Map<String, String>> languages = new ArrayList<Map<String, String>>();
	private String third_party_id;
	private int timezone;
	private String updated_time;
	private boolean verified;
	private String bio;

	private List<Map<String, String>> education = new ArrayList<Map<String, String>>();
	private String email;
	private Map<String, String> hometown;
	private List<String> interested_in = new ArrayList<String>();
	private Map<String, String> location = new HashMap<String, String>();
	private String political;
	private List<Map<String, String>> favorite_athletes = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> favorite_teams = new ArrayList<Map<String, String>>();
	private String quotes;
	private String relationship_status;
	private String religion;
	private Map<String, String> significant_other;
	private Map<String, String> video_upload_limits;
	private String website;
	private List<Map<String, String>> work = new ArrayList<Map<String, String>>();
	private String type;
	
	public String countryCode;
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public List<Map> friends = new ArrayList<Map>();
	
	public List<Map> getFriends() {
		return friends;
	}

	public void setFriends(List<Map> friends) {
		this.friends = friends;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Map<String, String>> getWork() {
		return work;
	}

	public void setWork(List<Map<String, String>> work) {
		this.work = work;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public List<Map<String, String>> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Map<String, String>> languages) {
		this.languages = languages;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getThird_party_id() {
		return third_party_id;
	}

	public void setThird_party_id(String third_party_id) {
		this.third_party_id = third_party_id;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public String getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Map<String, String>> getEducation() {
		return education;
	}

	public void setEducation(List<Map<String, String>> education) {
		this.education = education;
	}

	public Object getHometown() {
		return hometown;
	}

	public void setHometown( Map<String, String> hometown) {
		this.hometown = hometown;
	}

	public List<String> getInterested_in() {
		return interested_in;
	}

	public void setInterested_in(List<String> interested_in) {
		this.interested_in = interested_in;
	}

	public Map<String, String> getLocation() {
		return location;
	}

	public void setLocation(Map<String, String> location) {
		this.location = location;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public List< Map<String, String>> getFavorite_athletes() {
		return favorite_athletes;
	}

	public void setFavorite_athletes(List< Map<String, String>> favorite_athletes) {
		this.favorite_athletes = favorite_athletes;
	}

	public List< Map<String, String>> getFavorite_teams() {
		return favorite_teams;
	}

	public void setFavorite_teams(List< Map<String, String>> favorite_teams) {
		this.favorite_teams = favorite_teams;
	}

	public String getQuotes() {
		return quotes;
	}

	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}

	public String getRelationship_status() {
		return relationship_status;
	}

	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public Object getSignificant_other() {
		return significant_other;
	}

	public void setSignificant_other(Map<String, String> significant_other) {
		this.significant_other = significant_other;
	}

	public Object getVideo_upload_limits() {
		return video_upload_limits;
	}

	public void setVideo_upload_limits( Map<String, String> video_upload_limits) {
		this.video_upload_limits = video_upload_limits;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
	public String getFbId() {
		return fbId;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
}
