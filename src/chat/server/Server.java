package chat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import chat.MessageServerInterface;

/**
 * @author  Pierre-Henri and Eric
 */
@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements MessageServerInterface { 
	
	private Registry registry;
	public static final int MAX_MESSAGES = 100;
	public static final long MAX_CLIENT_IDLE_TIME = 100;
	
	private ListClients listClients;
	/**
	 * @uml.property  name="listMessages"
	 * @uml.associationEnd  
	 */
	private ListMessages listMessages;
	private int messageID;

	
	//Interface implementation
	public Server() throws RemoteException{
		listClients = new ListClients();
		listMessages = new ListMessages();
		messageID = 1;
		
	}
	
	public String getMessage (String clientID) throws RemoteException {
		return listMessages.getMessage(listClients.updateClient(clientID, messageID));
	}

	public void dropMessage (String clientID, String message) throws RemoteException {
		 listMessages.dropMessage(this.messageID, clientID, message);
		 messageID++;
	}

	
	/*** main ***/
	public static void main (String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Server server = new Server();
            Server stub =
                (Server) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("server bound");
        } catch (Exception e) {
            System.err.println("server exception:");
            e.printStackTrace();
        }
	}

	
}
