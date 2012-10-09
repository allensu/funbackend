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
						
			if("All".equals(cond.getCategoryQ()) == false)
				parameter.put("categoryQ", cond.getCategoryQ());
			
			if(StringUtility.isNotEmpty(cond.getWallOwnerQ()))
				parameter.put("wallOwnerQ", cond.getWallOwnerQ());
			
			if(StringUtility.isNotEmpty(cond.getPosterQ()))
				parameter.put("posterQ", cond.getPosterQ());
			
			count = (int) graffitiWallItemColl.count(parameter);
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
	
			if("All".equals(cond.getCategoryQ()) == false)
				criteria = Criteria.where("categoryQ").is(cond.getCategoryQ());
		
			if(StringUtility.isNotEmpty(cond.getWallOwnerQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("wallOwnerQ").is(cond.getWallOwnerQ());
				else 
					criteria = criteria.and("wallOwnerQ").is(cond.getWallOwnerQ());
			}
			
			if(StringUtility.isNotEmpty(cond.getPosterQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("posterQ").is(cond.getPosterQ());
				else 
					criteria = criteria.and("posterQ").is(cond.getPosterQ());
			}	
			
			Query query = null;
			if(criteria != null)
				query = new Query(criteria).skip(startIndex).limit(length);
			else
				query = new Query().skip(startIndex).limit(length);
			
			result = partyonMongo.find(query, GraffitiWallItem.class);
			resultFinal = result;
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
			
			if("All".equals(cond.getCategoryQ()) == false)
				criteria = Criteria.where("categoryQ").is(cond.getCategoryQ());
		
			if(StringUtility.isNotEmpty(cond.getWallOwnerQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("wallOwnerQ").is(cond.getWallOwnerQ());
				else 
					criteria = criteria.and("wallOwnerQ").is(cond.getWallOwnerQ());
			}
			
			if(StringUtility.isNotEmpty(cond.getPosterQ()))
			{
				if(criteria == null)
					criteria = Criteria.where("posterQ").is(cond.getPosterQ());
				else 
					criteria = criteria.and("posterQ").is(cond.getPosterQ());
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
			
			result = partyonMongo.find(query, GraffitiWallItem.class);
			resultFinal = result;
			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return resultFinal;
	}

}
