package tw.com.funbackend.service;

import java.util.List;

import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.User;

public interface ChatroomService {

	/**
	 * 取得條件下的總聊天室筆數
	 * @param cond
	 * @return
	 */
	int readChatroomCountByCond(ChatroomMessageRecordCondition cond);
	
	/**
	 * 取得條件下分頁聊天室 
	 * @param cond
	 * @param startIndex
	 * @param length
	 * @return
	 */
	List<Chatroom> readChatroomPageByCond(ChatroomMessageRecordCondition cond, int startIndex, int length);
	
	/**
	 * 取得條件及排序下分頁聊天室
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @param sortColName 排序欄位名稱
	 * @param sortDir 排序方向 (-1:asc, 1:desc)
	 * @return
	 */
	List<Chatroom> readChatroomPageByCondSort(ChatroomMessageRecordCondition cond, int startIndex, int length, String sortColName, int sortDir);
	
}
