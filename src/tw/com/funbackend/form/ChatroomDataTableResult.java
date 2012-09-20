package tw.com.funbackend.form;

import java.util.List;

public class ChatroomDataTableResult {
	private List<ChatroomDataTableSchema> aaData;
	private String sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	
	public List<ChatroomDataTableSchema> getAaData() {
		return aaData;
	}
	public void setAaData(List<ChatroomDataTableSchema> aaData) {
		this.aaData = aaData;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	} 
}
