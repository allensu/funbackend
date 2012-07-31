package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.persistence.MenuGroup;
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
}
