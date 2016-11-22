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
	HashMap<Integer,JPopupMenu> pop;
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
					menuItem.addActionListener(new MenuActionListenerAdmin(conn));
					
					pop.get(cid).add(menuItem);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
class MenuActionListener implements ActionListener {
	Connection conn;
	MenuActionListener(Connection conn){
		this.conn = conn;
	}
	  public void actionPerformed(ActionEvent e) {
		  try {
				PreparedStatement menuQuery = conn.prepareStatement("Select pname,cost,image,URL from product where pname = ?");
				String pstr = e.getActionCommand();
				menuQuery.setString(1, pstr);
				System.out.println(menuQuery);
				ResultSet rset = menuQuery.executeQuery();
				while(rset.next()){
//					EGPS.PRODUCT.setPname(rset.getString(1));
//					EGPS.PRODUCT.setCost(rset.getInt(2));
//					EGPS.PRODUCT.setPimage(ReturnProductImage.returnImage(rset.getBlob(3)));
//					EGPS.textPname.setText(EGPS.PRODUCT.pname);
//					EGPS.textCost.setText(Integer.toString((EGPS.PRODUCT.cost)));
//					EGPS.PRODUCT.setURL(rset.getString(4));
//					EGPS.panel.repaint();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
	}
class MenuActionListenerAdmin extends MenuActionListener{

	MenuActionListenerAdmin(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	 public void actionPerformed(ActionEvent e) {
		  try {
//			    while(EGPSAdmin.modelout.getRowCount() != 0)
//			    	EGPSAdmin.modelout.removeRow(0);
				PreparedStatement menuQuery = conn.prepareStatement("Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
				String pstr = e.getActionCommand();
				menuQuery.setString(1, pstr);
				System.out.println(menuQuery);
				ResultSet rset = menuQuery.executeQuery();
				ArrayList<String> row = new ArrayList<>();
				while(rset.next()){
					row.add(rset.getString(1)); //pid
					row.add(rset.getString(2));	//pname
					row.add(rset.getString(3));	//cost
					row.add(rset.getString(4));	//floor
					row.add(rset.getString(5));	//category
					row.add(rset.getString(6));	//cid
					row.add(rset.getString(7));	//x
					row.add(rset.getString(8));	//y
					row.add(rset.getString(9));	//URL
					EGPSAdmin.modelout.addRow(row.toArray());
					row.clear();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
}
class ReturnProductImage{
	static Image returnImage(Blob data) throws Exception{
		Image temp = ImageIO.read(data.getBinaryStream());
		Image temp2 = temp.getScaledInstance(305, 276, Image.SCALE_SMOOTH);
		return temp2;
	}
}
