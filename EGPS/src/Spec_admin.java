import javax.swing.JTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

//���õ� ��ǰ�� ����
public class Spec_admin extends  OnePro_admin{
	
	//modify
	public  void change(JTable table, Connection conn, Boolean sel)	//sel = ��� ���п�
	{
		int count = table.getSelectedRowCount();
		System.out.println("���� �� : "+count);
		while(count-- != 0)
		{
			int index[] = table.getSelectedRows();	//	���õ� ���̺� ��ȣ �迭�� �޾ƿ´�.
//			System.out.println("�ε��� ��ȣ : "+index[(table.getSelectedRowCount()-1)-count]);
			int num = index[(table.getSelectedRowCount()-1)-count];	//���̺� ������� ���õ� row�� �޴´�.
			
			String pid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(0);
			String pname = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(1);
			String cost = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(2);
			String floor = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(3);
			String category = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(4);
			String cid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(5);
			String x = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(6);
			String y = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(7);
			String URL = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(8);
			
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
	}
	
	//delete
	public  void change(JTable table, Connection conn){
		int count = table.getSelectedRowCount();
		System.out.println("���� �� : "+count);
		while(count-- != 0)
		{
			int index[] = table.getSelectedRows();	//	���õ� ���̺� ��ȣ �迭�� �޾ƿ´�.
			int num = index[(table.getSelectedRowCount()-1)-count];	//���̺� ������� ���õ� row�� �޴´�.
			
			// ���̺��� ���õ� ���� pid�� ���Ѵ�.
			String pid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(num)).elementAt(0);
			//System.out.println("selected pid : " + pid);
	
			try {
				AdminFrame.modelout.removeRow(num); // ������ �� ���� ����
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

}


