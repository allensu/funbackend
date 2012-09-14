package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.form.querycond.MemberDataQueryCondition;
import tw.com.funbackend.persistence.gopartyon.User;

import tw.com.funbackend.persistence.MessageData;

public interface MemberModel {
	/**
	 * 取得全部User
	 * @return
	 */
	List<User> readUserAll();
	
	/**
	 * 取得總 User 筆數
	 * @return
	 */
	int readUserCount();
	
	/**
	 * 取得分頁 User 
	 * @param startIndex 分頁啟始位置
	 * @param lengthIndex 分頁長度
	 * @return
	 */
	List<User> readUserPage(int startIndex, int length);
	
	/**
	 * 取得條件下的總 User 筆數 
	 * @param cond 查詢條件
	 * @return
	 */
	int readUserCountByCond(MemberDataQueryCondition cond);
	
	/**
	 * 取得條件下分頁 User 
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @return
	 */
	List<User> readUserPageByCond(MemberDataQueryCondition cond, int startIndex, int length);
	
	/**
	 * 取得條件及排序下分頁 User 
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @param sortColName 排序欄位名稱
	 * @param sortDir 排序方向 (-1:asc, 1:desc)
	 * @return
	 */
	List<User> readUserPageByCondSort(MemberDataQueryCondition cond, int startIndex, int length, String sortColName, int sortDir);
	
	/**
	 * 取得特定的 User
	 * @param id
	 * @return
	 */
	User readUser(String id);
	
	/**
	 * 取得特定的 User
	 * @param userName
	 * @return
	 */
	User readUserByUserName(String userName);
	
	/**
	 * 更新 User 資料
	 * @param user
	 * @return
	 */
	void updateUser(User user);
	
	/**
	 * 刪除照片
	 * @param user
	 * @param fileName
	 */
	public void deletePhotoFromAlbum(User user, String fileName);
	
	/**
	 * 刪除大頭照
	 * @param user
	 * @param fileName
	 */
	public void deletePhotoFromPic(User user, String fileName);
	
	/**
	 * 新增照片
	 * @param user
	 * @param fileName
	 */
	public void addPhotoToAlbum(User user, String fileName);
}
