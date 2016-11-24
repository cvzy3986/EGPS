import javax.swing.JTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;


interface ProductAdmin
{ 
	public void change(); //add
	public void change(JTable table, Connection conn, Boolean sel); //modify
	public void change(JTable table, Connection conn); //delete
}

//������ ��ǰ ����
public class OnePro_admin implements ProductAdmin
{
	//add
	public void change()
	{
		
	}
	
	
	//modify
	public void change(JTable table, Connection conn, Boolean sel)	//sel = ��� ���п�)
	{
		int index = table.getSelectedRow(); // ���õ� ���̺� ��ȣ �޾ƿ´�.

		// ���̺��� ���õ� ���� ������ �޴´�.
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
			PopupMenu.setPopMenuToButton(conn);	//�˾� �޴� ����
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
	}

	
	//delete
	public  void change(JTable table, Connection conn)
	{
		int index = table.getSelectedRow(); // ���õ� ���̺� ��ȣ �޾ƿ´�.

		// ���̺��� ���õ� ���� pid�� ���Ѵ�.
		String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(0);
		
		try {
			EGPSAdmin.modelout.removeRow(index); // ������ �� ���� ����
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
	}
}