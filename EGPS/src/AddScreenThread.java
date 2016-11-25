
import java.awt.Rectangle;
import javax.swing.JFrame;

import java.sql.Connection;

public class AddScreenThread extends Thread{
	Connection conn;
	AddScreenThread(Connection conn){
		this.conn = conn;
	}
	public void run(){
		Add_Screen addComp = new Add_Screen(conn);
		addComp.setBounds(new Rectangle(600, 500));			
		addComp.setVisible(true);
		addComp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}