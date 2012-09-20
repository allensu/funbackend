package tw.com.funbackend.service;

import java.util.List;

import org.apache.log4j.Logger;

import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.User;

public class ChatroomServiceImpl implements ChatroomService {
	
	protected Logger logger = Logger.getLogger("service");

	@Override
	public int readChatroomCountByCond(ChatroomMessageRecordCondition cond) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Chatroom> readChatroomPageByCond(
			ChatroomMessageRecordCondition cond, int startIndex, int length) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chatroom> readChatroomPageByCondSort(
			ChatroomMessageRecordCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
