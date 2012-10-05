package tw.com.funbackend.model;

import java.util.Date;
import java.util.List;

import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.MenuItem;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;

public interface AccountModel {
	
	/**
	 * 取得帳號資訊
	 * @param accountId
	 * @return
	 */
	UserInfo getUserInfo(String accountId);
	/**
	 * 系統登出
	 * @param accountId
	 */
	void userLogout(String accountId);
	/**
	 * 建立帳號
	 * @param userInfo
	 * @return
	 */
	UserInfo createUser(UserInfo userInfo);
	
	/**
	 * 取得功能清單
	 * @param accountId
	 * @return
	 */
	List<MenuGroup> getMenuList(String accountId);
	
	/**
	 * 記錄使用者登入時間
	 * @param accountId
	 */
	void updateUserLoginTime(String accountId, Date loginTime);
	
	/**
	 * 取得所有帳號
	 * @return
	 */
	List<UserInfo> readUserAll();
	
	/**
	 * 移除多筆帳號訊息
	 * @param ids
	 * @return
	 */
	boolean removeUserInfo(List<String> ids);
	
	/**
	 * 建立群組
	 * @param menuGroup
	 * @return
	 */
	boolean createMenuGroup(MenuGroup menuGroup);
	
	/**
	 * 建立功能項目
	 * @param menuItem
	 * @return
	 */
	boolean createMenuItem(MenuItem menuItem);
	
	/**
	 * 更新群組
	 * @param menuGroup
	 * @return
	 */
	boolean updateMenuGroup(MenuGroup menuGroup);
	
	/**
	 * 更新功能項目
	 * @param menuItem
	 * @return
	 */
	boolean updateMenuItem(MenuItem menuItem);
	
	/**
	 * 取得指定的群組項目
	 * @param groupId
	 * @return
	 */
	MenuGroup getMenuGroup(String groupId);
	
	/**
	 * 刪除功能項目
	 * @param itemId
	 * @return
	 */
	boolean removeMenuItem(String itemId);
	
	/**
	 * 刪除群組
	 * @param groupId
	 * @return
	 */
	boolean removeMenuGroup(String groupId);
	
	/**
	 * 刪除所有群組中的指定關連資料
	 * @param itemId
	 * @return
	 */
	boolean removeMenuGroupRefContent(String itemId);
	
	/**
	 * 刪除指定的群組中的指定關連資料
	 * @param groupId
	 * @param itemId
	 * @return
	 */
	boolean removeMenuGroupRefContent(String groupId, String itemId);
}
