package tw.com.funbackend.form.result;

import java.util.List;

import tw.com.funbackend.form.tableschema.ManageMenuAuthTableSchema;

public class ManageMenuAuthDataTableResult {
	private List<ManageMenuAuthTableSchema> aaData;
	private String sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	
	public List<ManageMenuAuthTableSchema> getAaData() {
		return aaData;
	}
	public void setAaData(List<ManageMenuAuthTableSchema> aaData) {
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
