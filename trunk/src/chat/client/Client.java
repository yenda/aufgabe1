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
	public static boolean timedOut = false;
	
	public static MessageServerInterface serverMsg = null;
	public static Registry reg = null;
	public static final int PORT = Registry.REGISTRY_PORT;
	
	/**
	 * Schedule a call to the getMessage remote method periodically according
	 * to the refresh rate defined in Settings
	 */
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
			timedOut = true;
			JOptionPane.showMessageDialog(null, "Server is not responding anymore, choose an onther one in the settings", "Error", JOptionPane.ERROR_MESSAGE);
			timerRefrehRate.cancel();
		}
    }

	
    /**
     * Get the new messages on the server
     * This method has a MAYBE failure semantic.
     * If there is a connection failure the variable serverOnline is turned to false
     * @return return the new messages or nothing if an exception is thrown
     */
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
	
	/**
	 * Drop the message on the server
	 * This method has an AT LEAST ONCE failure semantic
	 * If there is a connection failure the variable serverOnline is turned to false
	 * @param message
	 */
	public static void dropMessage(String message)
	{
		Client.testConnection();
		try {
			Client.serverMsg.dropMessage(SettingsGUI.getClientID(),message);
		} catch (Exception e) {
			Client.serverOnline = false;
			if (!timedOut){
				try{
					Thread.sleep(SettingsGUI.getTimeout()*100);
				}
				catch(Exception ex){}
				dropMessage(message);		
			}
		}
	}
	
	/**
	 * Connect the client to the server defined in the settings
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws AccessException
	 */
	public static void connexionServer() throws RemoteException, NotBoundException, AccessException{
        reg = LocateRegistry.getRegistry(SettingsGUI.getServer(), PORT);               
        Client.serverMsg = (MessageServerInterface) reg.lookup("MessageServer");
        serverOnline = true;
	}
	
	/**
	 * Test if the connection with the server is still up
	 * This function is called before the use of a remote method
	 * If the remote method throws a connection exception serverOnline is turned to false
	 * When serverOnline is true the method don't do anything
	 * When serverOnline is false the method try to reconnect and cancel the timerTimeout if the reconnection is successful
	 * If not the call of the method serverFailure is schedule for after a period defined in the settings
	 */
	public static void testConnection(){
		if (serverOnline == false){
			try {
				Client.connexionServer();
				serverOnline = true;
				ClientGUI.setSendButton(true);
				if (timerRunning == true)
					timerTimeout.cancel();
			}
			catch (RemoteException e1){
				if (timerRunning == false){
					Client.timerTimeout = new Timer();
					Client.timerTimeout.schedule(new Client.serverFailure(), SettingsGUI.getTimeout()*1000);
					timerRunning = true;
					ClientGUI.setSendButton(false);
				}
			}
			catch (NotBoundException e){
				if (timerRunning == false){
					Client.timerTimeout = new Timer();
					Client.timerTimeout.schedule(new Client.serverFailure(), SettingsGUI.getTimeout()*1000);
					timerRunning = true;
					ClientGUI.setSendButton(false);
				}
			}
		}
	}
	
    public static void main(String[] args) {
        ClientGUI.createAndShowGUI();	
    }
}
