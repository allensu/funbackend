package tw.com.funbackend.form;

import java.util.List;

import tw.com.funbackend.persistence.gopartyon.User;

public class MemberToDataTable {
	private List<MemberDataQueryDataTable> aaData;
	private String sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	
	public List<MemberDataQueryDataTable> getAaData() {
		return aaData;
	}
	public void setAaData(List<MemberDataQueryDataTable> aaData) {
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
