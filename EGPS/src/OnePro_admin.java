
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
 * @brief ��ǰ �߰� �Ǵ� ����Ʈ���� ������ ��ǰ��  ����, ���� Ŭ����
 * @details ������ ȭ�鿡�� ���ο� ��ǰ�� �߰��� ��� �Ǵ� ����Ʈ���� ������ ��ǰ�� ���� ����, ��ǰ ���� �ÿ� ���Ǵ� Ŭ����
 * @param table : ������ ��ǰ ����Ʈ�� ����Ǿ� �ִ� ���̺�
 * @param conn : DB�� �������ִ� Ŀ�ؼ�
 * @param sel : ������ ������ �������ֱ� ���� boolean Ÿ�� ����
 */
// ������ ��ǰ ����
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
			menuQuery.setInt(3, Integer.parseInt(form.getCost()));
			menuQuery.setInt(4, Integer.parseInt(form.getFloor()));
			menuQuery.setString(5, form.getCategory());
			menuQuery.setInt(6, Integer.parseInt(form.getCid()));
			FileInputStream fin = new FileInputStream(form.getImage());
			menuQuery.setBinaryStream(7,fin,form.getImage().length());
			menuQuery.setInt(8, Integer.parseInt(form.getX()));
			menuQuery.setInt(9, Integer.parseInt(form.getY()));
			menuQuery.setString(10,form.getURL());

			System.out.println(menuQuery);
			menuQuery.executeUpdate();
			PopupMenu.setPopMenuToButton(conn); // �˾� �޴� ����
			JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�.");
			return 0;
		}catch (SQLException sqle){
			JOptionPane.showMessageDialog(null, "SQL ����");
		}catch (FileNotFoundException imgaee){
			JOptionPane.showMessageDialog(null, "������ �������� �ʽ��ϴ�.");
		}catch (NullPointerException nulle){
			JOptionPane.showMessageDialog(null, "�̹����� �������� �ʽ��ϴ�.");
		}catch (NumberFormatException notInte){
			JOptionPane.showMessageDialog(null, "����, x,y��ǥ�� ���ڸ� �Է� �����մϴ�.");
		}
		return -1;
	}

	// modify
	public void change(JTable table, Connection conn, Boolean sel) // sel = ��ɱ��п�)
	{
		int index = table.getSelectedRow(); // ���õ� ���̺� ��ȣ �޾ƿ´�.

		// ���̺��� ���õ� ���� ������ �޴´�.
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
			PopupMenu.setPopMenuToButton(conn); // �˾� �޴� ����
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;
	}

	//����
	public void change(JTable table, Connection conn) {
		int index = table.getSelectedRow(); // ���õ� ���̺� ��ȣ �޾ƿ´�.

		// ���̺��� ���õ� ���� pid�� ���Ѵ�.
		String pid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(index)).elementAt(0);

		try {
			AdminFrame.modelout.removeRow(index); // ������ �� ���� ����
			// ���� ����
			PreparedStatement menuQuery = conn.prepareStatement("DELETE from product where pid = ?");
			menuQuery.setString(1, pid);
			System.out.println(menuQuery);
			menuQuery.executeUpdate();
			PopupMenu.setPopMenuToButton(conn); // �˾� �޴� ����
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}