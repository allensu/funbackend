package tw.com.funbackend.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class MenuGroup {
	
	/**
	 * Mongo Id
	 */
	@Id
	private String id;
	
	/**
	 * 群組名稱
	 */
	private String title;
	
	@DBRef
	private List<MenuItem> content = new ArrayList<MenuItem>();


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<MenuItem> getContent() {
		return content;
	}


	public void setContent(List<MenuItem> content) {
		this.content = content;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
