package tw.com.funbackend.form.querycond;

public class ChatroomMessageCondition {

	private String receiverNameQ;
	private String senderNameQ;
	private String chatroomIdQ;
	private String typeQ;

	public String getChatroomIdQ() {
		return chatroomIdQ;
	}

	public void setChatroomIdQ(String chatroomIdQ) {
		this.chatroomIdQ = chatroomIdQ;
	}

	public String getReceiverNameQ() {
		return receiverNameQ;
	}

	public void setReceiverNameQ(String receiverNameQ) {
		this.receiverNameQ = receiverNameQ;
	}

	public String getSenderNameQ() {
		return senderNameQ;
	}

	public void setSenderNameQ(String senderNameQ) {
		this.senderNameQ = senderNameQ;
	}

	public String getTypeQ() {
		return typeQ;
	}

	public void setTypeQ(String typeQ) {
		this.typeQ = typeQ;
	}
	
	
}
