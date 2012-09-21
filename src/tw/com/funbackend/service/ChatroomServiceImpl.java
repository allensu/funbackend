package tw.com.funbackend.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.model.ChatroomModel;
import tw.com.funbackend.model.MemberModel;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.User;

@Service
public class ChatroomServiceImpl implements ChatroomService {
	
	protected Logger logger = Logger.getLogger("service");

	@Autowired
	private ChatroomModel chatroomModel;
	
	@Override
	public int readChatroomCountByCond(ChatroomMessageRecordCondition cond) {
		
		return chatroomModel.readChatroomCountByCond(cond);
	}

	@Override
	public List<Chatroom> readChatroomPageByCond(
			ChatroomMessageRecordCondition cond, int startIndex, int length) {
		
		return chatroomModel.readChatroomPageByCond(cond, startIndex, length);
	}

	@Override
	public List<Chatroom> readChatroomPageByCondSort(
			ChatroomMessageRecordCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {
		
		return chatroomModel.readChatroomPageByCondSort(cond, startIndex, length, sortColName, sortDir);
	}
	

}
