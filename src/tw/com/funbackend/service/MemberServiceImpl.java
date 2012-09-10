package tw.com.funbackend.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.persistence.gopartyon.User;

import tw.com.funbackend.form.querycond.MemberDataQueryCondition;
import tw.com.funbackend.model.MemberModel;

@Service
public class MemberServiceImpl implements MemberService {
	protected Logger logger = Logger.getLogger("service");
	
	@Autowired
	private MemberModel memberModel;

	@Override
	public List<User> readUserAll() {
		
		return memberModel.readUserAll();
	}

	@Override
	public User readUser(String id) {
		
		return memberModel.readUser(id);
	}

	@Override
	public void updateUser(User user) {
		
		memberModel.updateUser(user);
	}

	@Override
	public int readUserCount() { 
	
		return memberModel.readUserCount();
	}

	@Override
	public List<User> readUserPage(int startIndex, int length) {

		return memberModel.readUserPage(startIndex, length);
	}

	@Override
	public int readUserCountByCond(MemberDataQueryCondition cond) {

		return memberModel.readUserCountByCond(cond);
	}

	@Override
	public List<User> readUserPageByCond(MemberDataQueryCondition cond,
			int startIndex, int length) {

		return memberModel.readUserPageByCond(cond, startIndex, length);
	}

	@Override
	public List<User> readUserPageByCondSort(MemberDataQueryCondition cond,
			int startIndex, int length, String sortColName, int sortDir) {
				
		return memberModel.readUserPageByCondSort(cond, startIndex, length, sortColName, sortDir);
	}
}
