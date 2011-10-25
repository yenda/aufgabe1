package chat.server;

import java.sql.Date;

public class Client {
	private int idClient;
	private int idLastMessage;
	private Date expiration;		
	
	public int getIdClient(){
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdLastMessage() {
		return idLastMessage;
	}

	public void setIdLastMessage(int idLastMessage) {
		this.idLastMessage = idLastMessage;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
