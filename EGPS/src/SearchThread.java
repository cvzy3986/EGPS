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
		 System.out.println("�˻�â ����");
		 try {
				PreparedStatement query = conn.prepareStatement(
						"Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
				query.setString(1, "%" + textField.getText() + "%");
				ResultSet rset = query.executeQuery();
				if(!rset.next()){	//�˻� ����� ������
					JOptionPane.showMessageDialog(null, "�˻� ����� �������� �ʽ��ϴ�.");
					SearchActive.isActive=false;
					return;
				}
			} catch (SQLException sqle) {
				SearchActive.isActive=false;
				System.out.println("SQLException : " + sqle);
			}
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