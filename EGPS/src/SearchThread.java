import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

class SearchThread extends Thread{
	JTextField textField;
	Connection conn;
	MapImage mapImage;
	IsSearchActive SearchActive;
	SearchThread(JTextField textField,Connection conn,MapImage mapImage,IsSearchActive SearchActive){
		this.textField= textField;
		this.conn = conn;
		this.mapImage = mapImage;
		this.SearchActive = SearchActive;
	}
	public  void run(){
		 System.out.println("검색창 실행");
		   JFrame frame2 = new SearchFrame(textField,conn,mapImage);
		   frame2.setBounds(new Rectangle(1000, 800));
		   frame2.setVisible(true);
		   frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   frame2.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent e){
				   SearchActive.isActive=false;
			   }
		});
	   }
}