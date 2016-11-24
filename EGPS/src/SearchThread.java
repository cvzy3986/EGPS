import java.awt.Rectangle;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

class SearchThread extends Thread{
	JTextField textField;
	Connection conn;
	MapImage mapImage;
	SearchThread(JTextField textField,Connection conn,MapImage mapImage){
		this.textField= textField;
		this.conn = conn;
		this.mapImage = mapImage;
	}
	public  void run(){
		   JFrame frame2 = new SearchFrame(textField,conn,mapImage);
		   frame2.setBounds(new Rectangle(1000, 500));
		   frame2.setVisible(true);
		   frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   System.out.println("검색창 실행");
		   
	   }
}