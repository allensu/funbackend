package tw.com.funbackend.persistence.gopartyon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import tw.com.funbackend.persistence.gopartyon.User;

/**
 * 人人網使用者資料
 * @author allensu
 *
 */
@Document
public class RRUser{
	@Id
	private String id;
	@DBRef
	@Indexed
	private User user;
	@Indexed
	private String source;
	
	private String uid;
	private String name;
	private int sex;
	private int star;
	private int zidou;
	private int vip;
	private String birthday;
	private String email_hash;
	private String headurl;
	private String tinyurl;
	private String mainurl;
	private Map<String, String> hometown_location;
	private List<Map<String, String>> work_history;
	private List<Map> university_history;
	private List<Map> hs_history;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getZidou() {
		return zidou;
	}

	public void setZidou(int zidou) {
		this.zidou = zidou;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail_hash() {
		return email_hash;
	}

	public void setEmail_hash(String email_hash) {
		this.email_hash = email_hash;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public String getTinyurl() {
		return tinyurl;
	}

	public void setTinyurl(String tinyurl) {
		this.tinyurl = tinyurl;
	}

	public String getMainurl() {
		return mainurl;
	}

	public void setMainurl(String mainurl) {
		this.mainurl = mainurl;
	}

	public Map<String, String> getHometown_location() {
		return hometown_location;
	}

	public void setHometown_location(Map<String, String> hometown_location) {
		this.hometown_location = hometown_location;
	}

	public List<Map<String, String>> getWork_history() {
		return work_history;
	}

	public void setWork_history(List<Map<String, String>> work_history) {
		this.work_history = work_history;
	}

	public List<Map> getUniversity_history() {
		return university_history;
	}

	public void setUniversity_history(
			List<Map> university_history) {
		this.university_history = university_history;
	}

	public List<Map> getHs_history() {
		return hs_history;
	}

	public void setHs_history(List<Map> hs_history) {
		this.hs_history = hs_history;
	}

}
