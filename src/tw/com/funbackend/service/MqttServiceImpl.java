package tw.com.funbackend.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.model.MqttModel;
import tw.com.funbackend.persistence.MessageData;

@Service
public class MqttServiceImpl implements MqttService {
	protected Logger logger = Logger.getLogger("service");
	
	@Autowired
	private MqttModel mqttModel;
	
	@Override
	public MessageData createMessage(MessageData messageData) {
		
		MessageData result = null;
		
		try {
			MessageData lastMessageData = mqttModel.getLastMessage();
			int newSerial = 1;
			
			if(lastMessageData != null)
			{
				newSerial = Integer.parseInt(lastMessageData.getSerial()) + 1;
			}
			
			messageData.setSerial(String.valueOf(newSerial));
			
			result = mqttModel.createMessage(messageData);
		} catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public List<MessageData> readMessageAll() {
		return mqttModel.readMessageAll();
	}

}
