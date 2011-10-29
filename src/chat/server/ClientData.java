package chat.server;

/**
 * @author Eric
 */
public class ClientData {
	private String clientID;
	private int lastMessageID;
	private long expiration;
	
	public ClientData (String clientID, int lastMessageID, long expiration){
		this.clientID = clientID;
		this.lastMessageID = lastMessageID;
		this.expiration = expiration;
	}
		    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clientID == null) ? 0 : clientID.hashCode());
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
		ClientData other = (ClientData) obj;
		if (clientID == null) {
			if (other.clientID != null)
				return false;
		} else if (!clientID.equals(other.clientID))
			return false;
		return true;
	}


	public int getLastMessageID() {
		return lastMessageID;
	}
	public void setLastMessageID(int lastMessageID) {
		this.lastMessageID = lastMessageID;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
	public String getClientID() {
		return clientID;
	}
}