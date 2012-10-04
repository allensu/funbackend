package tw.com.funbackend.service;

import java.util.List;

import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.MenuItem;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;

public interface AccountService {
	
	/**
	 * 系統登入
	 * @param accountId
	 */
	UserInfo userLogin(String accountId, String accountPass);
	
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
	 * 取得所有帳號
	 * @return
	 */
	List<UserInfo> readUserAll();
	
	/**
	 * 刪除帳號資料
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
	 * @param groupId
	 * @param menuItem
	 * @return
	 */
	boolean updateMenuItem(String groupId, MenuItem menuItem);
	
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
}
