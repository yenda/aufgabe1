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

	public static final int MAX_MESSAGES = 100;
	public static final long MAX_CLIENT_IDLE_TIME = 100;
	public static final int PORT = Registry.REGISTRY_PORT;
	
	private Timer timer;
	private Registry registry;
	
	private ListClients listClients;
	private ListMessages listMessages;
	
	private int messageID;
	
	private String logfile;
	private FileWriter fw;

	
	//Interface implementation
	public Server() throws RemoteException{
		super();
		this.listClients = new ListClients();
		this.listMessages = new ListMessages();
		this.messageID = 1;
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new cleanUpClientList(), 10000, 10000);
		this.logfile = "log.txt";
		try{
			fw = new FileWriter(logfile);
		}catch (IOException e){
			System.err.println("Logfile creation failed");
		}
		
        this.registry = LocateRegistry.createRegistry(PORT);
        this.registry.rebind("chatServer", (MessageServerInterface) UnicastRemoteObject.exportObject(this, 0));
        
        String input = "Server is online on port " + PORT; 
        logInput(input);
        input = "Client will timeout avec " + MAX_CLIENT_IDLE_TIME + " seconds of idle time";
        logInput(input);
        input = "Server will store the last " + MAX_MESSAGES + " last messages";
        logInput(input);
	}
	
	public synchronized String getMessage (String clientID) throws RemoteException {
		return listMessages.getMessage(listClients.updateClient(clientID, messageID));
	}

	public synchronized void dropMessage (String clientID, String message) throws RemoteException {
		 listMessages.dropMessage(this.messageID, clientID, message);
		 messageID++;
	}

	public synchronized void logInput (String input){
		try{
			input = new Date().toString() + " : " + input + "\n";
			fw.write(input);
			fw.flush();
			System.out.print(input);
		}catch (IOException e){
			System.err.println("logfile modification failed");
		}
	}
	
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
            System.err.println("server lauch failed:");
            e.printStackTrace();
        }

	}

	
}
