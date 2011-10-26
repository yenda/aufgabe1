package chat.server;

import java.util.ArrayList;
/**
 * @author Eric
 */
public class ListClients {
	private ArrayList<Client> listClients;
	
	public ListClients (){
		listClients = new ArrayList<Client>(); 
	}
	
	/**
	 * @return the listClients
	 */
	public ArrayList<Client> getListClients() {
		return listClients;
	}



	public String getMessage (String clientID, int messageID){
		int lastMessageID = this.updateClient(clientID, messageID);
		
		return "kill";
	}
	
	public int updateClient(String clientID, int messageID){
		Client client = new Client(clientID,messageID, System.currentTimeMillis());
		if (this.isNewClient(client)){
			this.addClient(client);
			return messageID;
		}
		else{
			int i = this.listClients.indexOf(client);
			int lastMessageID = this.listClients.get(i).getLastMessageID();
			client.setLastMessageID(lastMessageID);
			this.listClients.set(i, client);
			return lastMessageID;
		}
	}
	
	public boolean isNewClient (Client client){;
		return !listClients.contains(client);
	}
	
	public void addClient (Client client){
		listClients.add(client);
	}
	

}
