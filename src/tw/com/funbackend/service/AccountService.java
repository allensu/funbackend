package tw.com.funbackend.service;

import java.util.List;

import tw.com.funbackend.enumeration.FunctionalType;
import tw.com.funbackend.form.querycond.ManageMenuAuthCondition;
import tw.com.funbackend.persistence.MenuAuth;
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
	 * 取得指定的功能權限項目
	 * @param menuAuthId
	 * @return
	 */
	MenuAuth getMenuAuth(String menuAuthId);
	
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
	boolean updateMenuItem(String orgGroupId, String groupId, MenuItem menuItem);
	
	/**
	 * 更新功能權限項目
	 * @param menuAuthId
	 * @param newAuth
	 * @param updateAuth
	 * @param deleteAuth
	 * @param queryAuth
	 * @return
	 */
	MenuAuth updateMenuAuth(String menuAuthId, boolean enabled, boolean newAuth, boolean updateAuth, boolean deleteAuth, boolean queryAuth);
	
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
	 * 取得該帳號功能清單權限
	 * @param userInfoId
	 * @return
	 */
	List<MenuAuth> readMenuAuthByUserInfoId(String userInfoId);
	
	/**
	 * 取得條件下的總帳號權限筆數 
	 * @param cond 查詢條件
	 * @return
	 */
	int readMenuAuthCountByCond(ManageMenuAuthCondition cond);
	
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
	 * 取得帳號是否有功能項目權限
	 * @param functionalType
	 * @param userInfoId
	 * @param menuItemId
	 * @return
	 */
	boolean hasFuncationalAuth(FunctionalType functionalType, String userInfoId, String menuItemId);
}
