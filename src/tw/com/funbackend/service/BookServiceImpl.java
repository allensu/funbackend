package tw.com.funbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.model.BookModel;
import tw.com.funbackend.pojo.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookModel bookModel;
	
	@Override
	public List<Book> getBookList() {
		
		return bookModel.getBookList();
	}

	@Override
	public List<Book> getBookListByCategory(String serNo) {

		return bookModel.getBookListByCategory(serNo);		
	}

}
