package ci.kossovo.ecole;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "dbecole2";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1",27017);
	}

	
	/*@Override
	protected Collection<String> getMappingBasePackages() {
		return super.getMappingBasePackages();
	}*/
}
