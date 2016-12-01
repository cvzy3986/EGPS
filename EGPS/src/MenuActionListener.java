import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @brief ��ǰ������ �� ���� �� �߻��Ǵ� �̺�ƮŬ����
 * @details Ư�� ��ǰ Ŭ�� �� �ش� ��ǰ���� pname(��ǰ��),cost(����),image(��ǰ�̹���),URL(�ش� ��ǰ URL),x,y(��ǰ ��ġ) ������ �����´�.
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
					EGPS.textCost.setText(String.format("%,d ��",EGPS.PRODUCT.cost));;
					
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
