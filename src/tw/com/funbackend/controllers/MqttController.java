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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tw.com.funbackend.enumeration.UserInfoCategory;
import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.AccountService;
import tw.com.funbackend.service.MqttService;
import tw.com.funbackend.utility.Encrypt;

@SessionAttributes("userBean")
@Controller
public class MqttController {
	
	protected Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private MqttService mqttService;
	
	/**
	 * MQTT資料管理
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Mqtt/MqttManage", method = RequestMethod.GET)
	public ModelAndView articleRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Mqtt/MqttManage");	
	}
	
	/**
	 * 建立發送訊息
	 * 
	 * @param userBean
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/Mqtt/CreateMessage", method = RequestMethod.POST)
	public ModelAndView createMessage(
			@ModelAttribute("userBean") UserBean userBean,
			@ModelAttribute MessageData messageData) {
		
		mqttService.createMessage(messageData);
		
		return new ModelAndView("/Mqtt/MqttManage");
	}
	
	@RequestMapping(value = "/Mqtt/ReadMessage")
	public @ResponseBody List<MessageData> readMessage() {
		List<MessageData> messageDataList = new ArrayList<MessageData>();
		
		try {
			messageDataList = mqttService.readMessageAll();
			
			if(messageDataList == null || messageDataList.size() == 0)
			{
				messageDataList = new ArrayList<MessageData>();
			}
			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return messageDataList;
	}
	
}
