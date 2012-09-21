package tw.com.funbackend.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.utility.StringUtility;

@Repository
public class ChatroomModelImpl implements ChatroomModel {
	protected Logger logger = Logger.getLogger("model");
	
	@Autowired
	MongoOperations partyonMongo;

	@Override
	public int readChatroomCountByCond(ChatroomMessageRecordCondition cond) {

		int count = 0;
		
		try 
		{
			DBCollection chatroomColl = partyonMongo.getCollection(partyonMongo.getCollectionName(Chatroom.class));  
			BasicDBObject parameter = new BasicDBObject();  
			
			if("All".equals(cond.getChatRoomStyleQ()) == false)
				parameter.put("chatRoomStyle", cond.getChatRoomStyleQ());
			
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
				parameter.put("userName", cond.getUserNameQ());
			
			count = (int) chatroomColl.count(parameter);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return count;
	}

	@Override
	public List<Chatroom> readChatroomPageByCond(
			ChatroomMessageRecordCondition cond, int startIndex, int length) {

		List<Chatroom> result = new ArrayList<Chatroom>();
		
		try 
		{
			Criteria criteria = null;
	
			if("All".equals(cond.getChatRoomStyleQ()) == false)
				criteria = Criteria.where("chatRoomStyle").is(cond.getChatRoomStyleQ());
			
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
				criteria = Criteria.where("userName").is(cond.getUserNameQ());
			
			Query query = null;
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
			result = partyonMongo.find(query, Chatroom.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public List<Chatroom> readChatroomPageByCondSort(
			ChatroomMessageRecordCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {

		List<Chatroom> result = new ArrayList<Chatroom>();
		
		try 
		{
			Criteria criteria = null;
			
			if("All".equals(cond.getChatRoomStyleQ()) == false)
				criteria = Criteria.where("chatRoomStyle").is(cond.getChatRoomStyleQ());
					
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
				criteria = Criteria.where("userName").is(cond.getUserNameQ());
			
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
			
			result = partyonMongo.find(query, Chatroom.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
		
	}
	
	
}
