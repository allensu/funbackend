package tw.com.funbackend.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MenuAuth {
	
	/**
	 * Mongo Id
	 */
	@Id
	private String id;
	
	@Indexed
	@DBRef
	private UserInfo userInfo;
	
	private boolean enabled;
	
	private boolean newAuth;
	
	private boolean updateAuth;
	
	private boolean deleteAuth;
	
	private boolean queryAuth;

	@Indexed
	@DBRef
	private MenuItem menuItem;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return the newAuth
	 */
	public boolean isNewAuth() {
		return newAuth;
	}

	/**
	 * @param newAuth the newAuth to set
	 */
	public void setNewAuth(boolean newAuth) {
		this.newAuth = newAuth;
	}

	/**
	 * @return the updateAuth
	 */
	public boolean isUpdateAuth() {
		return updateAuth;
	}

	/**
	 * @param updateAuth the updateAuth to set
	 */
	public void setUpdateAuth(boolean updateAuth) {
		this.updateAuth = updateAuth;
	}

	/**
	 * @return the deleteAuth
	 */
	public boolean isDeleteAuth() {
		return deleteAuth;
	}

	/**
	 * @param deleteAuth the deleteAuth to set
	 */
	public void setDeleteAuth(boolean deleteAuth) {
		this.deleteAuth = deleteAuth;
	}

	/**
	 * @return the queryAuth
	 */
	public boolean isQueryAuth() {
		return queryAuth;
	}

	/**
	 * @param queryAuth the queryAuth to set
	 */
	public void setQueryAuth(boolean queryAuth) {
		this.queryAuth = queryAuth;
	}
	
	/**
	 * @return the menuItem
	 */
	public MenuItem getMenuItem() {
		return menuItem;
	}

	/**
	 * @param menuItem the menuItem to set
	 */
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
