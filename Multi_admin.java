import javax.swing.JTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

//���̺��� ��� ��ǰ ����
public class Multi_admin extends OnePro_admin{
//	//modify
//	public static void change(Connection conn){
//		// ���� ������ ��
//		int count = EGPSAdmin.modelout.getRowCount();
//		while (count-- != 0)
//		{
//			// ���̺��� ���õ� ���� ������ �޴´�.
//			int index = (EGPSAdmin.modelout.getRowCount()-1) - count;
//			System.out.println("���� ��  : "+index);
//			String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(0);
//			String pname = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(1);
//			String cost = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(2);
//			String floor = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(3);
//			String category = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(4);
//			String cid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(5);
//			String x = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(6);
//			String y = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(7);
//			String URL = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(8);
//			
//			try {
//				PreparedStatement menuQuery = conn.prepareStatement(
//						"UPDATE product SET pname = ?, cost = ?, floor = ?, category = ?, cid = ?, x = ?, y = ?, URL = ? where pid = ?");
//	
//				menuQuery.setString(1, pname);
//				menuQuery.setString(2, cost);
//				menuQuery.setString(3, floor);
//				menuQuery.setString(4, category);
//				menuQuery.setString(5, cid);
//				menuQuery.setString(6, pid);
//				menuQuery.setString(6, x);
//				menuQuery.setString(7, y);
//				menuQuery.setString(8, URL);
//				menuQuery.setString(9, pid);
//	
//				System.out.println(menuQuery);
//				menuQuery.executeUpdate();
//				PopupMenu.setPopMenuToButton(conn);	//�˾� �޴� ����
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			};
//		}
//	}

	
	//delete
	public static void change(Connection conn)
	{
		while (EGPSAdmin.modelout.getRowCount() != 0)
		{
			try {
				// ���̺��� ���õ� ���� pid�� ���Ѵ�.
				String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(0)).elementAt(0);
				
				// ���� ����
				PreparedStatement menuQuery = conn.prepareStatement("DELETE from product where pid = ?");
				menuQuery.setString(1, pid);
				System.out.println(menuQuery);
				menuQuery.executeUpdate();
				PopupMenu.setPopMenuToButton(conn);	//�˾� �޴� ����
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			EGPSAdmin.modelout.removeRow(0);
		}
	}
}
