package chat.client;

public class SettingsInfo {
	private String username, server;
	private int serverport, timeout, refreshrate;
	
	public int getRefreshrate() {
		return refreshrate;
	}
	public void setRefreshrate(int refreshrate) {
		this.refreshrate = refreshrate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getServerport() {
		return serverport;
	}
	public void setServerport(int serverport) {
		this.serverport = serverport;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public SettingsInfo(){}
	public SettingsInfo(String username, String server, int serverport, int timeout, int refreshrate){
		this.username = username;
		this.server = server;
		this.serverport = serverport;
		this.timeout = timeout;
		this.refreshrate = refreshrate;
	}

}
