package chat.client;


import chat.MessageServerInterface;

import java.rmi.AccessException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

/**
 * @author Laurine and Eric
 */
public class Client {
	public static Timer timerRefrehRate;
	public static Timer timerTimeout;
	public static boolean timerRunning = false;
	public static boolean serverOnline = false;
	
	public static MessageServerInterface serverMsg = null;
	public static Registry reg = null;
	public static final int PORT = Registry.REGISTRY_PORT;
	
	public static void launchTimerRefreshRate(){
		try{
	    	Client.timerRefrehRate.cancel();
	    }catch (NullPointerException e){
	    	Client.timerRefrehRate = new Timer();
	    	Client.timerRefrehRate.scheduleAtFixedRate(new ClientGUI.getMessage(), 0, SettingsGUI.getRefreshrate()*1000);
	    }
	}
	

	
    static class serverFailure extends TimerTask{
		public void run(){
			JOptionPane.showMessageDialog(null, "Server is not responding anymore, choose an onther one in the settings", "error", JOptionPane.ERROR_MESSAGE);
		}
    }
	
	public static String getMessage()
	{
		Client.testConnection();
		try {
			return Client.serverMsg.getMessage(SettingsGUI.getClientID());
		} catch (RemoteException e) {
			if (e instanceof ConnectException){
				Client.serverOnline = false;
			}
			return "";
		}
	}
	
	public static void dropMessage(String message)
	{
		Client.testConnection();
		try {
			Client.serverMsg.dropMessage(SettingsGUI.getClientID(),message);
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Call of function dropMessage failed", "error", JOptionPane.ERROR_MESSAGE);
			Client.serverOnline = false;
		}
	}
	
	public static void connexionServer() throws RemoteException, NotBoundException, AccessException{
        reg = LocateRegistry.getRegistry(SettingsGUI.getServer(), PORT);               
        Client.serverMsg = (MessageServerInterface) reg.lookup("chatServer");
        serverOnline = true;
	}
	
	public static void testConnection(){
		if (serverOnline == false){
			try {
				Client.connexionServer();
				serverOnline = true;
				if (timerRunning == true)
					timerTimeout.cancel();
			}
			catch (RemoteException e1){
				if (timerRunning == false){
					Client.timerTimeout = new Timer();
					Client.timerTimeout.scheduleAtFixedRate(new Client.serverFailure(), 0, SettingsGUI.getTimeout()*1000);
					timerRunning = true;
				}
			}
			catch (NotBoundException e){
				if (timerRunning == false){
					Client.timerTimeout = new Timer();
					Client.timerTimeout.scheduleAtFixedRate(new Client.serverFailure(), 0, SettingsGUI.getTimeout()*1000);
					timerRunning = true;
				}
			}
		}
	}
	
    public static void main(String[] args) {
        ClientGUI.createAndShowGUI();	
    }
}
