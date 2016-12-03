
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import java.sql.Connection;

public class AddScreenThread extends Thread{
	Connection conn;
	IsAdminActive adminActive;
	AddScreenThread(Connection conn,IsAdminActive adminActive){
		this.conn = conn;
		this.adminActive = adminActive;
	}
	public void run(){
		Add_Screen addComp = new Add_Screen(conn);
		addComp.setBounds(new Rectangle(1100, 1000));			
		addComp.setVisible(true);
		addComp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComp.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent e){
				   adminActive.isAdd=false;
			   }
		});
	}
}