package tw.com.funbackend.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MenuItem {

	/**
	 * Mongo Id
	 */
	@Id
	private String id;
	
	/**
	 * 功能名稱
	 */
	private String title;
	
	/**
	 * 頁面路徑
	 */
    private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}
