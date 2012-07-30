package tw.com.funbackend.service;

import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;

public interface AccountService {
	UserBean userLogin(String accountId, String accountPass);
	void userLogout(String accountId);
	UserInfo createUser(UserInfo userInfo);
}
