package chat.server;

public class Message {
	/**
	 * @uml.property  name="messageID"
	 */
	private int messageID;
	/**
	 * @uml.property  name="clientID"
	 */
	private String clientID;
	/**
	 * @uml.property  name="message"
	 */
	private String message;
	/**
	 * @uml.property  name="timestamp"
	 */
	private String timestamp;
	
	public Message(int messageID, String clientID, String message, String date){
		this.messageID = messageID;
		this.clientID = clientID;
		this.message = message;
		this.timestamp = date;
	}
	
	public String messageToString(){
		String message = "<";
		message += this.getMessageID();
		message += "> <";
		message += this.getClientID();
		message += "> : <";
		message += this.getMessage();
		message += "> <";
		message += this.getTimestamp();
		message += ">";
		return message;
	}

	/**
	 * @return
	 * @uml.property  name="messageID"
	 */
	public int getMessageID() {
		return messageID;
	}

	/**
	 * @return
	 * @uml.property  name="clientID"
	 */
	public String getClientID() {
		return clientID;
	}

	/**
	 * @return
	 * @uml.property  name="message"
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return
	 * @uml.property  name="timestamp"
	 */
	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + messageID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (messageID != other.messageID)
			return false;
		return true;
	}	
}

