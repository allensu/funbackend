package tw.com.funbackend.form.result;

import java.util.List;

import tw.com.funbackend.form.ChatroomDataTableSchema;
import tw.com.funbackend.form.tableschema.GraffitiWallTableSchema;

public class GraffitiWallDataTableResult {
	private List<GraffitiWallTableSchema> aaData;
	private String sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	
	/**
	 * @return the aaData
	 */
	public List<GraffitiWallTableSchema> getAaData() {
		return aaData;
	}
	/**
	 * @param aaData the aaData to set
	 */
	public void setAaData(List<GraffitiWallTableSchema> aaData) {
		this.aaData = aaData;
	}
	/**
	 * @return the sEcho
	 */
	public String getsEcho() {
		return sEcho;
	}
	/**
	 * @param sEcho the sEcho to set
	 */
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	/**
	 * @return the iTotalRecords
	 */
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}
	/**
	 * @param iTotalRecords the iTotalRecords to set
	 */
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	/**
	 * @return the iTotalDisplayRecords
	 */
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	/**
	 * @param iTotalDisplayRecords the iTotalDisplayRecords to set
	 */
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}	
}
