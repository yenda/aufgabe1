import java.rmi.*;
import java.rmi.server.*; // for UnicastRemoteObject

public class Server extends UnicastRemoteObject implements MessageServerInterface { 
	
	//Interface implementation
	public Server() throws RemoteException{ }
	
	public String getMessage(String clientID) throws RemoteException {
		return null;
	}

	public void dropMessage(String clientID, String message) throws RemoteException {
		 
	}

	
	/*** main ***/
	public static void main (String[] args) {
		try {
			MessageServerInterface service = new Server();
			Naming.rebind("", service);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
