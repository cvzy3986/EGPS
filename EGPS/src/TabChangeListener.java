import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class TabChangeListener implements ChangeListener {
	Map_Screen mapImage;
	ArrayList<Image> arrFloor;
	TabChangeListener(Map_Screen mapImage, ArrayList<Image> arrFloor){
		this.mapImage=mapImage;
		this.arrFloor=arrFloor;
	}
    public void stateChanged(ChangeEvent changeEvent) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
  
        mapImage.setImage(arrFloor.get(index));
        EGPS.PRODUCT.x = -1;
        EGPS.PRODUCT.y = -2;
        mapImage.repaint();
    }
}