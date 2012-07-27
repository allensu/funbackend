package tw.com.funbackend.service;

import java.util.List;

import tw.com.funbackend.pojo.Book;

public interface BookService {
	public List<Book> getBookList();
	public List<Book> getBookListByCategory(String serNo);
}
