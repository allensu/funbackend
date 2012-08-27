package tw.com.funbackend.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.persistence.gopartyon.User;

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
}
