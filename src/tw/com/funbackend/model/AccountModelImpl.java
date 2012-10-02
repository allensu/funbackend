package tw.com.funbackend.model;

import static org.springframework.data.mongodb.core.query.Criteria.where;

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
import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.UserInfo;

@Repository
public class AccountModelImpl implements AccountModel {
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
		
		menuGroupListResult = funBackendMongo.findAll(MenuGroup.class);	
		
		if(menuGroupListResult == null || menuGroupListResult.size() == 0)
		{
			MenuGroup menuGroup1 = new MenuGroup();
			MenuGroup menuGroup2 = new MenuGroup();
			
			List<MenuItem> menuItemList1 = new ArrayList<MenuItem>();
			List<MenuItem> menuItemList2 = new ArrayList<MenuItem>();
			
			MenuItem menuItem1_1 = new MenuItem();
	        menuItem1_1.setTitle("會員基本資料查詢");
	        menuItem1_1.setId("MemberDataQuery");
	        menuItem1_1.setUrl("/funbackend/controller/Member/MemberDataQuery");
	        MenuItem menuItem1_2 = new MenuItem();
	        menuItem1_2.setTitle("黑名單排行榜");
	        menuItem1_2.setId("BlockUserRank");
	        menuItem1_2.setUrl("/funbackend/controller/Rank/BlockUserRank");
	        MenuItem menuItem1_3 = new MenuItem();
	        menuItem1_3.setTitle("會員登入/出時間");
	        menuItem1_3.setId("MemberLoginRecord");
	        menuItem1_3.setUrl("/funbackend/controller/Member/MemberLoginRecord");
	        MenuItem menuItem1_4 = new MenuItem();
	        menuItem1_4.setTitle("各地區會員數");
	        menuItem1_4.setId("MemberPlace");
	        menuItem1_4.setUrl("/funbackend/controller/Member/MemberPlace");
	        MenuItem menuItem1_5 = new MenuItem();
	        menuItem1_5.setTitle("聊天室記錄查詢");
	        menuItem1_5.setId("ChatroomMessageRecord");
	        menuItem1_5.setUrl("/funbackend/controller/Chatroom/ChatroomMessageRecord");
	        MenuItem menuItem1_6 = new MenuItem();
	        menuItem1_6.setTitle("Po文排行");
	        menuItem1_6.setId("ArticleRank");
	        menuItem1_6.setUrl("/funbackend/controller/Rank/ArticleRank");
	        MenuItem menuItem1_7 = new MenuItem();
	        menuItem1_7.setTitle("MQTT資料管理");
	        menuItem1_7.setId("MqttManage");
	        menuItem1_7.setUrl("/funbackend/controller/Mqtt/MqttManage");
	        
	        menuItemList1.add(menuItem1_1);
	        menuItemList1.add(menuItem1_2);
	        menuItemList1.add(menuItem1_3);
	        menuItemList1.add(menuItem1_4);
	        menuItemList1.add(menuItem1_5);
	        menuItemList1.add(menuItem1_6);
	        menuItemList1.add(menuItem1_7);
	        
	        menuGroup1.setContent(menuItemList1);
	        menuGroup1.setTitle("FunCube");
	        
	        MenuItem menuItem2_1 = new MenuItem();
	        menuItem2_1.setTitle("帳號管理");
	        menuItem2_1.setId("ManageUser");
	        menuItem2_1.setUrl("/funbackend/controller/Account/ManageUser");
	        menuItemList2.add(menuItem2_1);
	        MenuItem menuItem2_2 = new MenuItem();
	        menuItem2_2.setTitle("功能群組管理");
	        menuItem2_2.setId("ManageMenu");
	        menuItem2_2.setUrl("/funbackend/controller/Account/ManageMenu");
	        menuItemList2.add(menuItem2_2);
	        menuGroup2.setContent(menuItemList2);
	        menuGroup2.setTitle("系統管理");
	        
	        menuGroupListResult.add(menuGroup1);
	        menuGroupListResult.add(menuGroup2);

	        funBackendMongo.save(menuItem1_1);
	        funBackendMongo.save(menuItem1_2);
	        funBackendMongo.save(menuItem1_3);
	        funBackendMongo.save(menuItem1_4);
	        funBackendMongo.save(menuItem1_5);
	        funBackendMongo.save(menuItem1_6);
	        funBackendMongo.save(menuItem1_7);
	        funBackendMongo.save(menuItem2_1);
	        funBackendMongo.save(menuItem2_2);
	        funBackendMongo.save(menuGroup1);
	        funBackendMongo.save(menuGroup2);
		}
        
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

	@Override
	public boolean removeUserInfo(List<String> ids) {
		
		try {
			for(String currId : ids)
			{
				Query query = new Query(where("id").is(currId));
				//Query query = new Query(where("accountId").is("allensu"));
				funBackendMongo.remove(query, UserInfo.class);
			}
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return true;
	}

	
	
	
}
