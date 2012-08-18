package tw.com.funbackend.model;

import java.util.List;

import tw.com.funbackend.persistence.MessageData;

public interface MqttModel {
	/**
	 * 取得發送訊息
	 * @param messageData
	 * @return
	 */
	MessageData createMessage(MessageData messageData);
	
	/**
	 * 取得最後建立的訊息
	 * @return
	 */
	MessageData getLastMessage();
	
	/**
	 * 取得全部訊息
	 * @return
	 */
	List<MessageData> readMessageAll();
	
}
