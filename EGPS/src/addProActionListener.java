import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author ParkJiyong
 * @brief ��ǰ �߰� ȭ�鿡�� �߰� ��ư �Է� �� ����Ǵ� Ŭ����
 * @details ��ǰ ������ �Է��ϰ� �߰� ��ư�� ������ �����ϴ� Ŭ������ �Էµ� ������ �޾ƿ� ��ǰ ��ü�� �����Ѵ�.
 * @param fields
 *            : pid, cost �� ��ǰ�� ������ ������ �ؽ�Ʈ �ʵ���� ������ ArrayList
 * @param cidBox
 *            : ī�װ� ID ������ ���õ� �޺��ڽ�
 * @param conn
 *            : DB�� �������ִ� Ŀ�ؼ�
 * 
 */
public class addProActionListener implements ActionListener {
	ArrayList<JTextField> fields;
	ArrayList<JComboBox> combos;
	Connection conn;

	addProActionListener(ArrayList<JTextField> fields, ArrayList<JComboBox> combos, Connection conn) {
		this.fields = fields;
		this.combos = combos;
		this.conn = conn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ProAdmin_form product = new ProAdmin_form();
		product.setPid(fields.get(0).getText());
		product.setPname(fields.get(1).getText());
		product.setCost(fields.get(2).getText());
		product.setX(fields.get(3).getText());
		product.setY(fields.get(4).getText());
		product.setURL(fields.get(5).getText());
		
		product.setCid(combos.get(0).getSelectedItem().toString());
		product.setCategory(combos.get(1).getSelectedItem().toString());
		product.setFloor(combos.get(2).getSelectedItem().toString());
		
		try {
			File file = new File(Add_Screen.filePath);
			product.setImage(file);
		} catch (NullPointerException nulle) {
			nulle.printStackTrace();
		}

		OnePro_admin obj = new OnePro_admin();
		obj.setProductForm(product);

		// pid ������ ���� ó��
		if (obj.change(conn) == 0)
			fields.get(0).setText(Integer.toString(Integer.parseInt(product.getPid()) + 1));
		else
			fields.get(0).setText(Integer.toString(Integer.parseInt(product.getPid())));

		fields.get(1).setText(null);
		fields.get(2).setText(null);
		fields.get(3).setText(null);
		fields.get(4).setText(null);
		fields.get(5).setText(null);
		fields.get(6).setText(null);
	}
}