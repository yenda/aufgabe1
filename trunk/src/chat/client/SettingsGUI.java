package chat.client;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Laurine and Eric
 */
public class SettingsGUI extends JDialog {
	

	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel, serverLabel, timeoutLabel, refreshRateLabel;
	private JTextField jClientID, jServer, jTimeout, jRefreshrate;
	private static String clientID, server;
	private static int timeout, refreshrate;	

	public static String getClientID() {
		return clientID;
	}

	public static String getServer() {
		return server;
	}

	public static int getTimeout() {
		return timeout;
	}

	public static int getRefreshrate() {
		return refreshrate;
	}

	/**
	 * Builder
	 * @param fromFrame
	 * @param title
	 * @param modal
	 */
	public SettingsGUI(){
		//builder JDialog
		super((JFrame)null, "Settings", false);
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
	public SettingsGUI showSetdialog(){
		this.initComponent();
		this.setVisible(true);
		return this;		
	}
	
	/**
	 * Initialize the content of the box
	 */
	private void initComponent(){
		//username
		JPanel panUsername = new JPanel();
		panUsername.setBackground(Color.white);
		panUsername.setPreferredSize(new Dimension(220, 60));
		jClientID = new JTextField();
		jClientID.setPreferredSize(new Dimension(100, 25));
		panUsername.setBorder(BorderFactory.createTitledBorder("Username"));
		usernameLabel = new JLabel("Username :");
		panUsername.add(usernameLabel);
		panUsername.add(jClientID);
		jClientID.setText("User");
		
		//Server
		JPanel panServer = new JPanel();
		panServer.setBackground(Color.white);
		panServer.setPreferredSize(new Dimension(220, 60));
		jServer = new JTextField();
		jServer.setPreferredSize(new Dimension(100, 25));
		panServer.setBorder(BorderFactory.createTitledBorder("Server"));
		serverLabel = new JLabel("Server :");
		panServer.add(serverLabel);
		panServer.add(jServer);
		jServer.setText("localhost");
		
		//Timeout
		JPanel panTimeout = new JPanel();
		panTimeout.setBackground(Color.white);
		panTimeout.setPreferredSize(new Dimension(220, 60));
		jTimeout = new JTextField();
		jTimeout.setPreferredSize(new Dimension(100, 25));
		panTimeout.setBorder(BorderFactory.createTitledBorder("Timeout"));
		timeoutLabel = new JLabel("Timeout (in s)");
		panTimeout.add(timeoutLabel);
		panTimeout.add(jTimeout);
		jTimeout.setText("10");
		
		//Refresh rate
		JPanel panRefreshrate = new JPanel();
		panRefreshrate.setBackground(Color.white);
		panRefreshrate.setPreferredSize(new Dimension(220, 60));
		jRefreshrate = new JTextField();
		jRefreshrate.setPreferredSize(new Dimension(100, 25));
		panRefreshrate.setBorder(BorderFactory.createTitledBorder("Refresh rate :"));
		refreshRateLabel = new JLabel("Refresh rate");
		panRefreshrate.add(refreshRateLabel);
		panRefreshrate.add(jRefreshrate);
		jRefreshrate.setText("2");
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panUsername);
		content.add(panServer);
		content.add(panTimeout);
		content.add(panRefreshrate);
		
		JPanel control = new JPanel();
		JButton okButton = new JButton("OK");
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getValues();
			}	
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
			}
		});

		control.add(okButton);
		control.add(cancelButton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
		
	}
	
	//Set the values from the Settings box and create a new client with them
	public void getValues(){
		SettingsGUI.clientID = this.jClientID.getText();
		SettingsGUI.server = this.jServer.getText();
		String strTimeout = this.jTimeout.getText();
		String strRefreshrate = this.jRefreshrate.getText();			
		if((SettingsGUI.clientID != null) && (SettingsGUI.server != null) && (strTimeout != null) && (strRefreshrate != null))
			{
			try {
				Client.connexionServer();
				ClientGUI.setSendButton(true);
				try {	    
					SettingsGUI.refreshrate = Integer.parseInt(strRefreshrate);
					SettingsGUI.timeout = Integer.parseInt(strTimeout);
				    setVisible(false);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
				}
				Client.launchTimerRefreshRate();
			}
			catch (RemoteException e1){
				JOptionPane.showMessageDialog(null, "Connection to the server failed", "error", JOptionPane.ERROR_MESSAGE);
			}
			catch (NotBoundException e){
	            JOptionPane.showMessageDialog(null, "Connection to the server failed", "error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else
			JOptionPane.showMessageDialog(null, "Some fields are missing", "Error", JOptionPane.ERROR_MESSAGE);
	}	
}
