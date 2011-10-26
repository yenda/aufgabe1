package chat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;


import chat.MessageServerInterface;

/**
 * @author  Pierre-Henri and Eric
 */
@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements MessageServerInterface { 

	public static final int MAX_MESSAGES = 100;
	public static final long MAX_CLIENT_IDLE_TIME = 100;
	public static final int PORT = Registry.REGISTRY_PORT;
	
	private Timer timer;
	private ListClients listClients;
	/**
	 * @uml.property  name="listMessages"
	 * @uml.associationEnd  
	 */
	private ListMessages listMessages;
	private int messageID;

	
	//Interface implementation
	public Server() throws RemoteException{
		super();
		listClients = new ListClients();
		listMessages = new ListMessages();
		messageID = 1;
		timer = new Timer();
		timer.scheduleAtFixedRate(new cleanUpClientList(), 5000, 5000);
	}
	
	public String getMessage (String clientID) throws RemoteException {
		return listMessages.getMessage(listClients.updateClient(clientID, messageID));
	}

	public void dropMessage (String clientID, String message) throws RemoteException {
		 listMessages.dropMessage(this.messageID, clientID, message);
		 messageID++;
	}

	class cleanUpClientList extends TimerTask{
		public void run(){
			listClients.cleanUp();
			System.out.println("Nettoyage effectué");
		}
	}

	
	/*** main ***/
	public static void main (String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Server server = new Server();
            Registry registry = LocateRegistry.createRegistry(PORT);
            UnicastRemoteObject.unexportObject(server, true);
            registry.rebind("chatServer", (MessageServerInterface) UnicastRemoteObject.exportObject(server, 0));
            System.out.println("server bound");
        } catch (Exception e) {
            System.err.println("server exception:");
            e.printStackTrace();
        }

	}

	
}
