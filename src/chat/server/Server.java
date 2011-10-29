package chat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import java.io.FileWriter;
import java.io.IOException;

import chat.MessageServerInterface;

/**
 * @author  Pierre-Henri and Eric
 */
public class Server implements MessageServerInterface { 

	public static final int MAX_MESSAGES = 10;
	public static final long MAX_CLIENT_IDLE_TIME = 100;
	public static final int PORT = Registry.REGISTRY_PORT;
		
	private Timer timer;
	private Registry registry;
	
	private ListClients listClients;
	private ListMessages listMessages;
	
	private int messageID;
	
	private String logfile;
	private static FileWriter fw;

	
	/**
	 * Interface implementation
	 * @throws RemoteException
	 */
	public Server() throws RemoteException{
		super();
		this.listClients = new ListClients();
		this.listMessages = new ListMessages();
		this.messageID = 0;
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new cleanUpClientList(), 10000, 10000);
		this.logfile = "log.txt";
		try{
			fw = new FileWriter(logfile);
		}catch (IOException e){
			System.err.println("Logfile creation failed");
		}
		
        this.registry = LocateRegistry.createRegistry(PORT);
        this.registry.rebind("MessageServer", (MessageServerInterface) UnicastRemoteObject.exportObject(this, 0));
        
        String input = "Server is online on port " + PORT; 
        logInput(input);
        input = "Client will timeout avec " + MAX_CLIENT_IDLE_TIME + " seconds of idle time";
        logInput(input);
        input = "Server will store the last " + MAX_MESSAGES + " last messages";
        logInput(input);
	}
	
	/**
	 * Grab all the messages the client has not received yet and return them to him into a String
	 * @param String clientID : ID of the client
	 * @throws RemoteException
	 * @throws RemoteException ("no more messages") when there is no new messages for the client
	 * @return String : the messages the client has not received yet
	 */
	public synchronized String getMessage (String clientID) throws RemoteException {
		logInput(clientID+" received : ");
		return listMessages.getMessage(listClients.updateClient(clientID, messageID+1));
	}
	
	/**
	 * Add the message sent by the client to the list of messages on the server
	 * @param String clientID : ID of the client
	 * @param String message : the message of the client
	 * @throws RemoteException
	 */
	public synchronized void dropMessage (String clientID, String message) throws RemoteException {
		 this.messageID++;
		 listMessages.dropMessage(this.messageID, clientID, message);
		 
	}

	/**
	 * Print in a logfile and on the console the string it gets in the parameters
	 * Is called every time an important event occurs on the server
	 * @param String input
	 * @throws IOException when an error occurs when the file writer try to write on the logfile
	 */
	public static synchronized void logInput (String input){
		try{
			input = new Date().toString() + " : " + input + "\n";
			fw.write(input);
			fw.flush();
			System.out.print(input);
		}catch (IOException e){
			System.err.println("logfile modification failed");
		}
	}
	
	/**
	 * Is called periodically (every 10 seconds)
	 * Delete all the client from the list whose timestamp is too old according to the constant MAX_CLIENT_IDLE_TIME
	 */
	class cleanUpClientList extends TimerTask{
		public void run(){
			int deleted = listClients.cleanUp();
			String input = "Cleaning of client base processed : " + deleted + " client(s) deleted after timeout";
			logInput(input);
		}
	}

	
	/*** main ***/
	public static void main (String[] args) throws RemoteException{
		try {
            new Server();
        } catch (Exception e) {
            System.err.println("Server lauch failed");
        }

	}

	
}
