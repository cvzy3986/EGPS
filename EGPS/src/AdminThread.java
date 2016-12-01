import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JFrame;

public class AdminThread extends Thread{
	Connection conn;
	AdminThread(Connection conn){
		this.conn  = conn;
	}
	public void run(){
		AdminFrame adminframe = new AdminFrame(conn);
		adminframe.setBounds(new Rectangle(1000, 700));
		adminframe.setVisible(true);
		adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		adminframe.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent e){
				    EGPS frame = new EGPS(false);
					frame.setBounds(new Rectangle(1468, 950));
					frame.setVisible(true);
					
			   }
		});
	}

}
