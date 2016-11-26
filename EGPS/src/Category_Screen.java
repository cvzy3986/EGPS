import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JPanel;

public class Category_Screen{
	Panels panels;
	Category_Screen(Connection conn){
		panels = new Panels(conn);
	}
}