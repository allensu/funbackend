package tw.com.funbackend.service;

import java.util.List;

import com.gofunfriend.gofuncube.partyon.domain.User;


public interface MemberService {
	/**
	 * 取得全部User
	 * @return
	 */
	List<User> readUserAll();
}
