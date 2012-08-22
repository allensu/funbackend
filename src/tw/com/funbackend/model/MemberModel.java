package tw.com.funbackend.model;

import java.util.List;

import com.gofunfriend.gofuncube.partyon.domain.User;

import tw.com.funbackend.persistence.MessageData;

public interface MemberModel {
	/**
	 * 取得全部User
	 * @return
	 */
	List<User> readUserAll();
}
