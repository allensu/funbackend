package tw.com.funbackend.model;

import static org.springframework.data.mongodb.core.query.Criteria.where;

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
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import tw.com.funbackend.form.querycond.ChatroomMessageCondition;
import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.ChatroomMessage;
import tw.com.funbackend.persistence.gopartyon.User;
import tw.com.funbackend.utility.GeoUtility;
import tw.com.funbackend.utility.StringUtility;
import tw.com.gopartyon.mqtt.MessageData;

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
				parameter.put("users", cond.getUserNameQ());
			
			if("geo".equals(cond.getChatRoomStyleQ()) && cond.getKilometerQ() != "")
			{
				DBCursor dbCursor = chatroomColl.find(parameter);
				
				double lon = 0;
				double lat = 0;
				double distance = 0;
				GeoUtility geoUtility = new GeoUtility();
				while(dbCursor.hasNext())
				{
					DBObject dbObj = dbCursor.next();
					
					lon = Double.parseDouble(String.valueOf(((DBObject)dbObj.get("location")).get("lon")));
					lat = Double.parseDouble(String.valueOf(((DBObject)dbObj.get("location")).get("lat")));
					
					distance = geoUtility.GetDistance(lat, lon, 
							Double.parseDouble(cond.getLatitudeQ()), 
							Double.parseDouble(cond.getLongitudeQ()));

					if(distance <= Double.parseDouble(cond.getKilometerQ()))
					{
						count++;
					}
				}
			} else {
				count = (int) chatroomColl.count(parameter);
			}
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
		List<Chatroom> resultFinal = new ArrayList<Chatroom>();
		
		try 
		{
			Criteria criteria = null;
	
			if("All".equals(cond.getChatRoomStyleQ()) == false)
				criteria = Criteria.where("chatRoomStyle").is(cond.getChatRoomStyleQ());
			
			
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("users").is(cond.getUserNameQ());
				else 
					criteria = criteria.and("users").is(cond.getUserNameQ());
			}
			
			Query query = null;
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
			result = partyonMongo.find(query, Chatroom.class);
			
			if("geo".equals(cond.getChatRoomStyleQ()) && cond.getKilometerQ() != "")
			{
				double lon = 0;
				double lat = 0;
				double distance = 0;
				GeoUtility geoUtility = new GeoUtility();
				for(Chatroom currChatroom : result)
				{
					lon = currChatroom.getLocation().get("lon");
					lat = currChatroom.getLocation().get("lat");
					distance = geoUtility.GetDistance(lat, lon, 
							Double.parseDouble(cond.getLatitudeQ()), 
							Double.parseDouble(cond.getLongitudeQ()));
					
					if(distance <= Double.parseDouble(cond.getKilometerQ()))
					{
						resultFinal.add(currChatroom);
					}
				}					
			} else {
				resultFinal = result;
			}
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return resultFinal;
	}

	@Override
	public List<Chatroom> readChatroomPageByCondSort(
			ChatroomMessageRecordCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {

		List<Chatroom> result = new ArrayList<Chatroom>();
		List<Chatroom> resultFinal = new ArrayList<Chatroom>();
		
		try 
		{
			Criteria criteria = null;
			
			if("All".equals(cond.getChatRoomStyleQ()) == false)
				criteria = Criteria.where("chatRoomStyle").is(cond.getChatRoomStyleQ());
					
			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("users").is(cond.getUserNameQ());
				else 
					criteria = criteria.and("users").is(cond.getUserNameQ());
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
			
			result = partyonMongo.find(query, Chatroom.class);
			
			if("geo".equals(cond.getChatRoomStyleQ()) && cond.getKilometerQ() != "")
			{
				double lon = 0;
				double lat = 0;
				double distance = 0;
				GeoUtility geoUtility = new GeoUtility();
				for(Chatroom currChatroom : result)
				{
					lon = currChatroom.getLocation().get("lon");
					lat = currChatroom.getLocation().get("lat");
					distance = geoUtility.GetDistance(lat, lon, 
							Double.parseDouble(cond.getLatitudeQ()), 
							Double.parseDouble(cond.getLongitudeQ()));
					
					if(distance <= Double.parseDouble(cond.getKilometerQ()))
					{
						resultFinal.add(currChatroom);
					}
				}					
			} else {
				resultFinal = result;
			}
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return resultFinal;
		
	}

	@Override
	public Chatroom readChatroom(String id) {

		Chatroom result = null;
		
		try {
			Query query = new Query(where("id").is(id));
			
			result = partyonMongo.findOne(query, Chatroom.class);			
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public List<ChatroomMessage> readChatroomMessage(String chatroomId) {
		
		List<ChatroomMessage> result = new ArrayList<ChatroomMessage>();
		
		try {
			Query query = new Query(where("chatroomId"));
			
			result = partyonMongo.find(query, ChatroomMessage.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public int readChatroomMessageCountByCond(ChatroomMessageCondition cond) {
		int count = 0;
		
		try 
		{
			DBCollection chatroomMessageColl = partyonMongo.getCollection(partyonMongo.getCollectionName(ChatroomMessage.class));  
			BasicDBObject parameter = new BasicDBObject();  
			
			if(StringUtility.isNotEmpty(cond.getChatroomIdQ()))
				parameter.put("chatroomId", cond.getChatroomIdQ());
			
			if(StringUtility.isNotEmpty(cond.getReceiverNameQ()))
				parameter.put("userName", cond.getReceiverNameQ());

			if(StringUtility.isNotEmpty(cond.getSenderNameQ()))
				parameter.put("senderName", cond.getSenderNameQ());
			
			if("All".equals(cond.getTypeQ()) == false)
				parameter.put("type", cond.getTypeQ());
			
			count = (int) chatroomMessageColl.count(parameter);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return count;
	}

	@Override
	public List<ChatroomMessage> readChatroomMessagePageByCond(ChatroomMessageCondition cond,
			int startIndex, int length) {
		
		List<ChatroomMessage> result = new ArrayList<ChatroomMessage>();
		
		try 
		{
			Criteria criteria = null;
	
			if(StringUtility.isNotEmpty(cond.getChatroomIdQ()))
			{
				criteria = Criteria.where("chatroomId").is(cond.getChatroomIdQ());
			}
			
			if(StringUtility.isNotEmpty(cond.getReceiverNameQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("userName").is(cond.getReceiverNameQ());
				else 
					criteria = criteria.and("userName").is(cond.getReceiverNameQ());
			}
			
			if(StringUtility.isNotEmpty(cond.getSenderNameQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("senderName").is(cond.getSenderNameQ());
				else 
					criteria = criteria.and("senderName").is(cond.getSenderNameQ());
			}
			
			if("All".equals(cond.getTypeQ()) == false)
			{
				if(criteria == null)
					criteria = Criteria.where("type").is(cond.getTypeQ());
				else 
					criteria = criteria.and("type").is(cond.getTypeQ());
			}
			
			Query query = null;
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
			result = partyonMongo.find(query, ChatroomMessage.class);
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}
}
