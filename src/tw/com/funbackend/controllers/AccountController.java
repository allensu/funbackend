package tw.com.funbackend.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.JsonView;

import com.mchange.v2.c3p0.stmt.GooGooStatementCache;

import tw.com.funbackend.form.AccountLoginForm;
import tw.com.funbackend.pojo.UserBean;
import tw.com.funbackend.service.AccountService;



@SessionAttributes("userBean")
@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/Account/Index", method = RequestMethod.GET)
	public ModelAndView Index() {
		return new ModelAndView("/Account/Login");
	}
	
	@RequestMapping(value = "/Account/Login", method = RequestMethod.POST)
	public ModelAndView Login(@ModelAttribute("userBean") UserBean userBean, @ModelAttribute AccountLoginForm form) {
		
		
		//UserBean userBean = new UserBean(); 
		userBean.setAccountId(form.getAccount());
		//modelMap.addAttribute("userBean", userBean);
		//modelMap.put("userBean", userBean);
		return new ModelAndView("redirect:/controller/Home/Index");		
	}
	
	@RequestMapping(value = "/Account/TestJson")	
	public @ResponseBody ArrayList TestJson(HttpServletResponse response)
	{

		
		UserBean userBean = new UserBean(); 
		userBean.setAccountId("allensu");
		
		
		ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();

        Hashtable ht1 = new Hashtable();
        ht1.put("title", "分類群組一");
        Hashtable ht1_1 = new Hashtable();
        ht1_1.put("title", "功能一");
        ht1_1.put("url", "/ProductManage/ProductCard");
        ht1_1.put("id", "iProductCard");
        Hashtable ht1_2 = new Hashtable();
        ht1_2.put("title", "功能二");
        ht1_2.put("url", "/ProductManage/RSPointCard");
        ht1_2.put("id", "iRSPointCard");
        Hashtable ht1_3 = new Hashtable();
        ht1_3.put("title", "功能三");
        ht1_3.put("url", "/ProductManage/RSTimeCard");
        ht1_3.put("id", "iRSTimeCard");
        
        list1.add(ht1_1);
        list1.add(ht1_2);
        list1.add(ht1_3);
        ht1.put("content", list1);

        ArrayList list2 = new ArrayList();
        Hashtable ht2 = new Hashtable();
        ht2.put("title", "分類群組二");
        Hashtable ht2_1 = new Hashtable();
        ht2_1.put("title", "功能一");
        ht2_1.put("url", "/ItemManage/ItemManage");
        ht2_1.put("id", "iItemManage");
        Hashtable ht2_2 = new Hashtable();
        ht2_2.put("title", "功能二");
        ht2_2.put("url", "/ItemManage/ItemShare");
        ht2_2.put("id", "iItemShare");
        list2.add(ht2_1);
        list2.add(ht2_2);
        ht2.put("content", list2);

        ArrayList list3 = new ArrayList();
        Hashtable ht3 = new Hashtable();
        ht3.put("title", "分類群組三");
        Hashtable ht3_1 = new Hashtable();
        ht3_1.put("title", "功能一");
        ht3_1.put("url", "/ActiveKeyManage/ActiveKeyProductionMgr");
        ht3_1.put("id", "iActiveKeyProductionMgr");
        Hashtable ht3_2 = new Hashtable();
        ht3_2.put("title", "功能二");
        ht3_2.put("url", "/ActiveKeyManage/ActiveKeyTradeDown");
        ht3_2.put("id", "iActiveKeyTradeDown");
        list3.add(ht3_1);
        list3.add(ht3_2);
        ht3.put("content", list3);

        ArrayList list4 = new ArrayList();
        Hashtable ht4 = new Hashtable();
        ht4.put("title", "分類群組四");
        Hashtable ht4_1 = new Hashtable();
        ht4_1.put("title", "功能一");
        ht4_1.put("url", "/ECManage/PartnertabQuery");
        ht4_1.put("id", "iPartnertabQuery");
        Hashtable ht4_2 = new Hashtable();
        ht4_2.put("title", "功能二");
        ht4_2.put("url", "/ECManage/CreateAllinOneAccountBatch");
        ht4_2.put("id", "iCreateAllinOneAccountBatch");
        list4.add(ht4_1);
        list4.add(ht4_2);
        ht4.put("content", list4);

        Hashtable ht5 = new Hashtable();
        ht5.put("title", "帳號管理");
        ArrayList list5 = new ArrayList();
        Hashtable ht5_1 = new Hashtable();
        ht5_1.put("title", "使用者管理");
        ht5_1.put("url", "/ProductManage/STDProductionMgr");
        ht5_1.put("id", "iPaymentControl");
        list5.add(ht5_1);
        ht5.put("content", list5);

        list.add(ht1);
        list.add(ht2);
        list.add(ht3);
        list.add(ht4);
        list.add(ht5);
        
		
		return list;	    
	}
	
}
