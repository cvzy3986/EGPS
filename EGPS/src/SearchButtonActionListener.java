import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;

class SearchButtonActionListener implements ActionListener{
	JTextField textField;
	Connection conn;
	MapImage mapImage;
	SearchButtonActionListener(JTextField textField,Connection conn,MapImage mapImage){
		this.textField =textField;
		this.conn  = conn;
		this.mapImage = mapImage;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		SearchThread thread = new SearchThread(textField,conn,mapImage);
		thread.start();
	}
}