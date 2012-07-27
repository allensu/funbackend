package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.pojo.Book;

public interface BookModel {
	public List<Book> getBookList();
	public List<Book> getBookListByCategory(String serNo);
}
