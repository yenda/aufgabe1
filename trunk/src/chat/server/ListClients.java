package chat.server;

import java.util.ArrayList;
/**
 * @author Eric
 */
public class ListClients {
	private ArrayList<ClientData> listClients;
	
	public ListClients (){
		listClients = new ArrayList<ClientData>(); 
	}
	
	/**
	 * @return the listClients
	 */
	public ArrayList<ClientData> getListClients() {
		return listClients;
	}


	/**
	 * @param String clientID : ID of the client
	 * @param int messageID : ID of the last message sent to the server
	 * @return the ID of the last message the client received
	 * or the last message sent to the server if it's a new client
	 */	
	public int updateClient(String clientID, int messageID){
		ClientData client = new ClientData(clientID,messageID, System.currentTimeMillis()+(Server.MAX_CLIENT_IDLE_TIME*1000));
		int index = this.listClients.indexOf(client);
		if (index == -1){
			this.addClient(client);
			return messageID;
		}
		else{
			int lastMessageID = this.listClients.get(index).getLastMessageID();
			client.setLastMessageID(lastMessageID);
			this.listClients.set(index, client);
			return lastMessageID;
		}
	}
	
	public void addClient (ClientData client){
		listClients.add(client);
	}
	
	/**
	 * delete the clients who have been idle for too long
	 * @return nb of deleted client
	 */
	public int cleanUp (){
		long timestamp = System.currentTimeMillis();
		int deleted = 0;
		for (int i = 0; i < this.listClients.size();i++){
			if (this.listClients.get(i).getExpiration() < timestamp){
				this.listClients.remove(i);
				deleted++;
			}
		}
		return deleted;
	}

}
