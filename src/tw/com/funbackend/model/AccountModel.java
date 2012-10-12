package tw.com.funbackend.model;

import java.util.Date;
import java.util.List;

import tw.com.funbackend.form.querycond.GraffitiWallCondition;
import tw.com.funbackend.form.querycond.ManageMenuAuthCondition;
import tw.com.funbackend.persistence.MenuAuth;
import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.MenuItem;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.persistence.gopartyon.GraffitiWallItem;
import tw.com.funbackend.pojo.UserBean;

public interface AccountModel {
	
	/**
	 * 取得帳號資訊
	 * @param accountId
	 * @return
	 */
	UserInfo getUserInfo(String accountId);
	
	/**
	 * 取得帳號資訊
	 * @param accountName
	 * @return
	 */
	UserInfo getUserInfoByAccountName(String accountName);
	
	/**
	 * 取得所有帳號
	 * @return
	 */
	List<UserInfo> getUserInfoAll();
	
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
	 * 建立帳號功能權限
	 * @param menuAuth
	 * @return
	 */
	MenuAuth createMenuAuth(MenuAuth menuAuth);
	
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
	 * 更新功能權限項目
	 * @param menuAuth
	 * @return
	 */
	boolean updateMenuAuth(MenuAuth menuAuth);
	
	/**
	 * 取得指定的功能權限項目
	 * @param menuAuthId
	 * @return
	 */
	MenuAuth getMenuAuth(String menuAuthId);
	
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
	
	/**
	 * 取得條件下的總帳號權限筆數 
	 * @param cond 查詢條件
	 * @return
	 */
	int readMenuAuthCountByCond(ManageMenuAuthCondition cond);
	
	/**
	 * 取得條件下分頁帳號權限
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @return
	 */
	//List<MenuAuth> readMenuAuthPageByCond(ManageMenuAuthCondition cond, int startIndex, int length);
	
	/**
	 * 取得條件及排序下分頁帳號權限
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @param sortColName 排序欄位名稱
	 * @param sortDir 排序方向 (-1:asc, 1:desc)
	 * @return
	 */
	List<MenuAuth> readMenuAuthPageByCondSort(ManageMenuAuthCondition cond, int startIndex, int length, String sortColName, int sortDir);
	
	/**
	 * 取得全部功能項目清單
	 * @param accountId
	 * @return
	 */
	List<MenuItem> getMenuItemListAll();
	
	/**
	 * 取得功能項目清單
	 * @param title
	 * @return
	 */
	MenuItem getMenuitemByTitle(String title);
	
}
