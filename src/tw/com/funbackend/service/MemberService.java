package tw.com.funbackend.service;

import java.util.List;

import tw.com.funbackend.persistence.gopartyon.User;


public interface MemberService {
	/**
	 * 取得全部User
	 * @return
	 */
	List<User> readUserAll();
	
	/**
	 * 取得特定的 User
	 * @param id
	 * @return
	 */
	User readUser(String id);
}
