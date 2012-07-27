package tw.com.funbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.model.AccountModel;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountModel accountModel;
}
