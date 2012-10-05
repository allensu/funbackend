package tw.com.funbackend.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EnumType;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.JsonView;

import com.mchange.v2.c3p0.stmt.GooGooStatementCache;

import tw.com.funbackend.enumeration.UserInfoCategory;
import tw.com.funbackend.form.AccountLoginForm;
import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.MenuItem;
import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.AccountService;
import tw.com.funbackend.utility.Encrypt;
import tw.com.funbackend.utility.StringUtility;

@SessionAttributes("userBean")
@Controller
public class AccountController {
	//Logger log = Logger.getLogger(AccountController.class);
	protected Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private AccountService accountService;

	@ModelAttribute("userBean")
	public UserBean initUserBean() {
	   return new UserBean(); // populates form for the first time if its null
	}
	
	/**
	 * 帳號系統
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Account/Index", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("/Account/Login");
	}

	/**
	 * 使用者登入作業
	 * 
	 * @param userBean
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/Account/Login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userBean") UserBean userBean,
			@ModelAttribute AccountLoginForm form) {

		UserInfo userInfo = accountService.userLogin(form.getAccount(), form.getPassword());
		
		if(userInfo == null)
		{
			return new ModelAndView("/Account/Login");
		} else {
			userBean.setAccountId(userInfo.getAccountId());
			userBean.setAccountName(userInfo.getAccountName());
			userBean.setTheme(form.getTheme());
			userBean.setLoginDateTime(userInfo.getLastLoginDateTime());
			return new ModelAndView("redirect:/controller/Home/Index");
		}
		
	}
	
	/**
	 * 使用者登出作業
	 * @return
	 */
	@RequestMapping(value = "/Account/Logout", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("userBean") UserBean userBean)
	{
		try 
		{
			accountService.userLogout(userBean.getAccountId());
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return new ModelAndView("/Account/Login");
	}

	/**
	 * 建立使用者帳號
	 * 
	 * @param userBean
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/Account/CreateUser", method = RequestMethod.POST)
	public ModelAndView createUser(
			@ModelAttribute("userBean") UserBean userBean,
			@ModelAttribute UserInfo userInfo) {

		userInfo.setAccountPass(Encrypt.encodePassword(userInfo
				.getAccountPass()));
		userInfo.setCategory(UserInfoCategory.Admin);
		userInfo.setCreateDateTime(new Date());

		UserInfo userInfoResult = accountService.createUser(userInfo);
		
		return new ModelAndView("/Account/ManageUser");
	}
	
	@RequestMapping(value = "/Account/ReadUser")
	public @ResponseBody List<UserInfo> readUser() {
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		
		try {
			userInfoList = accountService.readUserAll();
			
			if(userInfoList == null || userInfoList.size() == 0)
			{
				userInfoList = new ArrayList<UserInfo>();
			}
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userInfoList;
	}
	
	/**
	 * 刪除資料
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/Account/DeleteAccount", method = RequestMethod.POST)
	public @ResponseBody List<UserInfo> deleteAccount(
			@RequestParam(value="ids") List<String> ids) {
		List<UserInfo> userInfoDataList = new ArrayList<UserInfo>();
		
		try {
			// 刪除資料
			accountService.removeUserInfo(ids);
			
			// 重新查詢
			userInfoDataList = accountService.readUserAll();
			
			if(userInfoDataList == null || userInfoDataList.size() == 0)
			{
				userInfoDataList = new ArrayList<UserInfo>();
			}
			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userInfoDataList;
	}

	/**
	 * 開啟使用者帳號管理頁面
	 * 
	 * @param userBean
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/Account/ManageUser", method = RequestMethod.GET)
	public ModelAndView manageUser(@ModelAttribute("userBean") UserBean userBean) {    
		return new ModelAndView("/Account/ManageUser");
	}

	/**
	 * 開啟功能群組管理頁面
	 * 
	 * @param userBean
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/Account/ManageMenu", method = RequestMethod.GET)
	public ModelAndView manageMenuGroup(@ModelAttribute("userBean") UserBean userBean) {    
		return new ModelAndView("/Account/ManageMenu");
	}
	
	/**
	 * 取得使用者功能清單
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/Account/MenuList")
	public @ResponseBody List<MenuGroup> getMenuList(@ModelAttribute("userBean") UserBean userBean) {
		
		
		// 取得使用帳號的權限
		List<MenuGroup> menuGroupListResult = accountService
				.getMenuList(userBean.getAccountId());

		return menuGroupListResult;
	}
	
	/**
	 * 建立新群組
	 * 
	 * @param userBean
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/Account/ManageMenu/GroupItem/Create", method = RequestMethod.POST)
	public @ResponseBody List<MenuGroup> createGroupItem(
			@ModelAttribute("userBean") UserBean userBean,
			@ModelAttribute MenuGroup menuGroup) {

		List<MenuGroup> result = new ArrayList<MenuGroup>();
		
		try 
		{
			accountService.createMenuGroup(menuGroup);
			
			result = accountService.getMenuList(userBean.getAccountId());			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 建立新功能項目
	 * 
	 * @param userBean
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/Account/ManageMenu/MenuItem/Create", method = RequestMethod.POST)
	public @ResponseBody List<MenuGroup> createMenuItem(
			@ModelAttribute("userBean") UserBean userBean,
			@RequestParam(value="groupId") String groupId,
			@ModelAttribute MenuItem menuItem) {

		List<MenuGroup> result = new ArrayList<MenuGroup>();
		
		try 
		{
			accountService.createMenuItem(menuItem);
			
			if(StringUtility.isNotEmpty(menuItem.getId()))
			{
				MenuGroup menuGroup = accountService.getMenuGroup(groupId);			
				menuGroup.getContent().add(menuItem);
				accountService.createMenuGroup(menuGroup);
			}
			
			result = accountService.getMenuList(userBean.getAccountId());			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 修改群組
	 * 
	 * @param userBean
	 * @param menuGroup
	 * @return
	 */
	@RequestMapping(value = "/Account/ManageMenu/GroupItem/Update", method = RequestMethod.POST)
	public @ResponseBody List<MenuGroup> updateGroupItem(
			@ModelAttribute("userBean") UserBean userBean,
			@ModelAttribute MenuGroup menuGroup) {

		List<MenuGroup> result = new ArrayList<MenuGroup>();
		
		try 
		{
			accountService.updateMenuGroup(menuGroup);
			
			result = accountService.getMenuList(userBean.getAccountId());			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 修改功能項目
	 * 
	 * @param userBean
	 * @param groupId
	 * @param menuItem
	 * @return
	 */
	@RequestMapping(value = "/Account/ManageMenu/MenuItem/Update", method = RequestMethod.POST)
	public @ResponseBody List<MenuGroup> updateMenuItem(
			@ModelAttribute("userBean") UserBean userBean,
			@RequestParam(value="orgGroupId") String orgGroupId,
			@RequestParam(value="groupId") String groupId,
			@ModelAttribute MenuItem menuItem) {

		List<MenuGroup> result = new ArrayList<MenuGroup>();
		
		try 
		{
			accountService.updateMenuItem(orgGroupId, groupId, menuItem);				
			
			result = accountService.getMenuList(userBean.getAccountId());			
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/Account/ManageMenu/MenuItem/Remove", method = RequestMethod.POST)
	public @ResponseBody List<MenuGroup> removeMenuItem(
			@ModelAttribute("userBean") UserBean userBean,
			@RequestParam(value="itemId") String itemId) {
		
		List<MenuGroup> result = new ArrayList<MenuGroup>();
		
		try {
			// 刪除資料
			accountService.removeMenuItem(itemId);
					
			result = accountService.getMenuList(userBean.getAccountId());			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/Account/ManageMenu/GroupItem/Remove", method = RequestMethod.POST)
	public @ResponseBody List<MenuGroup> removeGroupItem(
			@ModelAttribute("userBean") UserBean userBean,
			@RequestParam(value="groupId") String groupId) {
		
		List<MenuGroup> result = new ArrayList<MenuGroup>();
		
		try {
			// 刪除資料
			accountService.removeMenuGroup(groupId);
					
			result = accountService.getMenuList(userBean.getAccountId());			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
}
