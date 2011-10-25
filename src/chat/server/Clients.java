package chat.server;

import java.sql.Date;
import java.util.ArrayList;

public class Clients {
	private ArrayList<Client> listClients;
	
	public Clients (){
		listClients = new ArrayList<Client>(); 
	}
	
	public String getMessage (String clientID){
		String kill = "kill";
		return kill;
	}
	
	public boolean isNewClient (String clientID){
		return listClients.contains(clientID);
	}
	
	public class Client {
		private String clientID;
		private int lastMessageID;
		private Date expiration;
		
		public Client (String clientID, int lastMessageID, Date expiration){
			this.clientID = clientID;
			this.lastMessageID = lastMessageID;
			this.expiration = expiration;
		}
		
	    public boolean equals (Client x) {
	        if (x.clientID == clientID) return true;
	       return false;
	    }

		
		public int getLastMessageID() {
			return lastMessageID;
		}
		public void setLastMessageID(int lastMessageID) {
			this.lastMessageID = lastMessageID;
		}
		public Date getExpiration() {
			return expiration;
		}
		public void setExpiration(Date expiration) {
			this.expiration = expiration;
		}
		public String getClientID() {
			return clientID;
		}	
	}
}
