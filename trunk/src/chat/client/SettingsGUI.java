package chat.client;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Timer;

//Box settings
public class SettingsGUI extends JDialog {
	
	/**
	 * @author Laurine
	 */
	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel, serverLabel, timeoutLabel, refreshRateLabel;
	private JTextField username, server, timeout, refreshrate;
	private Client client;

	/**
	 * Builder
	 * @param fromFrame
	 * @param title
	 * @param modal
	 */
	public SettingsGUI(JFrame fromFrame, String title, boolean modal, Client client){
		//builder JDialog
		super(fromFrame, title, modal);
		//size
		this.setSize(300, 400);
		//position
		this.setLocationRelativeTo(null);
		//not resizable
		this.setResizable(false);
		this.client=client;
		
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
				//Récupérer valeurs saisies?
				if((getUsername()) && (getServer()) && (getTimeout()) && (getRefreshrate())){
					ClientGUI.timer = new Timer();
		            ClientGUI.timer.scheduleAtFixedRate(new ClientGUI.getMessage(), 0, client.getRefreshrate());
					setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
			}
			public boolean getUsername() {
				String Username = username.getText();
				if((Username != null) && (Username.length() > 0)){
					client.setUsername(Username);
					return true;
				}
				else
					return false;
			}
			public boolean getServer() {
				if((server.getText() != null) && (server.getText().length() > 0)){
					client.setServer(server.getText());
					return true;
				}
				else
					return false;
			}
			public boolean getTimeout(){
				if((timeout.getText() != null))
				{
					int Timeout;
					try {	    
					    Timeout = Integer.parseInt(timeout.getText());
					  }
					catch (NumberFormatException e) {
					    return false;
					  }
					client.setTimeout(Timeout);
					return true;
				}
				else
					return false;
			}
			public boolean getRefreshrate(){
				if((refreshrate.getText() != null))
				{
					int Refreshrate;
					try {	    
					    Refreshrate = Integer.parseInt(refreshrate.getText());
					  }
					catch (NumberFormatException e) {
					    return false;
					  }
					client.setRefreshrate(Refreshrate);
					return true;
				}
				else
					return false;
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
}
