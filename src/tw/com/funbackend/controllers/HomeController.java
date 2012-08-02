package tw.com.funbackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tw.com.funbackend.pojo.UserBean;

@SessionAttributes("userBean")
@Controller
public class HomeController {
	

	
	@RequestMapping(value = "/Home/Index", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("userBean") UserBean userBean) {
	//public ModelAndView Index(ModelMap modelMap) {
		//UserBean userBean = (UserBean)modelMap.get("userBean");
		
		if(userBean != null && !"".equals(userBean.getAccountId()) && userBean.getAccountId() != null)
		{
			return new ModelAndView("Home/Index");	
		}
		else 
		{
			return new ModelAndView("redirect:/controller/Account/Index");	
		}
		
		
	}
}
