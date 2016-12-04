import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JFrame;

public class Maneger_ScreenThread extends Thread{
	Connection conn;
	Maneger_ScreenThread(Connection conn){
		this.conn  = conn;
	}
	public void run(){
		Maneger_Screen adminframe = new Maneger_Screen(conn);
		adminframe.setBounds(new Rectangle(1000, 700));
		adminframe.setVisible(true);
		adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
