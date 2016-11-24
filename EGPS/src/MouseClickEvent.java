import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

class MouseClickEvent extends MouseAdapter{
	/**
	 * @brief 카테고리 버튼 클릭이벤트 클래스
	 * @details PopupMenu: 
	 */
	int index;
	JButton button;
	PopupMenu popmenus;
	MouseClickEvent(int index,JButton button,PopupMenu popmenus){
		this.index = index;
		this.button = button;
		this.popmenus = popmenus;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * @brief 마우스 클릭이벤트
		 * @details 
		 */
		popmenus.pop.get(index).show(e.getComponent(), e.getX(), e.getY());
	}
	
}
