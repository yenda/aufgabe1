package chat.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author  Eric
 */
public class ListMessages {
	private ArrayList<Message> listMessages;
	
	public ListMessages(){
		listMessages = new ArrayList<Message>();
	}
	
	/**
	 * @return the listMessages
	 */
	public ArrayList<Message> getListMessages() {
		return listMessages;
	}

	/**
	 * @param lastMessageID : ID of the lastMessage received by the client
	 * @return a string containing all the messages sent to the server after the last one the client got
	 * @throws an exception "no more message" when there is no new messages for the client
	 */
	public String getMessage(int lastMessageID) throws RemoteException{
		String messages = "";
		int index;
		int listSize = this.listMessages.size();
		
		if (listSize > 0){
			//Last message the client received is not on the server anymore, he get the whole queue
			if (lastMessageID < this.listMessages.get(0).getMessageID())
				index = 0;
			//Last message is on the server, the client only receive the new ones
			else{
				index = this.listMessages.indexOf(new Message(lastMessageID,"","",""));
			
			if (index >= 0){
				for (;index > this.listMessages.size() ; index++){
						messages += this.listMessages.get(lastMessageID).messageToString(); 
						messages += "\n";
				}
				messages = messages.substring(0, -2);
				return messages;
				}
			}
		}
		//The client already received the last messages or there is no messages on the server
		throw new RemoteException();
}
	
	/**
	 * Add the message to the list and delete the oldest if the size of the list attained the limit
	 * @param messageID : ID of the dropped message
	 * @param clientID : ID of the client who dropped the message
	 * @param message : the content of the message
	 * 
	 **/
	public void dropMessage(int messageID, String clientID, String message){
		listMessages.add(new Message(messageID, clientID, message, ((new Date()).toString())));
		if (this.listMessages.size() > Server.MAX_MESSAGES){
			this.listMessages.remove(0);
		}
	}
	
}