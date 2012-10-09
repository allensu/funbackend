package tw.com.funbackend.form.tableschema;

import tw.com.funbackend.persistence.MenuItem;
import tw.com.funbackend.persistence.UserInfo;

public class ManageMenuAuthTableSchema {
	
	static public String[] MapColumns = {
		"userInfo",
		"menuItem",
		"newAuth",
		"updateAuth",
		"deleteAuth",
		"queryAuth",
		""
		};
	
	private String id;
	private UserInfo userInfo;
	private MenuItem menuItem;
	private boolean newAuth;
	private boolean updateAuth;
	private boolean deleteAuth;
	private boolean queryAuth;
	private String saveBtnCol;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	public boolean isNewAuth() {
		return newAuth;
	}
	public void setNewAuth(boolean newAuth) {
		this.newAuth = newAuth;
	}
	public boolean isUpdateAuth() {
		return updateAuth;
	}
	public void setUpdateAuth(boolean updateAuth) {
		this.updateAuth = updateAuth;
	}
	public boolean isDeleteAuth() {
		return deleteAuth;
	}
	public void setDeleteAuth(boolean deleteAuth) {
		this.deleteAuth = deleteAuth;
	}
	public boolean isQueryAuth() {
		return queryAuth;
	}
	public void setQueryAuth(boolean queryAuth) {
		this.queryAuth = queryAuth;
	}
	public String getSaveBtnCol() {
		return saveBtnCol;
	}
	public void setSaveBtnCol(String saveBtnCol) {
		this.saveBtnCol = saveBtnCol;
	}
}
