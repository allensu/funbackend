package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.persistence.MessageData;
import tw.com.funbackend.persistence.gopartyon.User;

public interface MemberModel {
	/**
	 * 取得全部User
	 * @return
	 */
	List<User> readUserAll();
}
