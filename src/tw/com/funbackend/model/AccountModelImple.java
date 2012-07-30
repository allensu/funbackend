package tw.com.funbackend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import tw.com.funbackend.persistence.UserInfo;

@Repository
public class AccountModelImple implements AccountModel {

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public UserInfo getUserInfo(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void userLogout(String accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserInfo createUser(UserInfo userInfo) {
		
		mongoOperations.save(userInfo);
		
		return userInfo;
	}
	
	
	
}
