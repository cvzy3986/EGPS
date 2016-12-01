import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @file TabChangeListener.java
 * @brief �� �� ȭ�� ��� Ŭ����
 * @param arrFloor : �� ���� �̹����� �����ϰ� �ִ� �迭
 * @remark �ǿ� �ִ� �� ���� Ŭ�� �� ���� ���� ȭ���� ��½����ش�. 
 */

public class TabChangeListener implements ChangeListener {
	MapImage mapImage;
	ArrayList<Image> arrFloor;
	TabChangeListener(MapImage mapImage, ArrayList<Image> arrFloor){
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