package chat.client;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClientGUI extends JPanel{
	
	protected JButton sendButton;
	protected JTextArea chatHistory;
	protected JTextArea chatInput;
	protected JPanel panel;
	protected static Client client;
	private Timer timer;
	
	//Constructor of the GUI
	private ClientGUI(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel = new JPanel();		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		sendButton = new JButton("Send");
		
		//Create a non editable TextArea for the chat history
		chatHistory = new JTextArea(20,20);
		chatHistory.setLineWrap(true);
		chatHistory.setEditable(false);
		
		//Create a TextArea for the chat input
		chatInput = new JTextArea(5,20); 
		chatInput.setLineWrap(true);
		
		//Listen to the actions "press the send button" and "press enter" to send the content of chat input
		sendButton.addActionListener(new sendButtonListener());
		chatInput.addKeyListener(new chatInputListener());
		
        //Put the chatHistory and chatInput textAreas in scroll panes.
        JScrollPane scrollerChatHistory = new JScrollPane(chatHistory);
        JScrollPane scrollerChatInput = new JScrollPane(chatInput);
        
        client = new Client();
        client.setTimeout(5000);
        timer = new Timer();
		timer.scheduleAtFixedRate(new getMessage(), 0, client.getTimeout());
        
        panel.add(scrollerChatInput);
		panel.add(sendButton);
		add(scrollerChatHistory);
		add(panel);
	}
	
	//Create the Menu bar
	private static JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
 
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menu);
 
        //a group of JMenuItems
        menuItem = new JMenuItem("Settings", KeyEvent.VK_S);
        menu.add(menuItem);
        
        menuItem.setActionCommand("Settings");
        //Action when we click on menuItem
        menuItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				SettingsGUI settings = new SettingsGUI(null, "Settings", false, client);
				settings.showSetdialog(); 
			}
        });
        
        return menuBar;
    }
	
	//Create and show the GUI
	private static void createAndShowGUI(){
		
		//Create and set up the window.
		JFrame frame = new JFrame("Message of the Day");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create and set up the menu bar.
        frame.setJMenuBar(createMenuBar());
		
        //Create and set up the content pane.
        ClientGUI newContentPane = new ClientGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);      
				
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	//TO MODIFY AND REPLACE BY THE REMOTE METHOD
	public void dropMessage(){
		//client.dropMessage(clientID, message);
		chatHistory.append(chatInput.getText());
		chatInput.setText("");
	}
	
	//Call the method dropMessage() when the action is performed
    private class sendButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent event){
			dropMessage();
    	}
    }
    
    //Call the method dropMessage() when the key ENTER is released
    private class chatInputListener implements KeyListener {
		public void keyReleased(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		  		dropMessage();
		  	}
		}		
		public void keyPressed(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
    }
    
    class getMessage extends TimerTask{
		public void run(){
			client.getMessage(client.getUsername());
			System.out.println("Messages reçus");
		}
	}
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    
 }
