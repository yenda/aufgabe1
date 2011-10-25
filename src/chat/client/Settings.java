package chat.client;

import javax.swing.*;
import java.awt.*;

//Box settings
public class Settings extends JDialog {
	
	private SettingsInfo settInfo = new SettingsInfo();
	private JLabel usernameLabel, serverLabel, serverPortLabel, timeoutLabel, refreshRateLabel;
	private JTextField username, server, serverport, timeout, refreshrate;

	/**
	 * Builder
	 * @param fromFrame
	 * @param title
	 * @param modal
	 */
	public Settings(JFrame fromFrame, String title, boolean modal){
		//builder JDialog
		super(fromFrame, title, modal);
		//size
		this.setSize(300, 400);
		//position
		this.setLocationRelativeTo(null);
		//not resizable
		this.setResizable(false);
		
		this.setVisible(true);
	}
	
	/**
	 * Method which is called to use the box 
	 * @return settInfo
	 */
	public SettingsInfo showSetdialog(){
		this.initComponent();
		this.setVisible(true);
		return this.settInfo;		
	}
	
	/**
	 * Initialize the content of the box
	 */
	private void initComponent(){
		//username
		JPanel panUsername = new JPanel();
		panUsername.setBackground(Color.white);
		panUsername.setPreferredSize(new Dimension(220, 60));
		username = new JTextField();
		username.setPreferredSize(new Dimension(100, 25));
		panUsername.setBorder(BorderFactory.createTitledBorder("Username"));
		usernameLabel = new JLabel("Username :");
		panUsername.add(usernameLabel);
		panUsername.add(username);
		
		//Server
		JPanel panServer = new JPanel();
		panServer.setBackground(Color.white);
		panServer.setPreferredSize(new Dimension(220, 60));
		server = new JTextField();
		server.setPreferredSize(new Dimension(100, 25));
		panServer.setBorder(BorderFactory.createTitledBorder("Server"));
		serverLabel = new JLabel("Server :");
		panServer.add(serverLabel);
		panServer.add(server);
		
		//Serverport
		JPanel panServerPort = new JPanel();
		panServerPort.setBackground(Color.white);
		panServerPort.setPreferredSize(new Dimension(220, 60));
		serverport = new JTextField();
		serverport.setPreferredSize(new Dimension(100, 25));
		panServerPort.setBorder(BorderFactory.createTitledBorder("Serverport"));
		serverPortLabel = new JLabel("Serverport :");
		panServerPort.add(serverPortLabel);
		panServerPort.add(serverport);
		
		//Timeout
		JPanel panTimeout = new JPanel();
		panTimeout.setBackground(Color.white);
		panTimeout.setPreferredSize(new Dimension(220, 60));
		timeout = new JTextField();
		timeout.setPreferredSize(new Dimension(100, 25));
		panTimeout.setBorder(BorderFactory.createTitledBorder("Timeout"));
		timeoutLabel = new JLabel("Timeout (in s)");
		panTimeout.add(timeoutLabel);
		panTimeout.add(timeout);
		
		//Refresh rate
		JPanel panRefreshrate = new JPanel();
		panRefreshrate.setBackground(Color.white);
		panRefreshrate.setPreferredSize(new Dimension(220, 60));
		refreshrate = new JTextField();
		refreshrate.setPreferredSize(new Dimension(100, 25));
		panRefreshrate.setBorder(BorderFactory.createTitledBorder("Refresh rate"));
		refreshRateLabel = new JLabel("Refresh rate");
		panRefreshrate.add(refreshRateLabel);
		panRefreshrate.add(refreshrate);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panUsername);
		content.add(panServer);
		content.add(panServerPort);
		content.add(panTimeout);
		content.add(panRefreshrate);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		
		JButton cancelBouton = new JButton("Annuler");

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
