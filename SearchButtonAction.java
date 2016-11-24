import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

class SearchButtonAction implements ActionListener {
	Connection conn;
	JTextField searchField;

	SearchButtonAction(Connection conn, JTextField searchField) {
		this.conn = conn;
		this.searchField = searchField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String product = searchField.getText();
		try {
			while (EGPSAdmin.modelout.getRowCount() != 0)
				EGPSAdmin.modelout.removeRow(0);
			PreparedStatement query = conn.prepareStatement(
					"Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
			query.setString(1, "%" + product + "%");
			System.out.println(query);
			ResultSet rset = query.executeQuery();
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
				EGPSAdmin.modelout.addRow(row.toArray());
				row.clear();
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException : " + sqle);
		}
	}
}