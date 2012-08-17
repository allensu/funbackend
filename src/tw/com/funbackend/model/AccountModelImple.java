package tw.com.funbackend.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import tw.com.funbackend.config.FunBackendMongoConfig;
import tw.com.funbackend.enumeration.UserInfoCategory;
import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.MenuItem;
import tw.com.funbackend.persistence.UserInfo;

@Repository
public class AccountModelImple implements AccountModel {
	protected Logger logger = Logger.getLogger("model");
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(FunBackendMongoConfig.class);
    MongoOperations funBackendMongo = ctx.getBean(MongoOperations.class);
    
	@Autowired
	MongoOperations partyonMongo;

	@Override
	public UserInfo getUserInfo(String accountId) {
		
		UserInfo userInfo = null;
		
		try {
			userInfo = funBackendMongo.findOne(new Query(Criteria.where("accountId").is(accountId)), UserInfo.class);	
			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userInfo;
	}

	@Override
	public void updateUserLoginTime(String accountId, Date loginTime) {

		try {
			funBackendMongo.updateFirst(new Query(Criteria.where("accountId").is(accountId)), Update.update("lastLoginDateTime", loginTime), UserInfo.class);
			
			
			UserInfo userInfo = funBackendMongo.findOne(new Query(Criteria.where("accountId").is(accountId)), UserInfo.class);	
			
			
			SimpleDateFormat nowdate1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String sdate1 = nowdate1.format(userInfo.getLastLoginDateTime());
			logger.info(" model " + sdate1);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
	}
	
	@Override
	public void userLogout(String accountId) {
		// TODO Auto-generated method stub
		
		
		
		
	}
	
	@Override
	public UserInfo createUser(UserInfo userInfo) {
		
		
		
		funBackendMongo.save(userInfo);
	    
		return userInfo;
	}

	@Override
	public List<MenuGroup> getMenuList(String accountId) {

		List<MenuGroup> menuGroupListResult = new ArrayList<MenuGroup>();
		MenuGroup menuGroup1 = new MenuGroup();
		MenuGroup menuGroup2 = new MenuGroup();
		
		List<MenuItem> menuItemList1 = new ArrayList<MenuItem>();
		List<MenuItem> menuItemList2 = new ArrayList<MenuItem>();
		
		
		
		MenuItem menuItem1_1 = new MenuItem();
        menuItem1_1.setTitle("功能一");
        menuItem1_1.setUrl("/ProductManage/ProductCard");
        MenuItem menuItem1_2 = new MenuItem();
        menuItem1_2.setTitle("功能二");
        menuItem1_2.setUrl("/ProductManage/RSPointCard");
        MenuItem menuItem1_3 = new MenuItem();
        menuItem1_3.setTitle("功能三");
        menuItem1_3.setUrl("/ProductManage/RSTimeCard");
        menuItemList1.add(menuItem1_1);
        menuItemList1.add(menuItem1_2);
        menuItemList1.add(menuItem1_3);
        menuGroup1.setContent(menuItemList1);
        menuGroup1.setTitle("群組一");
        
        MenuItem menuItem2_1 = new MenuItem();
        menuItem2_1.setTitle("功能一");
        menuItem2_1.setUrl("/ProductManage/ProductCard");
        MenuItem menuItem2_2 = new MenuItem();
        menuItem2_2.setTitle("功能二");
        menuItem2_2.setUrl("/ProductManage/RSPointCard");
        menuItemList2.add(menuItem2_1);
        menuItemList2.add(menuItem2_2);
        menuGroup2.setContent(menuItemList2);
        menuGroup2.setTitle("群組二");
        
        menuGroupListResult.add(menuGroup1);
        menuGroupListResult.add(menuGroup2);
        

        
//        funBackendMongo.save(menuItem1_1);
//        funBackendMongo.save(menuItem1_2);
//        funBackendMongo.save(menuItem1_3);
//        funBackendMongo.save(menuItem2_1);
//        funBackendMongo.save(menuItem2_2);
//        funBackendMongo.save(menuGroup1);
//        funBackendMongo.save(menuGroup2);
        
        return menuGroupListResult;
	}

	@Override
	public List<UserInfo> readUserAll() {
		
		List<UserInfo> result = new ArrayList<UserInfo>();
		
		try {
			result = funBackendMongo.findAll(UserInfo.class);						
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		
		return result;
	}

	
	
	
}
