package tw.com.funbackend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tw.com.funbackend.persistence.gopartyon.User;

import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.AccountService;
import tw.com.funbackend.service.MemberService;

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
		
		return userDataList;
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
