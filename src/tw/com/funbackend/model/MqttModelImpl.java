package tw.com.funbackend.model;

import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import tw.com.funbackend.config.FunBackendMongoConfig;
import tw.com.funbackend.config.GoPartyonMessageConfig;
import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.UserInfo;
import tw.com.funbackend.service.MqttService;
@Repository
public class MqttModelImpl implements MqttModel {
	protected Logger logger = Logger.getLogger("model");
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(GoPartyonMessageConfig.class);
    MongoOperations goPartyonMessageMongo = ctx.getBean(MongoOperations.class);

    
	@Override
	public MessageData createMessage(MessageData messageData) {
		
		goPartyonMessageMongo.save(messageData);
		
		return messageData;
	}


	@Override
	public MessageData getLastMessage() {
		MessageData result = null;
		
		try {
			DBCollection goPartyonMessageColl = goPartyonMessageMongo.getCollection(goPartyonMessageMongo.getCollectionName(MessageData.class));  
			DBObject sort = BasicDBObjectBuilder.start().add("serial", -1).get();			
			DBCursor item = goPartyonMessageColl.find().sort(sort).limit(1);  
		  
		    if (item.hasNext()) {
		       DBObject parameter = item.next();
		       
		       result = new MessageData();
		       result.setId(parameter.get("_id").toString());
		       result.setSerial(Integer.valueOf(parameter.get("serial").toString()));
		       result.setTarget(parameter.get("target").toString());
		       result.setMessage(parameter.get("message").toString());		       
		    } 
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

}
