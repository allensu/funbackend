package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.persistence.gopartyon.User;

import tw.com.funbackend.persistence.MessageData;

public interface MemberModel {
	/**
	 * 取得全部User
	 * @return
	 */
	List<User> readUserAll();
	
	/**
	 * 取得總 User 筆數
	 * @return
	 */
	int readUserCount();
	
	/**
	 * 取得分頁 User 
	 * @param startIndex
	 * @param lengthIndex
	 * @return
	 */
	List<User> readUserPage(int startIndex, int length);
	
	/**
	 * 取得特定的 User
	 * @param id
	 * @return
	 */
	User readUser(String id);
	
	/**
	 * 更新 User 資料
	 * @param user
	 * @return
	 */
	void updateUser(User user);
}
