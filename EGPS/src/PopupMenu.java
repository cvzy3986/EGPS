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
class MenuActionListener implements ActionListener {
	/**
	 * @brief 상품아이템 당 선택 시 발생되는 이벤트클래스
	 * @details 특정 상품 클릭 시 해당 상품명의 pname(상품명),cost(가격),image(상품이미지),URL(해당 상품 URL),x,y(상품 위치) 정보를 가져온다.
	 */
	Connection conn;
	MenuActionListener(Connection conn){
		this.conn = conn;
	}
	  public void actionPerformed(ActionEvent e) {
		  try {
				PreparedStatement menuQuery = conn.prepareStatement("Select pname,cost,image,URL,x,y from product where pname = ?");
				String pstr = e.getActionCommand();
				menuQuery.setString(1, pstr);
				System.out.println(menuQuery);
				ResultSet rset = menuQuery.executeQuery();
				while(rset.next()){
					EGPS.PRODUCT.setPname(rset.getString(1));
					EGPS.PRODUCT.setCost(rset.getInt(2));
					EGPS.PRODUCT.setPimage(ReturnProductImage.returnImage(rset.getBlob(3)));
					EGPS.textPname.setText(EGPS.PRODUCT.pname);
					EGPS.textCost.setText(Integer.toString((EGPS.PRODUCT.cost)));
					EGPS.PRODUCT.setURL(rset.getString(4));
				    EGPS.PRODUCT.setX(rset.getInt(5));
				    EGPS.PRODUCT.setY(rset.getInt(6));
					EGPS.panel.repaint();
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

class ReturnProductImage{
	static Image returnImage(Blob data) throws Exception{
		Image temp = ImageIO.read(data.getBinaryStream());
		Image temp2 = temp.getScaledInstance(305, 276, Image.SCALE_SMOOTH);
		return temp2;
	}
}
