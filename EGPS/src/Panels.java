import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JPanel;

class Panels{
	JPanel panelArray[];
	Panels(Connection conn){
		setButtonToPanel(conn);
	}
	void setButtonToPanel(Connection conn){
		Buttons Jbuttons= new Buttons(conn);
		panelArray = new JPanel[Jbuttons.floorNum];
		for(int i=0;i<Jbuttons.floorNum;i++){
			panelArray[i]  = new JPanel();
			panelArray[i].setLayout(new GridLayout(10,1));
		}
		for (int j = 0; j < Jbuttons.Categorys.size(); j++) {
			if (Jbuttons.Categorys.get(j).floor == 1) {
				panelArray[0].add(Jbuttons.CategoryButtons[j]);
			} else if (Jbuttons.Categorys.get(j).floor == 2) {
				panelArray[1].add(Jbuttons.CategoryButtons[j]);
			} else if (Jbuttons.Categorys.get(j).floor == 3) {
				panelArray[2].add(Jbuttons.CategoryButtons[j]);
			}
			
		}
	}
	
}
