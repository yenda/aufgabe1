package chat.server;

import java.util.ArrayList;

/**
 * @author  Eric
 */
public class Messages {
	private ArrayList<Message> listMessages;
	
	public Messages(){
		listMessages = new ArrayList<Message>();
	}
	
	public void dropMessage(int messageID, String clientID, String message){
		listMessages.add(new Message(messageID, clientID, message, System.currentTimeMillis()));		
	}
	
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
		private long timestamp;
		
		public Message(int messageID, String clientID, String message, long timestamp ){
			this.messageID = messageID;
			this.clientID = clientID;
			this.message = message;
			this.timestamp = timestamp;
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
		public long getTimestamp() {
			return timestamp;
		}
		
		
	}
}
