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
 * @brief 상품 추가 화면에서 추가 버튼 입력 시 실행되는 클래스
 * @details 상품 정보를 입력하고 추가 버튼을 누르면 동작하는 클래스로 입력된 정보를 받아와 상품 객체에 저장한다.
 * @param fields
 *            : pid, cost 등 상품의 정보를 저장한 텍스트 필드들을 저장한 ArrayList
 * @param cidBox
 *            : 카테고리 ID 정보가 선택된 콤보박스
 * @param conn
 *            : DB와 연결해주는 커넥션
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

		// pid 유지를 위한 처리
		if (obj.change(conn) == 0)
		{
			fields.get(0).setText(Integer.toString(Integer.parseInt(product.getPid()) + 1));
			fields.get(1).setText(null); fields.get(2).setText(null);
			fields.get(3).setText(null); fields.get(4).setText(null);
			fields.get(5).setText(null); fields.get(6).setText(null);
		}
	}
}