import java.rmi.*;
import java.rmi.server.*; // pour UnicastRemoteObject

public class Server extends UnicastRemoteObject implements MessageServerInterface {

	public Server() throws RemoteException{
		
	}
	
	public String getMessage(String clientID) throws RemoteException {
		return null;
	}

	public void dropMessage(String clientID, String message) throws RemoteException {
		
	}

}
