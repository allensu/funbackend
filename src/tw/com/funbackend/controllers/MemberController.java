package tw.com.funbackend.controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import tw.com.funbackend.service.FileService;

import tw.com.funbackend.enumeration.OrderDirection;
import tw.com.funbackend.form.MemberDataTableQueryParam;
import tw.com.funbackend.form.MemberDataTableSchema;
import tw.com.funbackend.form.MemberDataTableResult;
import tw.com.funbackend.form.SimpleResult;
import tw.com.funbackend.form.UploadResult;
import tw.com.funbackend.form.querycond.MemberDataQueryCondition;
import tw.com.funbackend.persistence.gopartyon.User;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.MemberService;

@SessionAttributes("userBean")
@Controller
@RequestMapping(value = "/Member")
public class MemberController {
	protected Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 會員黑名單
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/BlackMember", method = RequestMethod.GET)
	public ModelAndView blackMember(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/BlackMember");	
	}
	
	/**
	 * 會員基本資料查詢
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/MemberDataQuery", method = RequestMethod.GET)
	public ModelAndView memberDataQuery(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/MemberDataQuery");	
	}

	/**
	 * 取得所有User資料
	 * @return
	 */
	@RequestMapping(value = "/MemberDataQuery/Read")
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
	 * 取得分頁 User 資料
	 * @return
	 */
	@RequestMapping(value = "/MemberDataQuery/ReadPages")
	public @ResponseBody MemberDataTableResult readPagesMember(
			@ModelAttribute MemberDataQueryCondition qCondition,
			@ModelAttribute MemberDataTableQueryParam tableParm) {
//			@RequestParam(value="sEcho") String sEcho,
//			@RequestParam(value="iDisplayStart") int iDisplayStart,
//			@RequestParam(value="iDisplayLength") int iDisplayLength,
//			@RequestParam(value="iSortCol_0") int iSortCol_0,
//			@RequestParam(value="iSortingCols") int iSortingCols,
//			@RequestParam(value="sSortDir_0") String sSortDir_0) {
		
		MemberDataTableResult result = new MemberDataTableResult();
		List<User> userDataList = new ArrayList<User>();
		
		try {
			// 排序處理
			String orderColName = MemberDataTableSchema.MapColumns[tableParm.getiSortCol_0()];
			int sortDir = OrderDirection.asc.toString().equals(tableParm.getsSortDir_0()) ? 1 : -1;
					
			
			if("".equals(orderColName))
				userDataList = memberService.readUserPageByCond(qCondition, tableParm.getiDisplayStart(), tableParm.getiDisplayLength());
			else 
				userDataList = memberService.readUserPageByCondSort(qCondition, tableParm.getiDisplayStart(), tableParm.getiDisplayLength(), orderColName, sortDir);
			
			if(userDataList == null || userDataList.size() == 0)
			{
				userDataList = new ArrayList<User>();
			}
			
			int totalCount = memberService.readUserCountByCond(qCondition);
			
			List<MemberDataTableSchema> memberDataTable = new ArrayList<MemberDataTableSchema>();
			
			for(User currData : userDataList)
			{
				MemberDataTableSchema data = new MemberDataTableSchema();
				data.setId(currData.getId());
				data.setUserName(currData.getUserName());
				data.setDisplayName(currData.getDisplayName());
				data.setGender(currData.getGender());
				data.setCountryCode(currData.getCountryCode());
				data.setOnline(currData.isOnline());
				data.setFake(currData.isFake());
				data.setNumOfLikes(currData.getNumOfLikes());
				data.setMonthScore(currData.getMonthScore());
				data.setTotalScore(currData.getTotalScore());
				data.setRanking(currData.getRanking());
				memberDataTable.add(data);
			}
			
			result.setAaData(memberDataTable);
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
	

	/**
	 * 取得特定User資料
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/MemberDataQuery/Read/Id")
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
	@RequestMapping(value = "/MemberDataQuery/Update", method = RequestMethod.POST)
	public @ResponseBody User updateUser(
			@ModelAttribute("userBean") UserBean userBean,
			@ModelAttribute User userP, @RequestParam(value="updateFiald") String updateFiald) {

		System.out.println("Update Fiald:" + updateFiald);
		
		User userData = new User();
		
		try {
			// 取得原本的 User 資料
			userData = memberService.readUser(userP.getId());
			
			//大頭照
			if(updateFiald.contains("pic"))
				userData.setPic(userP.getPic());
			
			//顯示名稱
			if(updateFiald.contains("displayName"))
				userData.setDisplayName(userP.getDisplayName());
			
			//假帳號
			if(updateFiald.contains("fake"))
				userData.setFake(userP.isFake());
			
			//生日
			if(updateFiald.contains("birthday"))
				userData.setBirthday(userP.getBirthday());
			
			//信箱
			if(updateFiald.contains("email"))
				userData.setEmail(userP.getEmail());
			
			//電話號碼
			if(updateFiald.contains("phoneNo"))
				userData.setPhoneNo(userP.getPhoneNo());
			
			//國碼
			if(updateFiald.contains("countryCode"))
				userData.setCountryCode(userP.getCountryCode());
			
			//地址
			if(updateFiald.contains("address"))
				userData.setAddress(userP.getAddress());
			
			//贊數量
			if(updateFiald.contains("numOfLikes"))
				userData.setNumOfLikes(userP.getNumOfLikes());
			
			//排名
			if(updateFiald.contains("ranking"))
				userData.setRanking(userP.getRanking());
		
			//最後定位時間
			if(updateFiald.contains("locationDateTime"))
				userData.setLocationDateTime(userP.getLocationDateTime());
			
			//最後打卡地點名稱
			if(updateFiald.contains("placeName"))
				userData.setPlaceName(userP.getPlaceName());
			
			//興趣
			if(updateFiald.contains("interest"))
				userData.setInterest(userP.getInterest());
			
			//專長
			if(updateFiald.contains("profession"))
				userData.setProfession(userP.getProfession());
			
			//學校
			if(updateFiald.contains("school"))
				userData.setSchool(userP.getSchool());
			
			//自我介紹
			if(updateFiald.contains("description"))
				userData.setDescription(userP.getDescription());
			
			//更新時間
			if(updateFiald.contains("updateTime"))
				userData.setUpdateTime(userP.getUpdateTime());
			
			//月得分
			if(updateFiald.contains("monthScore"))
				userData.setMonthScore(userP.getMonthScore());
			
			//總得分
			if(updateFiald.contains("totalScore"))
				userData.setTotalScore(userP.getTotalScore());
			
			//排行榜上升或下降
			if(updateFiald.contains("rankingCompare"))
				userData.setRankingCompare(userP.getRankingCompare());
			
			//性別
			if(updateFiald.contains("gender"))
				userData.setGender(userP.getGender());

			//封存
			if(updateFiald.contains("deleted"))
				userData.setDeleted(userP.isDeleted());
			
			memberService.updateUser(userData);
			
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userData;
	}
	
	/**
	 * 取得特定User資料
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/MemberDataQuery/Album/Photos")
	public @ResponseBody List<Map<String, String>> getPhotos(@RequestParam("userName") String userName) {

		List<Map<String, String>> resultPhotos = new ArrayList<Map<String,String>>();
		
		try {
			User userData = memberService.readUserByUserName(userName);
			
			resultPhotos = userData.getPhotos();					
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return resultPhotos;
	}
	
	@RequestMapping(value = "/MemberDataQuery/Album/add")
	//public @ResponseBody SimpleResult addPhoto(@RequestParam("userName") String userName, @RequestParam("fileName") String fileName,
	//		@RequestParam("photofile") MultipartFile file) {
	public @ResponseBody SimpleResult addPhoto(@RequestParam("userName") String userName, HttpServletRequest request, HttpServletResponse response) 
	{
		
		SimpleResult result = new SimpleResult();
		result.setResultCode(0);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request ;
		CommonsMultipartFile cFile = (CommonsMultipartFile) multipartRequest.getFile("photofile");
			    
		System.out.println(cFile.getFileItem().getName());
		if (!cFile.isEmpty()) {
			try {
				
				User user = memberService.readUserByUserName(userName);
				
				byte[] bytes = cFile.getBytes();
				if (bytes.length > 0) {
					memberService.addPhotoToAlbum(user, cFile.getFileItem().getName(), bytes);
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				result.setResultCode(-1);
				result.setResultMessage("上傳檔案失敗");
			}

		} else {
			
			result.setResultCode(-1);
			result.setResultMessage("上傳檔案資料是空的");
		}

		return result;
	}
	
	/**
	 * 會員登入/出時間
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/MemberLoginRecord", method = RequestMethod.GET)
	public ModelAndView memberLoginRecord(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/MemberLoginRecord");	
	}
	
	/**
	 * 各地區會員數
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/MemberPlace", method = RequestMethod.GET)
	public ModelAndView memberPlace(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Member/MemberPlace");	
	}
}