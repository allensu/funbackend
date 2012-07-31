package tw.com.funbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.model.AccountModel;
import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountModel accountModel;

	@Override
	public UserBean userLogin(String accountId, String accountPass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void userLogout(String accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserInfo createUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		
		UserInfo userInfoResult = accountModel.createUser(userInfo);
		
		return userInfoResult;
	}

	@Override
	public List<MenuGroup> getMenuList(String accountId) {
			
		return accountModel.getMenuList(accountId);
	}
}
