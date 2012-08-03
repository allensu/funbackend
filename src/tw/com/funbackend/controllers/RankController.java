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
public class RankController {
	
	/**
	 * Po文排行
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Rank/ArticleRank", method = RequestMethod.GET)
	public ModelAndView articleRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Rank/ArticleRank");	
	}
	
	/**
	 * 最多贊會員排行
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Rank/LikeRank", method = RequestMethod.GET)
	public ModelAndView likeRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Rank/LikeRank");	
	}

	/**
	 * 上傳圖片排行
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = "/Rank/UploadPicRank", method = RequestMethod.GET)
	public ModelAndView uploadPicRank(@ModelAttribute("userBean") UserBean userBean) {
		return new ModelAndView("/Rank/UploadPicRank");	
	}
}
