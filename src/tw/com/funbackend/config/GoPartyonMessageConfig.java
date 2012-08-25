package tw.com.funbackend.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
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
		//return new Mongo("118.233.100.117");
		return new Mongo("dev2.gopartyon.com");
	}

}
