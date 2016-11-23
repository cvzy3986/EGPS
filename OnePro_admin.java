import javax.swing.JTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

//선택한 물품 관리
public class OnePro_admin{
	
	//추가
//	public void change(JTable table, Connection conn){
//	
//	}
	
	
	
	//modify
	public static void change(JTable table, Connection conn){
		// 수정 눌렸을 때
		int index = table.getSelectedRow(); // 선택된 테이블 번호 받아온다.
		// System.out.println("selected index : "+index);

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
			PopupMenu.setPopMenuToButton(conn);	//팝업 메뉴 갱신
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
	}

	
//	//delete
//	public static void change(JTable table, Connection conn)
//	{
//		int index = table.getSelectedRow(); // 선택된 테이블 번호 받아온다.
//		//System.out.println("selected index : " + index);
//
//		// 테이블에서 선택된 행의 pid를 구한다.
//		String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(0);
//		//System.out.println("selected pid : " + pid);
//
//		try {
//			EGPSAdmin.modelout.removeRow(index); // 선택한 행 내용 삭제
//			// 삭제 쿼리
//			PreparedStatement menuQuery = conn.prepareStatement("DELETE from product where pid = ?");
//			menuQuery.setString(1, pid);
//			System.out.println(menuQuery);
//			menuQuery.executeUpdate();
//			PopupMenu.setPopMenuToButton(conn);	//팝업 메뉴 갱신
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
}