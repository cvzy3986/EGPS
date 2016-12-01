
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

/**
 * 
 * @author ParkJiyong
 * @brief 상품 추가 또는 리스트에서 선택한 상품을  수정, 삭제 클래스
 * @details 관리자 화면에서 새로운 상품을 추가로 등록 또는 리스트에서 선택한 상품의 정보 수정, 상품 삭제 시에 사용되는 클래스
 * @param table : 선택한 상품 리스트가 저장되어 있는 테이블
 * @param conn : DB와 연결해주는 커넥션
 * @param sel : 수정과 삭제를 구분해주기 위한 boolean 타입 변수
 */
// 선택한 물품 관리
 interface ProductAdmin {
	 public int change(Connection conn); // add

	public void change(JTable table, Connection conn, Boolean sel); // modify

	public void change(JTable table, Connection conn); // delete
}
 
public class OnePro_admin implements ProductAdmin {
	ProAdmin_form form;
	void setProductForm(ProAdmin_form form){
		this.form = form;
	
	}
	public int change(Connection conn) {
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
			JOptionPane.showMessageDialog(null, "추가되었습니다.");
			return 0;
		}catch (SQLException sqle){
			JOptionPane.showMessageDialog(null, "SQL 에러");
		}catch (FileNotFoundException imgaee){
			JOptionPane.showMessageDialog(null, "파일이 존재하지 않습니다.");
		}catch (NullPointerException nulle){
			JOptionPane.showMessageDialog(null, "이미지가 존재하지 않습니다.");
		}catch (NumberFormatException notInte){
			JOptionPane.showMessageDialog(null, "가격, x,y좌표는 숫자만 입력 가능합니다.");
		}
		return -1;
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

	//삭제
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