package chat.client;


import chat.MessageServerInterface;

import java.rmi.Naming;
import java.util.Timer;

/**
 * @author Laurine and Eric
 */
public class Client {
	public static Timer timer;	

	public Client (String clientID, String server, int timeout, int refreshrate){
		this.clientID = clientID;
		this.server = server;
		this.timeout = timeout;
		this.refreshrate = refreshrate;
		timer = new Timer();
		timer.scheduleAtFixedRate(new ClientGUI.getMessage(), 0, this.refreshrate);	
	}
	
	public static void killTimer(){
		
	}

	
	private String clientID;
	public String getClientID() {
		return clientID;
	}

	private String server;
	public String getServer() {
		return server;
	}

	private int timeout;
	public int getTimeout() {
		return timeout;
	}
	
	
	private int refreshrate;
	public int getRefreshrate() {
		return refreshrate;
	}
	
	public String getMessage(String clientID)
	{
		try {
			ClientGUI.serverMsg = (MessageServerInterface)Naming.lookup("Server");
			return ClientGUI.serverMsg.getMessage(clientID);
		} catch (Exception e) {
			System.err.println("Rmi Client exception: " + e);
			e.printStackTrace();
			
			return e.getMessage();
		}
	}
	
	public void dropMessage(String clientID, String message)
	{
		try {
			ClientGUI.serverMsg = (MessageServerInterface)Naming.lookup("Server");
			ClientGUI.serverMsg.dropMessage(clientID,message);
		} catch (Exception e) {
			System.err.println("Rmi Client exception: " + e);
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
	}
}
