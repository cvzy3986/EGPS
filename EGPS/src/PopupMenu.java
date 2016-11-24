import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
public class PopupMenu extends	JFrame {
	public HashMap<Integer,JPopupMenu> pop;
	public PopupMenu(Connection conn){
		setPopMenuToButton(conn);
	}
	void setPopMenuToButton(Connection conn){
		pop =new HashMap<>();
		ArrayList<Integer> cids = new ArrayList<>(10);
		try {
			Statement stmt = conn.createStatement(); 
			ResultSet rset = stmt.executeQuery("Select cid from category");
			JMenuItem menuItem;
			while(rset.next()){
				pop.put(rset.getInt(1), new JPopupMenu());
				cids.add(rset.getInt(1));
			}
			PreparedStatement query = conn.prepareStatement("Select pname from product where cid = ?");
			for(int cid : cids){
				query.setInt(1, cid);
				rset = query.executeQuery();
				while(rset.next()){
					menuItem =new JMenuItem(rset.getString(1));
					menuItem.addActionListener(new MenuActionListener(conn));
					
					pop.get(cid).add(menuItem);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
