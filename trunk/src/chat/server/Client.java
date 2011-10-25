package chat.server;

import java.sql.Date;

/**
 * @author  Pierre-Henri
 */
public class Client {
	/**
	 * @uml.property  name="idClient"
	 */
	private int idClient;
	/**
	 * @uml.property  name="idLastMessage"
	 */
	private int idLastMessage;
	/**
	 * @uml.property  name="expiration"
	 */
	private Date expiration;		
	
	/**
	 * @return
	 * @uml.property  name="idClient"
	 */
	public int getIdClient(){
		return idClient;
	}
	/**
	 * @param idClient
	 * @uml.property  name="idClient"
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return
	 * @uml.property  name="idLastMessage"
	 */
	public int getIdLastMessage() {
		return idLastMessage;
	}
	/**
	 * @param idLastMessage
	 * @uml.property  name="idLastMessage"
	 */
	public void setIdLastMessage(int idLastMessage) {
		this.idLastMessage = idLastMessage;
	}

	/**
	 * @return
	 * @uml.property  name="expiration"
	 */
	public Date getExpiration() {
		return expiration;
	}
	/**
	 * @param expiration
	 * @uml.property  name="expiration"
	 */
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
