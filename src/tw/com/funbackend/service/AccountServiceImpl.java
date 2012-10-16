package tw.com.funbackend.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.activity.ActivityRequiredException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.enumeration.FunctionalType;
import tw.com.funbackend.enumeration.UserInfoCategory;
import tw.com.funbackend.form.querycond.ManageMenuAuthCondition;
import tw.com.funbackend.model.AccountModel;
import tw.com.funbackend.persistence.MenuAuth;
import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.MenuItem;
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
		
		List<MenuItem> menuItemList = accountModel.getMenuItemListAll();
		
		for(MenuItem currData : menuItemList)
		{
			MenuAuth menuAuth = new MenuAuth();
			menuAuth.setUserInfo(userInfoResult);
			
			if(userInfoResult.getCategory() == UserInfoCategory.Admin)
			{
				menuAuth.setEnabled(true);
				menuAuth.setNewAuth(true);
				menuAuth.setUpdateAuth(true);
				menuAuth.setDeleteAuth(true);
				menuAuth.setQueryAuth(true);
			} 
			else if(userInfoResult.getCategory() == UserInfoCategory.Normal)
			{
				menuAuth.setEnabled(true);
				menuAuth.setNewAuth(false);
				menuAuth.setUpdateAuth(false);
				menuAuth.setDeleteAuth(false);
				menuAuth.setQueryAuth(true);
			}
			else 
			{
				menuAuth.setEnabled(false);
				menuAuth.setNewAuth(false);
				menuAuth.setUpdateAuth(false);
				menuAuth.setDeleteAuth(false);
				menuAuth.setQueryAuth(false);
			}
			
			menuAuth.setMenuItem(currData);
			accountModel.createMenuAuth(menuAuth);
		}
		
		return userInfoResult;
	}

	@Override
	public List<MenuGroup> getMenuList(String accountId) {
			
		UserInfo userInfo = accountModel.getUserInfo(accountId);
		
		return accountModel.getMenuList(userInfo);
	}

	@Override
	public List<UserInfo> readUserAll() {
		
		return accountModel.readUserAll();
	}

	@Override
	public boolean removeUserInfo(List<String> ids) {
	
		boolean result = false;
		
		try 
		{
			for(String currId : ids)
			{
				accountModel.deleteMenuAuthByUserInfoId(currId);
				accountModel.removeUserInfo(currId);
			}
			
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public boolean createMenuGroup(MenuGroup menuGroup) {
				
		return accountModel.createMenuGroup(menuGroup);
	}

	@Override
	public MenuGroup getMenuGroup(String groupId) {
				
		return accountModel.getMenuGroup(groupId);
	}

	@Override
	public boolean createMenuItem(MenuItem menuItem) {
		
		boolean result = false;
		try 
		{
			result = accountModel.createMenuItem(menuItem);
			
			List<UserInfo> userInfoList = accountModel.getUserInfoAll();
			
			for(UserInfo currData : userInfoList)
			{
				MenuAuth menuAuth = new MenuAuth();
				menuAuth.setUserInfo(currData);
				
				if(currData.getCategory() == UserInfoCategory.Admin)
				{
					menuAuth.setEnabled(true);
					menuAuth.setNewAuth(true);
					menuAuth.setUpdateAuth(true);
					menuAuth.setDeleteAuth(true);
					menuAuth.setQueryAuth(true);
				} 
				else if(currData.getCategory() == UserInfoCategory.Normal)
				{
					menuAuth.setEnabled(true);
					menuAuth.setNewAuth(false);
					menuAuth.setUpdateAuth(false);
					menuAuth.setDeleteAuth(false);
					menuAuth.setQueryAuth(true);
				}
				else 
				{
					menuAuth.setEnabled(false);
					menuAuth.setNewAuth(false);
					menuAuth.setUpdateAuth(false);
					menuAuth.setDeleteAuth(false);
					menuAuth.setQueryAuth(false);
				}
				
				menuAuth.setMenuItem(menuItem);
				accountModel.createMenuAuth(menuAuth);				
			}
			
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		
		return result;
	}

	@Override
	public boolean removeMenuItem(String itemId) {
		
		// remove MenuGroup Ref
		accountModel.removeMenuGroupRefContent(itemId);		
		return accountModel.removeMenuItem(itemId);
	}

	@Override
	public boolean removeMenuGroup(String groupId) {

		return accountModel.removeMenuGroup(groupId);
	}

	@Override
	public boolean updateMenuGroup(MenuGroup menuGroup) {
		boolean result = false;
		
		try {			
			MenuGroup orgMenuGroup = accountModel.getMenuGroup(menuGroup.getId());
			menuGroup.setContent(orgMenuGroup.getContent());			
			result = accountModel.updateMenuGroup(menuGroup);
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public boolean updateMenuItem(String orgGroupId, String groupId, MenuItem menuItem) {
		
		boolean result = false;
		
		try {			
			
			accountModel.updateMenuItem(menuItem);
			
			MenuGroup orgMenuGroup = accountModel.getMenuGroup(orgGroupId);
			
			if(orgGroupId != groupId) {
				// remove orgMenuGroup 
				accountModel.removeMenuGroupRefContent(orgGroupId, menuItem.getId());
								
				MenuGroup menuGroup = accountModel.getMenuGroup(groupId);
				menuGroup.getContent().add(menuItem);
				
				result = accountModel.updateMenuGroup(menuGroup);
			} else {
				result = true;
			}
			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public int readMenuAuthCountByCond(ManageMenuAuthCondition cond) {
		
		return accountModel.readMenuAuthCountByCond(cond);		
	}

	@Override
	public List<MenuAuth> readMenuAuthPageByCondSort(
			ManageMenuAuthCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {
		
		return accountModel.readMenuAuthPageByCondSort(cond, startIndex, length, sortColName, sortDir);		
	}

	@Override
	public MenuAuth updateMenuAuth(String menuAuthId, boolean enabled, boolean newAuth,
			boolean updateAuth, boolean deleteAuth, boolean queryAuth) {
		MenuAuth result = new MenuAuth();
		
		try 
		{
			MenuAuth menuAuth = accountModel.getMenuAuth(menuAuthId);
			menuAuth.setEnabled(enabled);
			menuAuth.setNewAuth(newAuth);
			menuAuth.setUpdateAuth(updateAuth);
			menuAuth.setDeleteAuth(deleteAuth);
			menuAuth.setQueryAuth(queryAuth);
						
			accountModel.updateMenuAuth(menuAuth);
			
			result = menuAuth;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public MenuAuth getMenuAuth(String menuAuthId) {

		return accountModel.getMenuAuth(menuAuthId);
	}

	@Override
	public List<MenuAuth> readMenuAuthByUserInfoId(String userInfoId) {

		return accountModel.readMenuAuthByUserInfoId(userInfoId);
	}

	@Override
	public boolean hasFuncationalAuth(FunctionalType functionalType,
			String userInfoId, String menuItemId) {

		boolean result = false;
		
		try {			
			
			MenuAuth menuAuth = accountModel.getMenuAuth(userInfoId, menuItemId);
			
			if(menuAuth == null)
				result = false;
			else {
				if(functionalType == FunctionalType.NewAuth)
					result = menuAuth.isNewAuth();
				else if(functionalType == FunctionalType.UpdateAuth)
					result = menuAuth.isUpdateAuth();
				else if(functionalType == FunctionalType.DeleteAuth)
					result = menuAuth.isDeleteAuth();
				else if(functionalType == FunctionalType.QueryAuth)
					result = menuAuth.isQueryAuth();
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		
		return result;
	}
}
