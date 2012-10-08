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
import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.form.querycond.GraffitiWallCondition;
import tw.com.funbackend.form.result.GraffitiWallDataTableResult;
import tw.com.funbackend.form.tableschema.GraffitiWallTableSchema;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.GraffitiWallItem;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.ChatroomService;
import tw.com.funbackend.service.GraffitiWallService;

@SessionAttributes("userBean")
@Controller
@RequestMapping(value = "/GraffitiWall")
public class GraffitiWallController {

	protected Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private GraffitiWallService graffitiWallService;
	
	
	/**
	 * 塗鴉牆記錄查詢
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/GraffitiWallQuery", method = RequestMethod.GET)
	public ModelAndView chatroomMessageRecord(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/GraffitiWall/GraffitiWallQuery");	
	}
	
	/**
	 * 取得分頁塗鴉牆資料
	 * @return
	 */
	@RequestMapping(value = "/GraffitiWallQuery/ReadPages")
	public @ResponseBody GraffitiWallDataTableResult readPagesChatroom(
			@ModelAttribute GraffitiWallCondition qCondition,
			@ModelAttribute DataTableQueryParam tableParm) {
		
		GraffitiWallDataTableResult result = new GraffitiWallDataTableResult();
		List<GraffitiWallItem> graffitiWallDataList = new ArrayList<GraffitiWallItem>();
		
		try {
			// 排序處理
			String orderColName = GraffitiWallTableSchema.MapColumns[tableParm.getiSortCol_0()];
			int sortDir = OrderDirection.asc.toString().equals(tableParm.getsSortDir_0()) ? 1 : -1;
					

			if("".equals(orderColName))
				graffitiWallDataList = graffitiWallService.readGraffitiWallPageByCond(qCondition, tableParm.getiDisplayStart(), tableParm.getiDisplayLength());
			else 
				graffitiWallDataList = graffitiWallService.readGraffitiWallPageByCondSort(qCondition, tableParm.getiDisplayStart(), tableParm.getiDisplayLength(), orderColName, sortDir);
			
			if(graffitiWallDataList == null || graffitiWallDataList.size() == 0)
			{
				graffitiWallDataList = new ArrayList<GraffitiWallItem>();
			}
			
			int totalCount = graffitiWallService.readGraffitiWallCountByCond(qCondition);
			
			List<GraffitiWallTableSchema> graffitiWallDataTable = new ArrayList<GraffitiWallTableSchema>();
			
			for(GraffitiWallItem currData : graffitiWallDataList)
			{
				GraffitiWallTableSchema data = new GraffitiWallTableSchema();
				data.setId(currData.getId());
				data.setWallOwner(currData.getWallOwner());
				data.setPoster(currData.getPoster());
				data.setMessage(currData.getMessage());
				data.setTimeStamp(currData.getTimeStamp());
				data.setCategory(currData.getCategory());
				data.setLocation(currData.getLocation());
				graffitiWallDataTable.add(data);
			}
			
			result.setAaData(graffitiWallDataTable);
			result.setsEcho(tableParm.getsEcho());
			result.setiTotalDisplayRecords(totalCount);
			result.setiTotalRecords(totalCount);
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
	
	
	
}
