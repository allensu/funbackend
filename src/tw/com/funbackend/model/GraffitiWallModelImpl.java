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
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import tw.com.funbackend.form.querycond.GraffitiWallCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.GraffitiWallItem;
import tw.com.funbackend.utility.GeoUtility;
import tw.com.funbackend.utility.StringUtility;

@Repository
public class GraffitiWallModelImpl implements GraffitiWallModel {

protected Logger logger = Logger.getLogger("model");
	
	@Autowired
	MongoOperations partyonMongo;

	@Override
	public int readGraffitiWallCountByCond(GraffitiWallCondition cond) {

		int count = 0;
		
		try 
		{
			DBCollection graffitiWallItemColl = partyonMongo.getCollection(partyonMongo.getCollectionName(GraffitiWallItem.class));  
			BasicDBObject parameter = new BasicDBObject();  
			
			count = (int) graffitiWallItemColl.count(parameter);
//			if("All".equals(cond.getChatRoomStyleQ()) == false)
//				parameter.put("chatRoomStyle", cond.getChatRoomStyleQ());
//			
//			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
//				parameter.put("users", cond.getUserNameQ());
//			
//			if("geo".equals(cond.getChatRoomStyleQ()) && cond.getKilometerQ() != "")
//			{
//				DBCursor dbCursor = chatroomColl.find(parameter);
//				
//				double lon = 0;
//				double lat = 0;
//				double distance = 0;
//				GeoUtility geoUtility = new GeoUtility();
//				while(dbCursor.hasNext())
//				{
//					DBObject dbObj = dbCursor.next();
//					
//					lon = Double.parseDouble(String.valueOf(((DBObject)dbObj.get("location")).get("lon")));
//					lat = Double.parseDouble(String.valueOf(((DBObject)dbObj.get("location")).get("lat")));
//					
//					distance = geoUtility.GetDistance(lat, lon, 
//							Double.parseDouble(cond.getLatitudeQ()), 
//							Double.parseDouble(cond.getLongitudeQ()));
//
//					if(distance <= Double.parseDouble(cond.getKilometerQ()))
//					{
//						count++;
//					}
//				}
//			} else {
//				count = (int) chatroomColl.count(parameter);
//			}
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return count;
	}

	@Override
	public List<GraffitiWallItem> readGraffitiWallPageByCond(
			GraffitiWallCondition cond, int startIndex, int length) {
		
		List<GraffitiWallItem> result = new ArrayList<GraffitiWallItem>();
		List<GraffitiWallItem> resultFinal = new ArrayList<GraffitiWallItem>();
		
		try 
		{
			Criteria criteria = null;
	
//			if("All".equals(cond.getChatRoomStyleQ()) == false)
//				criteria = Criteria.where("chatRoomStyle").is(cond.getChatRoomStyleQ());
//			
//			
//			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
//			{
//				if(criteria == null)
//					criteria = Criteria.where("users").is(cond.getUserNameQ());
//				else 
//					criteria = criteria.and("users").is(cond.getUserNameQ());
//			}
			
			Query query = null;
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
			result = partyonMongo.find(query, GraffitiWallItem.class);
			resultFinal = result;
			
//			if("geo".equals(cond.getChatRoomStyleQ()) && cond.getKilometerQ() != "")
//			{
//				double lon = 0;
//				double lat = 0;
//				double distance = 0;
//				GeoUtility geoUtility = new GeoUtility();
//				for(Chatroom currChatroom : result)
//				{
//					lon = currChatroom.getLocation().get("lon");
//					lat = currChatroom.getLocation().get("lat");
//					distance = geoUtility.GetDistance(lat, lon, 
//							Double.parseDouble(cond.getLatitudeQ()), 
//							Double.parseDouble(cond.getLongitudeQ()));
//					
//					if(distance <= Double.parseDouble(cond.getKilometerQ()))
//					{
//						resultFinal.add(currChatroom);
//					}
//				}					
//			} else {
//				resultFinal = result;
//			}
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return resultFinal;
	}

	@Override
	public List<GraffitiWallItem> readGraffitiWallPageByCondSort(
			GraffitiWallCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {
		
		List<GraffitiWallItem> result = new ArrayList<GraffitiWallItem>();
		List<GraffitiWallItem> resultFinal = new ArrayList<GraffitiWallItem>();
		
		try 
		{
			Criteria criteria = null;
			
//			if("All".equals(cond.getChatRoomStyleQ()) == false)
//				criteria = Criteria.where("chatRoomStyle").is(cond.getChatRoomStyleQ());
//					
//			if(StringUtility.isNotEmpty(cond.getUserNameQ()))
//			{
//				if(criteria == null)
//					criteria = Criteria.where("users").is(cond.getUserNameQ());
//				else 
//					criteria = criteria.and("users").is(cond.getUserNameQ());
//			}
			
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
			
			result = partyonMongo.find(query, GraffitiWallItem.class);
			resultFinal = result;
//			if("geo".equals(cond.getChatRoomStyleQ()) && cond.getKilometerQ() != "")
//			{
//				double lon = 0;
//				double lat = 0;
//				double distance = 0;
//				GeoUtility geoUtility = new GeoUtility();
//				for(Chatroom currChatroom : result)
//				{
//					lon = currChatroom.getLocation().get("lon");
//					lat = currChatroom.getLocation().get("lat");
//					distance = geoUtility.GetDistance(lat, lon, 
//							Double.parseDouble(cond.getLatitudeQ()), 
//							Double.parseDouble(cond.getLongitudeQ()));
//					
//					if(distance <= Double.parseDouble(cond.getKilometerQ()))
//					{
//						resultFinal.add(currChatroom);
//					}
//				}					
//			} else {
//				resultFinal = result;
//			}
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return resultFinal;
	}

}
