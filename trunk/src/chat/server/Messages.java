package chat.server;

import java.util.ArrayList;

public class Messages {
	private ArrayList<Message> listMessages;
	
	public Messages(){
		listMessages = new ArrayList<Message>();
	}
	
	public void dropMessage(int messageID, String clientID, String message){
		listMessages.add(new Message(messageID, clientID, message, System.currentTimeMillis()));		
	}
	
	public class Message {
		private int messageID;
		private String clientID;
		private String message;
		private long timestamp;
		
		public Message(int messageID, String clientID, String message, long timestamp ){
			this.messageID = messageID;
			this.clientID = clientID;
			this.message = message;
			this.timestamp = timestamp;
		}

		public int getMessageID() {
			return messageID;
		}

		public String getClientID() {
			return clientID;
		}

		public String getMessage() {
			return message;
		}

		public long getTimestamp() {
			return timestamp;
		}
		
		
	}
}
