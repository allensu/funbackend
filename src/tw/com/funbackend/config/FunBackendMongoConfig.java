package tw.com.funbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

@Configuration
public class FunBackendMongoConfig extends AbstractMongoConfiguration {
	
	@Override
	public Mongo mongo() throws Exception {
		return new Mongo("localhost");
	}

	@Override
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

	@Override
	public String getDatabaseName() {
		return "FunBackend";
	}
}
