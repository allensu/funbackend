package tw.com.funbackend.service;

import java.util.List;
import tw.com.funbackend.persistence.MessageData;

public interface MqttService {
	MessageData createMessage(MessageData messageData);

	List<MessageData> readMessageAll();

	boolean removeMessage(String id);

	boolean removeMessage(List<String> ids);
}
