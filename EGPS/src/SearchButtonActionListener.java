import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;

class SearchButtonActionListener implements ActionListener{
	JTextField textField;
	Connection conn;
	MapImage mapImage;
	IsSearchActive SearchActive;
	SearchButtonActionListener(JTextField textField,Connection conn,MapImage mapImage,IsSearchActive SearchActive){
		this.textField =textField;
		this.conn  = conn;
		this.mapImage = mapImage;
		this.SearchActive = SearchActive;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!SearchActive.isActive){
			SearchActive.isActive = true;
			SearchThread thread = new SearchThread(textField, conn, mapImage,SearchActive);
			thread.start();
		}
	}
}