import java.rmi.*;

public interface MessageServerInterface extends Remote {
	
	public String getMessage (String clientID) throws RemoteException;
	
	public void dropMessage (String clientID, String message) throws RemoteException;
}

