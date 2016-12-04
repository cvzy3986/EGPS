import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;

class SearchActionListener implements ActionListener{
	JTextField textField;
	Connection conn;
	Map_Screen mapImage;
	IsSearchActive SearchActive;
	SearchActionListener(JTextField textField,Connection conn,Map_Screen mapImage,IsSearchActive SearchActive){
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
			Search_result_ScreenThread thread = new Search_result_ScreenThread(textField, conn, mapImage,SearchActive);
			thread.start();
		}
	}
}