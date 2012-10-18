package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.form.querycond.ChatroomMessageRecordCondition;
import tw.com.funbackend.form.querycond.GraffitiWallCondition;
import tw.com.funbackend.form.tableschema.GraffitiWallRankTableSchema;
import tw.com.funbackend.persistence.gopartyon.Chatroom;
import tw.com.funbackend.persistence.gopartyon.GraffitiWallItem;

public interface GraffitiWallModel {
	/**
	 * 取得條件下的總塗鴉牆筆數 
	 * @param cond 查詢條件
	 * @return
	 */
	int readGraffitiWallCountByCond(GraffitiWallCondition cond);
	
	/**
	 * 取得條件下分頁塗鴉牆
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @return
	 */
	List<GraffitiWallItem> readGraffitiWallPageByCond(GraffitiWallCondition cond, int startIndex, int length);
	
	/**
	 * 取得條件及排序下分頁塗鴉牆
	 * @param cond 查詢條件
	 * @param startIndex 分頁啟始位置
	 * @param length 分頁長度
	 * @param sortColName 排序欄位名稱
	 * @param sortDir 排序方向 (-1:asc, 1:desc)
	 * @return
	 */
	List<GraffitiWallItem> readGraffitiWallPageByCondSort(GraffitiWallCondition cond, int startIndex, int length, String sortColName, int sortDir);
	
	/**
	 * 取得Po文排行榜資料
	 * @return
	 */
	List<GraffitiWallRankTableSchema> readGraffitiWallRanking();
}
