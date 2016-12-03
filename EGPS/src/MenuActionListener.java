import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @brief 상품아이템 당 선택 시 발생되는 이벤트클래스
 * @details 특정 상품 클릭 시 해당 상품명의 pname(상품명),cost(가격),image(상품이미지),URL(해당 상품 URL),x,y(상품 위치) 정보를 가져온다.
 * @author user
 *
 */
class MenuActionListener implements ActionListener {
	private Connection conn;
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
//					EGPS.textCost.setText(Integer.toString((EGPS.PRODUCT.cost)));
					EGPS.textCost.setText(String.format("%,d 원",EGPS.PRODUCT.cost));;
					
					EGPS.PRODUCT.setURL(rset.getString(4));
				    EGPS.PRODUCT.setX(rset.getInt(5));
				    EGPS.PRODUCT.setY(rset.getInt(6));
					EGPS.panel.repaint();
					EGPS.mapImage.repaint();
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
