package tw.com.funbackend.service;

import tw.com.funbackend.persistence.MessageData;

public interface MqttService {
	 MessageData createMessage(MessageData messageData);
}
