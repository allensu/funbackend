package tw.com.funbackend.persistence.gopartyon.vo;

import java.util.Date;

public class MobileToken {
	private String authToken;
	private Date expiredDate;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

}