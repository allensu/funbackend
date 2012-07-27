package tw.com.funbackend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class AccountModelImple implements AccountModel {

	@Autowired
	MongoOperations mongoOperations;
	
	
	
}
