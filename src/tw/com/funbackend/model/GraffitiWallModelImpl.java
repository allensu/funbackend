package tw.com.funbackend.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import tw.com.funbackend.form.querycond.GraffitiWallCondition;
import tw.com.funbackend.form.tableschema.GraffitiWallRankTableSchema;
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
				parameter.put("category", cond.getCategoryQ());
			
			if(StringUtility.isNotEmpty(cond.getWallOwnerId()))
				parameter.put("wallOwner.$id", new ObjectId(cond.getWallOwnerId()));
			
			if(StringUtility.isNotEmpty(cond.getPosterId()))
				parameter.put("poster.$id", new ObjectId(cond.getPosterId()));
			
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
				criteria = Criteria.where("category").is(cond.getCategoryQ());
		
			if(StringUtility.isNotEmpty(cond.getWallOwnerId()))
			{
				if(criteria == null)
					criteria = Criteria.where("wallOwner.$id").is(new ObjectId(cond.getWallOwnerId()));
				else 
					criteria = criteria.and("wallOwner.$id").is(new ObjectId(cond.getWallOwnerId()));
			}
			
			if(StringUtility.isNotEmpty(cond.getPosterId()))
			{
				if(criteria == null)
					criteria = Criteria.where("poster.$id").is(new ObjectId(cond.getPosterId()));
				else 
					criteria = criteria.and("poster.$id").is(new ObjectId(cond.getPosterId()));
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
				criteria = Criteria.where("category").is(cond.getCategoryQ());
		
			if(StringUtility.isNotEmpty(cond.getWallOwnerId()))
			{
				if(criteria == null)
					criteria = Criteria.where("wallOwner.$id").is(new ObjectId(cond.getWallOwnerId()));
				else 
					criteria = criteria.and("wallOwner.$id").is(new ObjectId(cond.getWallOwnerId()));
			}
			
			if(StringUtility.isNotEmpty(cond.getPosterId()))
			{
				if(criteria == null)
					criteria = Criteria.where("poster.$id").is(new ObjectId(cond.getPosterId()));
				else 
					criteria = criteria.and("poster.$id").is(new ObjectId(cond.getPosterId()));
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

	@Override
	public List<GraffitiWallRankTableSchema> readGraffitiWallRanking() {

		List<GraffitiWallRankTableSchema> result = new ArrayList<GraffitiWallRankTableSchema>();
		
		try {
//			db.graffitiWallItem.group ({key:{"poster.$id":true},
//			initial:{totalCount:0,postMessage:0,wantTo:0,checkIn:0},
//			reduce:function(doc,out){out.totalCount++;if(doc.category=="post-message"){out.postMessage++;}
//			else if(doc.category=="want-to"){out.wantTo++;}
//			else if(doc.category=="check-in"){out.checkIn++;}},
//			finalize:function(out){}});

			//GroupByResults<GraffitiWallRank> result = new 
			
			GroupByResults<GraffitiWallRankTableSchema> groupResult = partyonMongo.group("graffitiWallItem",
					GroupBy.key("poster.$id").initialDocument("{posterId:'',totalCount:0,postMessage:0,wantTo:0,checkIn:0}")
					.reduceFunction("function(doc,out){out.posterId=doc.poster.$id;out.totalCount++;if(doc.category=='post-message'){out.postMessage++;}" +
					"else if(doc.category=='want-to'){out.wantTo++;}" +
							"else if(doc.category=='check-in'){out.checkIn++;}}"),
							GraffitiWallRankTableSchema.class);
			
			Iterator<GraffitiWallRankTableSchema> it = groupResult.iterator();
			while(it.hasNext())
			{
				GraffitiWallRankTableSchema currData = it.next();
				
				result.add(currData);
			}
			
			System.out.println(result.size());
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
			

		return result;
	}

}
