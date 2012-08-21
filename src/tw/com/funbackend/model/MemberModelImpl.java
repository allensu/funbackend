package tw.com.funbackend.model;

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
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
