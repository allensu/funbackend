package tw.com.funbackend.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tw.com.funbackend.pojo.Book;
import tw.com.funbackend.pojo.TestVo;

@Repository
public class BookModelImpl implements BookModel {

	//@Autowired
	//private JdbcTemplate jdbcTmp;
	@Autowired
	MongoOperations mongoOperations;
	
	//@Autowired
	//private MongoTemplate mongoTemplate;
				  
	@Override
	public List<Book> getBookList() {
		
		List<Book> bookListData = new ArrayList<Book>();
		
		Book book1 = new Book();
		book1.setBookName("Packtpub.NHibernate.3.0.Cookbook.Oct.2010");
		book1.setBookPath("/EBookLibrary/Resource/Ebook/Packtpub.NHibernate.3.0.Cookbook.Oct.2010.jpg");
		
		bookListData.add(book1);
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		//mongoOperations = (MongoOperations)ctx.getBean("mongoTemplate");
		
		TestVo testVo = new TestVo();
		testVo.setName("AllenSu TestVo 2");
		mongoOperations.save(testVo);
		
		//mongoTemplate.save(testVo);
		
		return bookListData;
	}

	@Override
	public List<Book> getBookListByCategory(String serNo) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}
