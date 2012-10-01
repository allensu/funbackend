package tw.com.funbackend.model;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import tw.com.funbackend.form.querycond.MemberDataQueryCondition;
import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.gopartyon.User;
import tw.com.funbackend.utility.StringUtility;


@Repository
public class MemberModelImpl implements MemberModel {
	protected Logger logger = Logger.getLogger("model");
	
	@Autowired
	MongoOperations partyonMongo;

	@Override
	public List<User> readUserAll() {

		List<User> result = new ArrayList<User>();
		
		try {
			if(partyonMongo.collectionExists(User.class))
			{
				result = partyonMongo.findAll(User.class);	
			}
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public User readUser(String id) {
		User result = null;
		
		try {
			Query query = new Query(where("id").is(id));
			
			result = partyonMongo.findOne(query, User.class);			
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public void updateUser(User user) {
		
		try {
			partyonMongo.save(user);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}

	}

	@Override
	public int readUserCount() {
		int count = 0;
		
		try 
		{
			DBCollection userColl = partyonMongo.getCollection(partyonMongo.getCollectionName(User.class));  
			BasicDBObject parameter = new BasicDBObject();  
			count = (int) userColl.count(parameter);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return count;
	}

	@Override
	public List<User> readUserPage(int startIndex, int length) {
		
		List<User> result = new ArrayList<User>();
		
		try 
		{
			result = partyonMongo.find(new Query().skip(startIndex).limit(length), User.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public int readUserCountByCond(MemberDataQueryCondition cond) {
				
		int count = 0;
		
		try 
		{
			DBCollection userColl = partyonMongo.getCollection(partyonMongo.getCollectionName(User.class));  
			BasicDBObject parameter = new BasicDBObject();  
			
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
				parameter.put("userName", cond.getUserNameQ());
			
			if(StringUtility.isNotEmpty(cond.getDisplayNameQ()))
				parameter.put("displayName", cond.getDisplayNameQ());
			
			if(StringUtility.isNotEmpty(cond.getGenderQ()))
			{
				if("All".equals(cond.getGenderQ()) == false)
					parameter.put("gender", cond.getGenderQ());
			}
			
			if(StringUtility.isNotEmpty(cond.getPhoneNoQ()))
				parameter.put("phoneNo", cond.getPhoneNoQ());
				
			count = (int) userColl.count(parameter);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return count;
		
	}

	@Override
	public List<User> readUserPageByCond(MemberDataQueryCondition cond,
			int startIndex, int length) {		

		List<User> result = new ArrayList<User>();
		
		try 
		{
			Criteria criteria = null;
	
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
				criteria = Criteria.where("userName").is(cond.getUserNameQ());
			
			if(StringUtility.isNotEmpty(cond.getDisplayNameQ()))
			{	
				if(criteria == null)
					criteria = Criteria.where("displayName").is(cond.getDisplayNameQ());
				else 
					criteria = criteria.and("displayName").is(cond.getDisplayNameQ());
			}
			
			if(StringUtility.isNotEmpty(cond.getGenderQ()))
			{
				if("All".equals(cond.getGenderQ()) == false)
				{
					if(criteria == null)
						criteria = Criteria.where("gender").is(cond.getGenderQ());
					else
						criteria = criteria.and("gender").is(cond.getGenderQ());	
				}
			}
			
			if(StringUtility.isNotEmpty(cond.getPhoneNoQ()))
			{	
				if(criteria == null)
					criteria = Criteria.where("phoneNo").is(cond.getPhoneNoQ());
				else 
					criteria = criteria.and("phoneNo").is(cond.getPhoneNoQ());
			}
			
			Query query = null;
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
			result = partyonMongo.find(query, User.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public List<User> readUserPageByCondSort(MemberDataQueryCondition cond,
			int startIndex, int length, String sortColName, int sortDir) {

		List<User> result = new ArrayList<User>();
		
		try 
		{
			Criteria criteria = null;
			
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
				criteria = Criteria.where("userName").is(cond.getUserNameQ());
			
			if(StringUtility.isNotEmpty(cond.getDisplayNameQ()))
			{	
				if(criteria == null)
					criteria = Criteria.where("displayName").is(cond.getDisplayNameQ());
				else 
					criteria = criteria.and("displayName").is(cond.getDisplayNameQ());
			}
			
			if(StringUtility.isNotEmpty(cond.getGenderQ()))
			{
				if("All".equals(cond.getGenderQ()) == false)
				{
					if(criteria == null)
						criteria = Criteria.where("gender").is(cond.getGenderQ());
					else
						criteria = criteria.and("gender").is(cond.getGenderQ());	
				}
			}
			 
			Query query = null;
			
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
			if(!"".equals(sortColName))
			{
				Order order = sortDir == 1 ? Order.ASCENDING : Order.DESCENDING;
				query.sort().on(sortColName, order);
			}
			
			result = partyonMongo.find(query, User.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
		
	}
	
	@Override
	public void deletePhotoFromAlbum(User user, String fileName) {
		try {
			Query query = new Query(where("userName").is(user.getUserName()));
			query.addCriteria(where("photos.photo").is(fileName));
			Update update = new Update();
			update.inc("numOfPic", -1);
			update.pull("photos", new BasicDBObject("photo", fileName));
			partyonMongo.updateFirst(query, update, User.class);
		} catch (Exception ex) {
			//e.printStackTrace();
			logger.error(ex.getMessage());
		}

	}

	@Override
	public void deletePhotoFromPic(User user, String fileName) {
		try {
			Query query = new Query(where("userName").is(user.getUserName()));
			query.addCriteria(where("pic").is(fileName));
			Update update = new Update();
			update.set("pic", "");
			partyonMongo.updateFirst(query, update, User.class);
		} catch (Exception ex) {
			//ex.printStackTrace();
			logger.error(ex.getMessage());
		}

	}

	@Override
	public void addPhotoToAlbum(User user, String fileName) {
		
		try {
			Map<String, String> data = new HashMap<String, String>(1);
			data.put("photo", fileName);
			Query query = new Query(where("userName").is(user.getUserName()));
			Update update = new Update();
			update.inc("numOfPic", 1);
			update.inc("filenameCount", 1);
			update.push("photos", data);
			partyonMongo.updateFirst(query, update, User.class);
		} catch (Exception ex) {
			//ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	@Override
	public User readUserByUserName(String userName) {
		User result = null;
		
		try {
			Query query = new Query(where("userName").is(userName));
			
			result = partyonMongo.findOne(query, User.class);			
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public List<User> readValidUser(boolean fake, boolean deleted) {

		List<User> result = new ArrayList<User>();
		
		try {
			if(partyonMongo.collectionExists(User.class))
			{
				//Query query = new Query(where("isFake").is(fake).and("isDeleted").is(deleted));
				
				//result = partyonMongo.find(query, User.class);
				result = partyonMongo.findAll(User.class);
			}
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;			
	}


	
}
