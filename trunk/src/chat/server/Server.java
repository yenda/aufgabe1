package chat.server;

import java.rmi.*;
import java.rmi.registry.*; // for LocaleRegistry
import java.rmi.server.*; // for UnicastRemoteObject
import java.util.ArrayList;

import chat.MessageServerInterface;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements MessageServerInterface { 
	
	public static final int MAX_MESSAGES = 100;
	public static final long MAX_CLIENT_IDLE_TIME = 100;
	
	private ArrayList<Client> listClients;
	private Messages listMessages;
	private int messageID;
	
	//Interface implementation
	public Server() throws RemoteException{
		listClients = new Clients();
		listMessages = new Messages();
		messageID = 1;
		
	}
	
	public String getMessage(String clientID) throws RemoteException {
		return listClients.getMessage(String clientID);
	}

	public void dropMessage(String clientID, String message) throws RemoteException {
		 listMessages.dropMessage(this.messageID, clientID, message);
	}

	
	/*** main ***/
	public static void main (String[] args) {
		
		if (System.getSecurityManager() == null) { //without a security manager, RMI doesn't download classes
		    System.setSecurityManager(new SecurityManager());
		}
		
		try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
		
		try {
			MessageServerInterface chatServer = new Server();
			MessageServerInterface stub = (MessageServerInterface) UnicastRemoteObject.exportObject(chatServer, 0);
			Naming.rebind("", chatServer);
		} catch (Exception ex) {
			System.err.println("RMI server exception:" + ex);
			ex.printStackTrace();
		}
	}
}
