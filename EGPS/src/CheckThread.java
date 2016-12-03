import java.sql.Connection;

import javax.swing.JFrame;

public class CheckThread  extends Thread{
	Connection conn;
	CheckThread(Connection conn){
		this.conn=conn;
	}
	public void run(){
		Check frame = new Check(conn);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
