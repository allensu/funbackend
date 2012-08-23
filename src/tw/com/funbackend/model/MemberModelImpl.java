package tw.com.funbackend.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

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
	
	
	
}
