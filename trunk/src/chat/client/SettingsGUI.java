package chat.client;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author Laurine and Eric
 */
public class SettingsGUI extends JDialog {
	

	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel, serverLabel, timeoutLabel, refreshRateLabel;
	private JTextField clientID, server, timeout, refreshrate;

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
		clientID = new JTextField();
		clientID.setPreferredSize(new Dimension(100, 25));
		panUsername.setBorder(BorderFactory.createTitledBorder("Username"));
		usernameLabel = new JLabel("Username :");
		panUsername.add(usernameLabel);
		panUsername.add(clientID);
		
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
		panRefreshrate.setBorder(BorderFactory.createTitledBorder("Refresh rate :"));
		refreshRateLabel = new JLabel("Refresh rate");
		panRefreshrate.add(refreshRateLabel);
		panRefreshrate.add(refreshrate);
		
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
		String clientID = this.clientID.getText();
		String server = this.server.getText();
		String strTimeout = this.timeout.getText();
		String strRefreshrate = this.refreshrate.getText();
		
		if((clientID != null) && (server != null) && (strTimeout != null) && (strRefreshrate != null))
		{
			int refreshrate=0, timeout=0;
			try {	    
			    refreshrate = Integer.parseInt(strRefreshrate);
			    timeout = Integer.parseInt(strTimeout);
			    try{
			    	Client.timer.cancel();
			    }catch (NullPointerException e){
			    	
			    }
			    
			    ClientGUI.setClient(new Client(clientID,server,timeout,refreshrate*1000));
			    setVisible(false);
			  }catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
			  }			
		}
		else
			JOptionPane.showMessageDialog(null, "Some fields are missing", "Error", JOptionPane.ERROR_MESSAGE);
	}	
}
