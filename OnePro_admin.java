import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

interface ProductAdmin {
	public void change(Connection conn); // add

	public void change(JTable table, Connection conn, Boolean sel); // modify

	public void change(JTable table, Connection conn); // delete
}

// 선택한 물품 관리
public class OnePro_admin implements ProductAdmin {
	// add
	public void change(Connection conn) {
		ProAdmin_form product = new ProAdmin_form();
		String pid = product.getPid();
		String pname = product.getPname();
		String cost = product.getCost();
		String floor = product.getFloor();
		String category = product.getCategory();
		String cid = product.getCid();
		String x = product.getX();
		String y = product.getY();
		String URL = product.getURL();
		String image = product.getImage().toString();

		PreparedStatement menuQuery;
		try {
			menuQuery = conn.prepareStatement(
					"INSERT INTO product(pid, pname, cost, floor, category, cid, image, x, y, URL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			menuQuery.setString(1, pid);
			menuQuery.setString(2, pname);
			menuQuery.setString(3, cost);
			menuQuery.setString(4, floor);
			menuQuery.setString(5, category);
			menuQuery.setString(6, cid);
			menuQuery.setString(7, image);
			menuQuery.setString(8, x);
			menuQuery.setString(9, y);
			menuQuery.setString(10, URL);

			System.out.println(menuQuery);
			menuQuery.executeUpdate();
			PopupMenu.setPopMenuToButton(conn); // 팝업 메뉴 갱신
			System.out.println(menuQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// JOptionPane.showMessageDialog(null, "추가 되었습니다.");
		// } catch (SQLException sqle){
		// JOptionPane.showMessageDialog(null, "카테고리 추가 오류.");
		// } catch (NumberFormatException nfe){
		// JOptionPane.showMessageDialog(null, "cid floor 숫자입력.");
		// }
	}

	// modify
	public void change(JTable table, Connection conn, Boolean sel) // sel = 기능
																	// 구분용)
	{
		int index = table.getSelectedRow(); // 선택된 테이블 번호 받아온다.

		// 테이블에서 선택된 행의 정보를 받는다.
		String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(0);
		String pname = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(1);
		String cost = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(2);
		String floor = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(3);
		String category = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(4);
		String cid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(5);
		String x = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(6);
		String y = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(7);
		String URL = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(8);

		try {
			PreparedStatement menuQuery = conn.prepareStatement(
					"UPDATE product SET pname = ?, cost = ?, floor = ?, category = ?, cid = ?, x = ?, y = ?, URL = ? where pid = ?");

			menuQuery.setString(1, pname);
			menuQuery.setString(2, cost);
			menuQuery.setString(3, floor);
			menuQuery.setString(4, category);
			menuQuery.setString(5, cid);
			menuQuery.setString(6, pid);
			menuQuery.setString(6, x);
			menuQuery.setString(7, y);
			menuQuery.setString(8, URL);
			menuQuery.setString(9, pid);

			System.out.println(menuQuery);
			menuQuery.executeUpdate();
			PopupMenu.setPopMenuToButton(conn); // 팝업 메뉴 갱신
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;
	}

	// delete
	public void change(JTable table, Connection conn) {
		int index = table.getSelectedRow(); // 선택된 테이블 번호 받아온다.

		// 테이블에서 선택된 행의 pid를 구한다.
		String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(0);

		try {
			EGPSAdmin.modelout.removeRow(index); // 선택한 행 내용 삭제
			// 삭제 쿼리
			PreparedStatement menuQuery = conn.prepareStatement("DELETE from product where pid = ?");
			menuQuery.setString(1, pid);
			System.out.println(menuQuery);
			menuQuery.executeUpdate();
			PopupMenu.setPopMenuToButton(conn); // 팝업 메뉴 갱신
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}