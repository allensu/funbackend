package tw.com.funbackend.model;

import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;

public interface AccountModel {
	UserInfo getUserInfo(String accountId);
	void userLogout(String accountId);
	UserInfo createUser(UserInfo userInfo);
}
