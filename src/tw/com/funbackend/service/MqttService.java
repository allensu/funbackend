package tw.com.funbackend.service;

import java.util.List;
import tw.com.funbackend.persistence.MessageData;

public interface MqttService {
	 MessageData createMessage(MessageData messageData);
	 List<MessageData> readMessageAll();
}
