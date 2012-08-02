package tw.com.funbackend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tw.com.funbackend.pojo.UserBean;

public class LoginVerificationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String pathInfo = request.getPathInfo();
		
		if(pathInfo.equals("/Account/Index") || pathInfo.equals("/Account/Login"))
		{
			return super.preHandle(request, response, handler);
		} else {
			UserBean userBean = (UserBean)request.getSession().getAttribute("userBean");
			
			 if(userBean == null || userBean.getAccountId() == "")
			 {
				 //request.getRequestDispatcher("/controller/Account/Index").forward(request, response);
				 response.sendRedirect("/funbackend/controller/Account/Index");
				 return false;
			 } else {
				 
				 return super.preHandle(request, response, handler);
			 }
		}
	}
	
//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		
//		
//		
//		
//		
//		
//		
//		
//		super.postHandle(request, response, handler, modelAndView);
//	}
}
