import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

class SearchButtonActionAdmin implements ActionListener {
	Connection conn;
	JTextField searchField;

	SearchButtonActionAdmin(Connection conn, JTextField searchField) {
		this.conn = conn;
		this.searchField = searchField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String product = searchField.getText();
		if(product.length() == 0) {	//검색어가 없을 때
			JOptionPane.showMessageDialog(null, "검색어를 입력해주세요.");
			return;
		}
		try {
			while (Maneger_Screen.modelout.getRowCount() != 0)
				Maneger_Screen.modelout.removeRow(0);
			PreparedStatement query = conn.prepareStatement(
					"Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
			query.setString(1, "%" + product + "%");
			System.out.println(query);
			ResultSet rset = query.executeQuery();
			if(!rset.next()){	//검색 결과가 없을때
				JOptionPane.showMessageDialog(null, "검색 결과가 존재하지 않습니다.");
				return;
			}
			rset = query.executeQuery();
			ArrayList<String> row = new ArrayList<>();
			while (rset.next()) {
				row.add(rset.getString(1)); // pid
				row.add(rset.getString(2)); // pname
				row.add(rset.getString(3)); // cost
				row.add(rset.getString(4)); // floor
				row.add(rset.getString(5)); // category
				row.add(rset.getString(6)); // cid
				row.add(rset.getString(7)); // x
				row.add(rset.getString(8)); // y
				row.add(rset.getString(9)); // URL
				Maneger_Screen.modelout.addRow(row.toArray());
				row.clear();
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException : " + sqle);
		}
	}
}