package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;

public interface ChatroomModel {

	/**
	 * 取得條件下的總 聊天室筆數 
	 * @param cond 查詢條件
	 * @return
	 */
	int readChatroomCountByCond(ChatroomMessageRecordCondition cond);
	
	/**
	 * 取得條件下分頁 聊天室
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @return
	 */
	List<Chatroom> readChatroomPageByCond(ChatroomMessageRecordCondition cond, int startIndex, int length);
	
	/**
	 * 取得條件及排序下分頁 聊天室 
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @param sortColName 排序欄位名稱
	 * @param sortDir 排序方向 (-1:asc, 1:desc)
	 * @return
	 */
	List<Chatroom> readChatroomPageByCondSort(ChatroomMessageRecordCondition cond, int startIndex, int length, String sortColName, int sortDir);
	
}
