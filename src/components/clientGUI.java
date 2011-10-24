package components;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class clientGUI extends JPanel{
	
	protected JButton sendButton;
	protected JTextArea chatHistory;
	protected JTextArea chatInput;
	protected JPanel panel;
	
	//Constructor of the GUI
	public clientGUI(){
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
        
        panel.add(scrollerChatInput);
		panel.add(sendButton);
		add(scrollerChatHistory);
		add(panel);
	}
	
	//Create the Menu bar
	public static JMenuBar createMenuBar() {
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
        clientGUI newContentPane = new clientGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);      
				
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	//TO MODIFY AND REPLACE BY THE REMOTE METHOD
	public void dropMessage(){
		chatHistory.append(chatInput.getText());
		chatInput.setText("");
	}
	
	//Call the method dropMessage() when the action is performed
    public class sendButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent event){
			dropMessage();
    	}
    }
    
    //Call the method dropMessage() when the key ENTER is released
    public class chatInputListener implements KeyListener {
		public void keyReleased(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		  		dropMessage();
		  	}
		}		
		public void keyPressed(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
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
