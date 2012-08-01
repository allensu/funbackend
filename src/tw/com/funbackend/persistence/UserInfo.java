package tw.com.funbackend.persistence;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import tw.com.funbackend.enumeration.UserInfoCategory;

@Document
public class UserInfo {
	
	/**
	 * Mongo Id
	 */
	@Id
	private String id;
	/**
	 * 帳號 Id
	 */
	private String accountId;
	/**
	 * 帳號密碼
	 */
	private String accountPass;
	/**
	 * 帳號名稱
	 */
	private String accountName;
	/**
	 * 最近登入時間
	 */
	private Date lastLoginDateTime;
	/**
	 * 建立時間
	 */
	private Date createDateTime;
	/**
	 * 帳號身份
	 */
	private UserInfoCategory category;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Date getLastLoginDateTime() {
		return lastLoginDateTime;
	}
	public void setLastLoginDateTime(Date lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getAccountPass() {
		return accountPass;
	}
	public void setAccountPass(String accountPass) {
		this.accountPass = accountPass;
	}
	public UserInfoCategory getCategory() {
		return category;
	}
	public void setCategory(UserInfoCategory category) {
		this.category = category;
	}	
}
