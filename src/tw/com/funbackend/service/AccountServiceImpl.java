package tw.com.funbackend.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.enumeration.UserInfoCategory;
import tw.com.funbackend.model.AccountModel;
import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.utility.Encrypt;

@Service
public class AccountServiceImpl implements AccountService {
	protected Logger logger = Logger.getLogger("service");
	
	@Autowired
	private AccountModel accountModel;

	@Override
	public UserInfo userLogin(String accountId, String accountPass) {
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		UserInfo userInfo = null;
		
		try {
			userInfo = accountModel.getUserInfo(accountId);
			
			if(userInfo == null)
			{
				if(accountId.equals("admin"))
				{
					logger.info("第一次登入 建立 admin 帳號 : " + accountId);
					UserInfo adminUserInfo = new UserInfo();
					adminUserInfo.setAccountId(accountId);
					adminUserInfo.setAccountName(accountId);
					adminUserInfo.setAccountPass(Encrypt.encodePassword(accountPass));
					adminUserInfo.setCategory(UserInfoCategory.Admin);
					
					userInfo = createUser(adminUserInfo);					
				} else {
					logger.info("無此帳號 : " + accountId);
					return null;
				}
			}
			
			String accountPassS = Encrypt.encodePassword(accountPass);
			
			if(userInfo.getAccountPass().equals(accountPassS) == false)
			{
				logger.info("帳號密碼錯誤 : " + accountId);
				return null;
			}
			
			accountModel.updateUserLoginTime(accountId, cal.getTime());
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userInfo;
	}

	@Override
	public void userLogout(String accountId) {

		accountModel.userLogout(accountId);
	}

	@Override
	public UserInfo createUser(UserInfo userInfo) {
		
		UserInfo userInfoResult = accountModel.createUser(userInfo);
		return userInfoResult;
	}

	@Override
	public List<MenuGroup> getMenuList(String accountId) {
			
		return accountModel.getMenuList(accountId);
	}

	@Override
	public List<UserInfo> readUserAll() {
		
		return accountModel.readUserAll();
	}

	@Override
	public boolean removeUserInfo(List<String> ids) {
	
		return accountModel.removeUserInfo(ids);
	}
}
