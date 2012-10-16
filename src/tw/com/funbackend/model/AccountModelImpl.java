package tw.com.funbackend.model;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import tw.com.funbackend.config.FunBackendMongoConfig;
import tw.com.funbackend.enumeration.UserInfoCategory;
import tw.com.funbackend.form.querycond.ManageMenuAuthCondition;
import tw.com.funbackend.persistence.MenuAuth;
import tw.com.funbackend.persistence.MenuGroup;
import tw.com.funbackend.persistence.MenuItem;
import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.persistence.gopartyon.GraffitiWallItem;
import tw.com.funbackend.persistence.gopartyon.User;
import tw.com.funbackend.utility.StringUtility;

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
	public List<MenuGroup> getMenuList(UserInfo userInfo) {

		List<MenuGroup> menuGroupListResult = new ArrayList<MenuGroup>();

		menuGroupListResult = funBackendMongo.findAll(MenuGroup.class);	
		
		if(menuGroupListResult == null || menuGroupListResult.size() == 0)
		{
			if(userInfo.getCategory() != UserInfoCategory.Admin)
				return menuGroupListResult;
			
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
	        MenuItem menuItem1_8 = new MenuItem();
	        menuItem1_8.setTitle("塗鴉牆查詢");
	        menuItem1_8.setId("GraffitWallQuery");
	        menuItem1_8.setUrl("/funbackend/controller/GraffitiWall/GraffitiWallQuery");
	        
	        menuItemList1.add(menuItem1_1);
	        menuItemList1.add(menuItem1_2);
	        menuItemList1.add(menuItem1_3);
	        menuItemList1.add(menuItem1_4);
	        menuItemList1.add(menuItem1_5);
	        menuItemList1.add(menuItem1_6);
	        menuItemList1.add(menuItem1_7);
	        menuItemList1.add(menuItem1_8);
	        
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
	        MenuItem menuItem2_3 = new MenuItem();
	        menuItem2_3.setTitle("功能權限管理");
	        menuItem2_3.setId("ManageMenuAuth");
	        menuItem2_3.setUrl("/funbackend/controller/Account/ManageMenuAuth");
	        menuItemList2.add(menuItem2_3);
	        
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
	        funBackendMongo.save(menuItem1_8);
	        funBackendMongo.save(menuItem2_1);
	        funBackendMongo.save(menuItem2_2);
	        funBackendMongo.save(menuItem2_3);
	        funBackendMongo.save(menuGroup1);
	        funBackendMongo.save(menuGroup2);
	        
	        
	        List<MenuItem> menuItemList = getMenuItemListAll();
			
			for(MenuItem currData : menuItemList)
			{
				MenuAuth menuAuth = new MenuAuth();
				menuAuth.setUserInfo(userInfo);
				
				menuAuth.setEnabled(true);
				menuAuth.setNewAuth(true);
				menuAuth.setUpdateAuth(true);
				menuAuth.setDeleteAuth(true);
				menuAuth.setQueryAuth(true);
				 
				menuAuth.setMenuItem(currData);
				createMenuAuth(menuAuth);
			}
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
	public boolean removeUserInfo(String id) {
		
		try {
			//for(String currId : ids)
			//{
				Query query = new Query(where("id").is(id));
				//Query query = new Query(where("accountId").is("allensu"));
				funBackendMongo.remove(query, UserInfo.class);
			//}
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return true;
	}

	@Override
	public boolean createMenuGroup(MenuGroup menuGroup) {
		
		boolean result = false;
		
		try 
		{
			funBackendMongo.save(menuGroup);
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public boolean createMenuItem(MenuItem menuItem) {
		
		boolean result = false;
		
		try 
		{
			funBackendMongo.save(menuItem);
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public MenuGroup getMenuGroup(String groupId) {
		
		MenuGroup result = new MenuGroup();
		
		try {
			result = funBackendMongo.findOne(new Query(Criteria.where("id").is(groupId)), MenuGroup.class);
			
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
			
		return result;
	}

	@Override
	public boolean removeMenuItem(String itemId) {
	
		boolean result = false;
		
		try 
		{			
			Query query = new Query(where("id").is(itemId));
			funBackendMongo.remove(query, MenuItem.class);
			
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public boolean removeMenuGroup(String groupId) {
		
		boolean result = false;
		
		try 
		{			
			Query query = new Query(where("id").is(groupId));
			funBackendMongo.remove(query, MenuGroup.class);
			
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public boolean updateMenuGroup(MenuGroup menuGroup) {
		boolean result = false;
		
		try 
		{
			funBackendMongo.save(menuGroup);
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public boolean updateMenuItem(MenuItem menuItem) {
		boolean result = false;
		
		try 
		{
			funBackendMongo.save(menuItem);
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public boolean removeMenuGroupRefContent(String itemId) {
		// db.menuGroup.update({}, {$pull:{'content':{'$id':'test11'}}}, false, true)
		boolean result = false;
		
		try 
		{
			DBCollection coll = funBackendMongo.getCollection(funBackendMongo.getCollectionName(MenuGroup.class));  			
			BasicDBObject match = new BasicDBObject();
			BasicDBObject update = new BasicDBObject("content", new BasicDBObject("$id", itemId));
			coll.update(match, new BasicDBObject("$pull", update), false, true);
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
	
		
		return result;
	}

	@Override
	public boolean removeMenuGroupRefContent(String groupId, String itemId) {
		// db.menuGroup.update({'_id':new Object('jksdf')}, {$pull:{'content':{'$id':'test11'}}}, false, true)
		boolean result = false;
		
		try 
		{
			
			//DBCollection coll = funBackendMongo.getCollection(funBackendMongo.getCollectionName(MenuGroup.class));  			
			//BasicDBObject match = new BasicDBObject("_id", groupId);
			
			Query query = new Query(where("id").is(groupId));
			//Update update = new Update();
			
			Update update = new Update().pull("content", new BasicDBObject("$id", itemId));
			
			funBackendMongo.upsert(query, update, MenuGroup.class);
			
			
			//BasicDBObject update = new BasicDBObject("content", new BasicDBObject("$id", itemId));
			//coll.update(match, new BasicDBObject("$pull", update));
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
	
		
		return result;
	}

	@Override
	public int readMenuAuthCountByCond(ManageMenuAuthCondition cond) {
		int count = 0;
		
		try 
		{
			DBCollection menuAuthColl = funBackendMongo.getCollection(funBackendMongo.getCollectionName(MenuAuth.class));  
			BasicDBObject parameter = new BasicDBObject();  
						
//			if("All".equals(cond.getCategoryQ()) == false)
//				parameter.put("category", cond.getCategoryQ());
			
			if(StringUtility.isNotEmpty(cond.getAccountIdQ()))
			{
				UserInfo userInfo = getUserInfo(cond.getAccountIdQ());
				if(userInfo == null)
					parameter.put("userInfo.$id", "");
				else
					parameter.put("userInfo.$id", new ObjectId(userInfo.getId()));
			}
			
			if(StringUtility.isNotEmpty(cond.getMenuTitleQ()))
			{
				MenuItem menuItem = getMenuitemByTitle(cond.getMenuTitleQ());
				if(menuItem == null)
					parameter.put("menuItem.$id", "");
				else 
					parameter.put("menuItem.$id", menuItem.getId());							
			}
			
			count = (int) menuAuthColl.count(parameter);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return count;
	}
	
	@Override
	public List<MenuAuth> readMenuAuthByUserInfoId(String userInfoId) {
		
		List<MenuAuth> menuAuthList = new ArrayList<MenuAuth>();
		
		try 
		{
			Criteria criteria = new Criteria();
			criteria = Criteria.where("userInfo.$id").is(new ObjectId(userInfoId));
			Query query = new Query(criteria);
			menuAuthList = funBackendMongo.find(query, MenuAuth.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
			
		return menuAuthList;
	}

	@Override
	public List<MenuAuth> readMenuAuthPageByCondSort(
			ManageMenuAuthCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {
		List<MenuAuth> result = new ArrayList<MenuAuth>();
		List<MenuAuth> resultFinal = new ArrayList<MenuAuth>();
		
		try 
		{
			Criteria criteria = new Criteria();
	
			
			
//			if("All".equals(cond.getCategoryQ()) == false)
//				criteria = Criteria.where("category").is(cond.getCategoryQ());
//		
			if(StringUtility.isNotEmpty(cond.getAccountIdQ()))
			{
				UserInfo userInfo = getUserInfo(cond.getAccountIdQ());
				if(userInfo == null)
					criteria = Criteria.where("userInfo.$id").is("");
				else if(criteria == null)
					criteria = Criteria.where("userInfo.$id").is(new ObjectId(userInfo.getId()));
				else 
					criteria = criteria.and("userInfo.$id").is(new ObjectId(userInfo.getId()));
			}
			
			if(StringUtility.isNotEmpty(cond.getMenuTitleQ()))
			{
				MenuItem menuItem = getMenuitemByTitle(cond.getMenuTitleQ());
				if(menuItem == null)
					criteria = criteria.and("menuItem.$id").is("");
				else 
					criteria = criteria.and("menuItem.$id").is(menuItem.getId());							
			}
			
			Query query = null;
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
//			if(!"".equals(sortColName))
//			{
//				Order order = sortDir == 1 ? Order.ASCENDING : Order.DESCENDING;
//				query.sort().on(sortColName, order);
//			}
			
			result = funBackendMongo.find(query, MenuAuth.class);
			resultFinal = result;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return resultFinal;
	}

	@Override
	public MenuAuth createMenuAuth(MenuAuth menuAuth) {
		
		funBackendMongo.save(menuAuth);
		
		return menuAuth;
	}

	@Override
	public List<MenuItem> getMenuItemListAll() {
		List<MenuItem> result = new ArrayList<MenuItem>();
		
		try {
			result = funBackendMongo.findAll(MenuItem.class);
			
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
			
		return result;
	}

	@Override
	public boolean updateMenuAuth(MenuAuth menuAuth) {
		boolean result = false;
		
		try 
		{
			funBackendMongo.save(menuAuth);
			result = true;
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
				
		return result;
	}

	@Override
	public MenuAuth getMenuAuth(String menuAuthId) {
		MenuAuth result = new MenuAuth();
		
		try {
			result = funBackendMongo.findOne(new Query(Criteria.where("id").is(menuAuthId)), MenuAuth.class);
			
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
			
		return result;
	}

	@Override
	public List<UserInfo> getUserInfoAll() {
		List<UserInfo> result = new ArrayList<UserInfo>();
		
		try {
			result = funBackendMongo.findAll(UserInfo.class);
			
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
			
		return result;
	}

	@Override
	public UserInfo getUserInfoByAccountName(String accountName) {
		UserInfo userInfo = null;
		
		try {
			userInfo = funBackendMongo.findOne(new Query(Criteria.where("accountName").is(accountName)), UserInfo.class);	
			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return userInfo;
	}

	@Override
	public MenuItem getMenuitemByTitle(String title) {
		
		MenuItem menuItem = null;
		
		try {
		
			menuItem = funBackendMongo.findOne(new Query(Criteria.where("title").is(title)), MenuItem.class);	
			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return menuItem;
	}

	@Override
	public boolean deleteMenuAuthByUserInfoId(String userInfoId) {
		
		boolean result = false;
		
		try 
		{
			Query query = new Query(where("userInfo.$id").is(new ObjectId(userInfoId)));
			funBackendMongo.remove(query, MenuAuth.class);
					
			return result;
		}
		catch (Exception ex) 
		{
			logger.error(ex.getMessage());
		}
		
		return false;
	}

	@Override
	public MenuAuth getMenuAuth(String userInfoId, String menuItemId) {
		
		MenuAuth result = new MenuAuth();
		
		try {
			Criteria criteria = new Criteria();
			criteria = Criteria.where("userInfo.$id").is(new ObjectId(userInfoId));
			criteria = criteria.and("menuItem.$id").is(menuItemId);										
			Query query = new Query(criteria);				
			result = funBackendMongo.findOne(query, MenuAuth.class);
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
			
		return result;
	}	
	
	
}
