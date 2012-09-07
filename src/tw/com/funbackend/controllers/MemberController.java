package tw.com.funbackend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tw.com.funbackend.enumeration.UserInfoCategory;
import tw.com.funbackend.persistence.gopartyon.User;

import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.AccountService;
import tw.com.funbackend.service.MemberService;
import tw.com.funbackend.utility.Encrypt;

@SessionAttributes("userBean")
@Controller
public class MemberController {
	protected Logger logger = Logger.getLogger("controller");
		
	@Autowired
	private MemberService memberService;
	
	/**
	 * 會員黑名單
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Member/BlackMember", method = RequestMethod.GET)
	public ModelAndView blackMember(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/BlackMember");	
	}
	
	/**
	 * 會員基本資料查詢
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Member/MemberDataQuery", method = RequestMethod.GET)
	public ModelAndView memberDataQuery(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/MemberDataQuery");	
	}

	/**
	 * 取得所有User資料
	 * @return
	 */
	@RequestMapping(value = "/Member/MemberDataQuery/Read")
	public @ResponseBody List<User> readMember() {
		List<User> userDataList = new ArrayList<User>();
		
		try {
			userDataList = memberService.readUserAll();
			
			if(userDataList == null || userDataList.size() == 0)
			{
				userDataList = new ArrayList<User>();
			}
			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		logger.info("return readMember");
		return userDataList;
	}
	
	/**
	 * 取得特定User資料
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/Member/MemberDataQuery/Read/Id")
	public @ResponseBody User readMemberById(@RequestParam(value="id") String id) {
		User userData = new User();
		
		try {
			userData = memberService.readUser(id);
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userData;
	}
	
	/**
	 * 更新User資料
	 * @param userBean
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/Member/MemberDataQuery/Update", method = RequestMethod.POST)
	public @ResponseBody User updateUser(
			@ModelAttribute("userBean") UserBean userBean,
			@ModelAttribute User userP, @RequestParam(value="updateFiald") String updateFiald) {

		System.out.println("Update Fiald:" + updateFiald);
		
		User userData = new User();
		
		try {
			// 取得原本的 User 資料
			userData = memberService.readUser(userP.getId());
			
			//顯示名稱
			if("displayName".equals(updateFiald))
				userData.setDisplayName(userP.getDisplayName());
			
			//假帳號
			if("fake".equals(updateFiald))
				userData.setFake(userP.isFake());
			
			//生日
			if("birthday".equals(updateFiald))
				userData.setBirthday(userP.getBirthday());
			
			//信箱
			if("email".equals(updateFiald))
				userData.setEmail(userP.getEmail());
			
			//電話號碼
			if("phoneNo".equals(updateFiald))
				userData.setPhoneNo(userP.getPhoneNo());
			
			//國碼
			if("countryCode".equals(updateFiald))
				userData.setCountryCode(userP.getCountryCode());
			
			//地址
			if("address".equals(updateFiald))
				userData.setAddress(userP.getAddress());
			
			//贊數量
			if("numOfLikes".equals(updateFiald))
				userData.setNumOfLikes(userP.getNumOfLikes());
			
			//排名
			if("ranking".equals(updateFiald))
				userData.setRanking(userP.getRanking());
		
			//最後定位時間
			if("locationDateTime".equals(updateFiald))
				userData.setLocationDateTime(userP.getLocationDateTime());
			
			//最後打卡地點名稱
			if("placeName".equals(updateFiald))
				userData.setPlaceName(userP.getPlaceName());
			
			//興趣
			if("interest".equals(updateFiald))
				userData.setInterest(userP.getInterest());
			
			//專長
			if("profession".equals(updateFiald))
				userData.setProfession(userP.getProfession());
			
			//學校
			if("school".equals(updateFiald))
				userData.setSchool(userP.getSchool());
			
			//自我介紹
			if("description".equals(updateFiald))
				userData.setDescription(userP.getDescription());
			
			//更新時間
			if("updateTime".equals(updateFiald))
				userData.setUpdateTime(userP.getUpdateTime());
			
			//月得分
			if("monthScore".equals(updateFiald))
				userData.setMonthScore(userP.getMonthScore());
			
			//總得分
			if("totalScore".equals(updateFiald))
				userData.setTotalScore(userP.getTotalScore());
			
			//排行榜上升或下降
			if("rankingCompare".equals(updateFiald))
				userData.setRankingCompare(userP.getRankingCompare());
			
			//性別
			if("gender".equals(updateFiald))
				userData.setGender(userP.getGender());

			//封存
			if("deleted".equals(updateFiald))
				userData.setDeleted(userP.isDeleted());
			
			
			
			
			
			
			
			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userData;
	}
	
	/**
	 * 會員登入/出時間
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Member/MemberLoginRecord", method = RequestMethod.GET)
	public ModelAndView memberLoginRecord(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/MemberLoginRecord");	
	}
	
	/**
	 * 各地區會員數
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Member/MemberPlace", method = RequestMethod.GET)
	public ModelAndView memberPlace(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/MemberPlace");	
	}
}
