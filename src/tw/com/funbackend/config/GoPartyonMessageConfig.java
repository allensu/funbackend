package tw.com.funbackend.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
@Configuration
public class GoPartyonMessageConfig extends AbstractMongoConfiguration {

	@Override
	public String getDatabaseName() {
		return "gopartyon_message";
	}

	@Override
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}
	
	@Override
	public Mongo mongo() throws Exception {
		
		ArrayList<ServerAddress> addr = new ArrayList<ServerAddress>();
		//addr.add(new ServerAddress("tpe01.gofuncube.com.tw:30000"));
		//addr.add(new ServerAddress("dev.gopartyon.com:30001"));
		//addr.add(new ServerAddress("dev2.gopartyon.com:30002"));
		addr.add(new ServerAddress("dev.gopartyon.com:27017"));
		addr.add(new ServerAddress("dev2.gopartyon.com:27017"));
		
		Mongo mongo = new Mongo(addr);
		
		return mongo;
	}

}
