package tw.com.funbackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tw.com.funbackend.pojo.UserBean;

@SessionAttributes("userBean")
@Controller
public class ChatroomController {
	
	/**
	 * 聊天室記錄查詢
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Chatroom/ChatroomMessageRecord", method = RequestMethod.GET)
	public ModelAndView chatroomMessageRecord(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Chatroom/ChatroomMessageRecord");	
	}
}
