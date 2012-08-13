package tw.com.funbackend.model;

import tw.com.funbackend.persistence.MessageData;

public interface MqttModel {
	MessageData createMessage(MessageData messageData);
	MessageData getLastMessage();
}
