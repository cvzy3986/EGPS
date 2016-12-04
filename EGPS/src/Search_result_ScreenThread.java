import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Search_result_ScreenThread extends Thread{
	JTextField textField;
	Connection conn;
	Map_Screen mapImage;
	IsSearchActive SearchActive;
	Search_result_ScreenThread(JTextField textField,Connection conn,Map_Screen mapImage,IsSearchActive SearchActive){
		this.textField= textField;
		this.conn = conn;
		this.mapImage = mapImage;
		this.SearchActive = SearchActive;
	}
	public  void run(){
		 System.out.println("검색창 실행");
		 try {
				PreparedStatement query = conn.prepareStatement(
						"Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
				query.setString(1, "%" + textField.getText() + "%");
				ResultSet rset = query.executeQuery();
				if(!rset.next()){	//검색 결과가 없을때
					JOptionPane.showMessageDialog(null, "검색 결과가 존재하지 않습니다.");
					SearchActive.isActive=false;
					return;
				}
			} catch (SQLException sqle) {
				SearchActive.isActive=false;
				System.out.println("SQLException : " + sqle);
			}
		   JFrame frame2 = new Search_result_Screen(textField,conn,mapImage);
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