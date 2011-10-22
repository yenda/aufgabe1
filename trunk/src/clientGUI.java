import javax.swing.*;
import java.awt.event.*;

public class clientGUI implements ActionListener{
	JButton button;

	public static void main (String[] args){
		clientGUI gui = new clientGUI();
		gui.start();
	}
	
	public void start(){
		JFrame frame = new JFrame();
		button = new JButton("click me");
		
		button.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(button);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		button.setText("Clicked");
	}
}
