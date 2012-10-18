package tw.com.funbackend.controllers;

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

import tw.com.funbackend.form.tableschema.BlockUserRankTableSchema;
import tw.com.funbackend.form.tableschema.GraffitiWallRankTableSchema;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.GraffitiWallService;
import tw.com.funbackend.service.MemberService;

@SessionAttributes("userBean")
@Controller
@RequestMapping(value = "/Rank")
public class RankController {
	protected Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GraffitiWallService graffitiWallService;
	
	/**
	 * 會員黑名單
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/BlockUserRank", method = RequestMethod.GET)
	public ModelAndView blockUserRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Rank/BlockUserRank");	
	}
	
	/**
	 * Po文排行
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/ArticleRank", method = RequestMethod.GET)
	public ModelAndView articleRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Rank/ArticleRank");	
	}
	
	/**
	 * 最多贊會員排行
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/LikeRank", method = RequestMethod.GET)
	public ModelAndView likeRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Rank/LikeRank");	
	}

	/**
	 * 上傳圖片排行
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/UploadPicRank", method = RequestMethod.GET)
	public ModelAndView uploadPicRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Rank/UploadPicRank");	
	}
	
	/**
	 * 取得會員黑名單
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/BlockUserRank/Read", method = RequestMethod.GET)
	public @ResponseBody List<BlockUserRankTableSchema> getBlockUserRank(@ModelAttribute("userBean") UserBean userBean) {
		
		List<BlockUserRankTableSchema> result = memberService.getBlockUserRank();
		
		
		return result;
	}
	
	/**
	 * 取得Po文排行榜
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/GraffitiWallRank/Read", method = RequestMethod.GET)
	public @ResponseBody List<GraffitiWallRankTableSchema> getGraffitiWallRank(@ModelAttribute("userBean") UserBean userBean) {
		
		List<GraffitiWallRankTableSchema> result = graffitiWallService.readGraffitiWallRanking();
		
		
		return result;
	}
}
