package chat.client;
import java.rmi.Naming;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
import chat.MessageServerInterface;

public class Client {
	//private static String clientID;
	//"obj" is the reference of the remote object
	private MessageServerInterface obj = null;
	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private String server;
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	
	private int timeout;
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	private int refreshrate;
	public int getRefreshrate() {
		return refreshrate;
	}
	public void setRefreshrate(int refreshrate) {
		this.refreshrate = refreshrate;
	}
	public String getMessage(String clientID)
	{
		try {
			obj = (MessageServerInterface)Naming.lookup("Server");
			return obj.getMessage(clientID);
		} catch (Exception e) {
			System.err.println("Rmi Client exception: " + e);
			e.printStackTrace();
			
			return e.getMessage();
		}
	}
	
	public void dropMessage(String clientID, String message)
	{
		try {
			obj = (MessageServerInterface)Naming.lookup("Server");
			obj.dropMessage(clientID,message);
		} catch (Exception e) {
			System.err.println("Rmi Client exception: " + e);
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
	}
	
	public Client()
	{		
	}
	
	public static void main(String[] args) {
		//Create and install a security manager
		if(System.getSecurityManager()== null){
			System.setSecurityManager(new SecurityManager());
		}
		
		Client client = new Client();
		
	}

}
