package tw.com.funbackend.service;

import java.util.List;

import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;

public interface AccountService {
	UserInfo userLogin(String accountId, String accountPass);
	void userLogout(String accountId);
	UserInfo createUser(UserInfo userInfo);
	List<MenuGroup> getMenuList(String accountId);
}
