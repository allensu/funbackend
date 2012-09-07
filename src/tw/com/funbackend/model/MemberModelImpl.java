package tw.com.funbackend.model;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.gopartyon.User;


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
	public User updateUser(User user) {
		
		try {
			
			
			
			
			
			
		} 
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return user;
	}
	
	
	
}
