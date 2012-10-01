package tw.com.funbackend.form.TableSchema;

public class BlockUserRankTableSchema {
	static public String[] MapColumns = {
		///"",
		"rankNum",
		"userName",
		"votes",
		//""
		};
	
	private int rankNum;
	private String userName;
	private int votes;
	
	/**
	 * @return the rankNum
	 */
	public int getRankNum() {
		return rankNum;
	}
	/**
	 * @param rankNum the rankNum to set
	 */
	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the votes
	 */
	public int getVotes() {
		return votes;
	}
	/**
	 * @param votes the votes to set
	 */
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	
}
