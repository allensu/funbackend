package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.form.querycond.ChatroomMessageCondition;
import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.ChatroomMessage;
import tw.com.funbackend.persistence.gopartyon.User;

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
	
	/**
	 * 取得特定的聊天室
	 * @param id
	 * @return
	 */
	Chatroom readChatroom(String id);
	
	/**
	 * 取得特定聊天室的訊息內容
	 * @param chatroomId
	 * @return
	 */
	List<ChatroomMessage> readChatroomMessage(String chatroomId);
	
	/**
	 * 取得條件下的總聊天室的訊息內容筆數 
	 * @param cond 查詢條件
	 * @return
	 */
	int readChatroomMessageCountByCond(ChatroomMessageCondition cond);
	
	/**
	 * 取得條件下分頁聊天室的訊息內容
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @return
	 */
	List<ChatroomMessage> readChatroomMessagePageByCond(ChatroomMessageCondition cond, int startIndex, int length);
}
