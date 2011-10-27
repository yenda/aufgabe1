package chat.client;


import chat.MessageServerInterface;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Timer;

import javax.swing.JOptionPane;

/**
 * @author Laurine and Eric
 */
public class Client {
	public static Timer timerRefrehRate;
	public static Timer timerTimeout;
	
	public static MessageServerInterface serverMsg;
	public static Registry reg = null;
	public static final int PORT = Registry.REGISTRY_PORT;
	
	
	public static String getMessage(String clientID)
	{
		try {
			return serverMsg.getMessage(clientID);
		} catch (Exception e) {			
			return "Exception in getMessage: " + e.getMessage();
		}
	}
	
	public static void launchTimerRefreshRate(){
		try{
	    	Client.timerRefrehRate.cancel();
	    }catch (NullPointerException e){
	    	Client.timerRefrehRate = new Timer();
	    	Client.timerRefrehRate.scheduleAtFixedRate(new ClientGUI.getMessage(), 0, SettingsGUI.getRefreshrate()*1000);
	    }
	}
	
	public static synchronized String getMessage()
	{
		try {
			return Client.serverMsg.getMessage(SettingsGUI.getClientID());
		} catch (RemoteException e) {
			return "Exception in Client.getMessage" + e;
		}
	}
	
	public static void dropMessage(String message)
	{
		try {
			Client.serverMsg.dropMessage(SettingsGUI.getClientID(),message);
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Call of function dropMessage failed", "error", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	public static void connexionServer() throws RemoteException, NotBoundException, AccessException{
        reg = LocateRegistry.getRegistry(SettingsGUI.getServer(), PORT);
        Client.serverMsg = (MessageServerInterface) reg.lookup("chatServer");
	}
	
    public static void main(String[] args) {
        ClientGUI.createAndShowGUI();	
    }
}
