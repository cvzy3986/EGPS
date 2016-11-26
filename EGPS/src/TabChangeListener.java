import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @file TabChangeListener.java
 * @brief 층 별 화면 출력 클래스
 * @param arrFloor : 각 층의 이미지를 저장하고 있는 배열
 * @remark 탭에 있는 각 층을 클릭 시 층에 따른 화면을 출력시켜준다. 
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
        mapImage.repaint();
    }
}