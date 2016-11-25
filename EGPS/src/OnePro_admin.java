
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.mysql.jdbc.Blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;


// 선택한 물품 관리
 interface ProductAdmin {
	public void change(Connection conn); // add

	public void change(JTable table, Connection conn, Boolean sel); // modify

	public void change(JTable table, Connection conn); // delete
}
 
public class OnePro_admin implements ProductAdmin {
	ProAdmin_form form;
	void setProductForm(ProAdmin_form form){
		this.form = form;
	
	}
	public void change(Connection conn) {
		PreparedStatement menuQuery;
		
		try {
			menuQuery = conn.prepareStatement(
					"INSERT INTO product(pid, pname, cost, floor, category, cid, image, x, y, URL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			menuQuery.setString(1, form.getPid());
			menuQuery.setString(2, form.getPname());
			menuQuery.setString(3, form.getCost());
			menuQuery.setString(4, form.getFloor());
			menuQuery.setString(5, form.getCategory());
			menuQuery.setString(6, form.getCid());
			FileInputStream fin = new FileInputStream(form.getImage());
			menuQuery.setBinaryStream(7,fin,form.getImage().length());
			menuQuery.setString(8, form.getX());
			menuQuery.setString(9, form.getY());
			menuQuery.setString(10,form.getURL());

			System.out.println(menuQuery);
			menuQuery.executeUpdate();
			PopupMenu.setPopMenuToButton(conn); // 팝업 메뉴 갱신

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	// modify
	public void change(JTable table, Connection conn, Boolean sel) // sel = 기능
																	// 구분용)
	{
		int index = table.getSelectedRow(); // 선택된 테이블 번호 받아온다.

		// 테이블에서 선택된 행의 정보를 받는다.
		String pid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(0);
		String pname = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(1);
		String cost = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(2);
		String floor = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(3);
		String category = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(4);
		String cid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(5);
		String x = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(6);
		String y = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(7);
		String URL = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(8);

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
		String pid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(0);

		try {
			AdminFrame.modelout.removeRow(index); // 선택한 행 내용 삭제
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