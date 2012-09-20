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

import tw.com.funbackend.enumeration.OrderDirection;
import tw.com.funbackend.form.ChatroomDataTableResult;
import tw.com.funbackend.form.ChatroomDataTableSchema;
import tw.com.funbackend.form.DataTableQueryParam;
import tw.com.funbackend.form.MemberDataTableResult;
import tw.com.funbackend.form.MemberDataTableSchema;
import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.form.querycond.MemberDataQueryCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.User;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.ChatroomService;
import tw.com.funbackend.service.MemberService;

@SessionAttributes("userBean")
@Controller
@RequestMapping(value = "/Chatroom")
public class ChatroomController {
	
	protected Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private ChatroomService chatroomService;
	
	/**
	 * 聊天室記錄查詢
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/ChatroomMessageRecord", method = RequestMethod.GET)
	public ModelAndView chatroomMessageRecord(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Chatroom/ChatroomMessageRecord");	
	}
	
	/**
	 * 取得分頁聊天室資料
	 * @return
	 */
	@RequestMapping(value = "/ChatroomMessageRecord/ReadPages")
	public @ResponseBody ChatroomDataTableResult readPagesChatroom(
			@ModelAttribute ChatroomMessageRecordCondition qCondition,
			@ModelAttribute DataTableQueryParam tableParm) {
		
		ChatroomDataTableResult result = new ChatroomDataTableResult();
		List<Chatroom> chatroomDataList = new ArrayList<Chatroom>();
		
		try {
			// 排序處理
			String orderColName = ChatroomDataTableSchema.MapColumns[tableParm.getiSortCol_0()];
			int sortDir = OrderDirection.asc.toString().equals(tableParm.getsSortDir_0()) ? 1 : -1;
					

			if("".equals(orderColName))
				chatroomDataList = chatroomService.readChatroomPageByCond(qCondition, tableParm.getiDisplayStart(), tableParm.getiDisplayLength());
			else 
				chatroomDataList = chatroomService.readChatroomPageByCondSort(qCondition, tableParm.getiDisplayStart(), tableParm.getiDisplayLength(), orderColName, sortDir);
			
			if(chatroomDataList == null || chatroomDataList.size() == 0)
			{
				chatroomDataList = new ArrayList<Chatroom>();
			}
			
			int totalCount = chatroomService.readChatroomCountByCond(qCondition);
			
			List<ChatroomDataTableSchema> chatroomDataTable = new ArrayList<ChatroomDataTableSchema>();
			
			for(Chatroom currData : chatroomDataList)
			{
				ChatroomDataTableSchema data = new ChatroomDataTableSchema();
				data.setId(currData.getId());
				data.setChatRoomStyle(currData.getChatRoomStyle());
				data.setNumOfUser(currData.getNumOfUser());
				chatroomDataTable.add(data);
			}
			
			result.setAaData(chatroomDataTable);
			result.setsEcho(tableParm.getsEcho());
			result.setiTotalDisplayRecords(totalCount);
			result.setiTotalRecords(totalCount);
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		logger.info("return readMember");
		
		return result;
	}
}
