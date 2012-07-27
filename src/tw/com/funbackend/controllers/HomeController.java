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
	
	@ModelAttribute("userBean")
	public UserBean initUserBean() {
	   return new UserBean(); // populates form for the first time if its null
	}
	
//	@RequestMapping(value = "/Home/IndexCreateSession", method = RequestMethod.GET)
//	public ModelAndView IndexCreateSession(ModelMap model)
//	{
//		UserBean userBean = new UserBean(); 
//		model.put("userBean", userBean);
//		
//		return new ModelAndView("redirect:/controller/Home/Index");	
//	}
	
	@RequestMapping(value = "/Home/Index", method = RequestMethod.GET)
	public ModelAndView Index(@ModelAttribute("userBean") UserBean userBean) {
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
