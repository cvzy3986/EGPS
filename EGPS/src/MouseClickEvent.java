import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

class MouseClickEvent extends MouseAdapter{
	/**
	 * @brief ī�װ� ��ư Ŭ���̺�Ʈ Ŭ����
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
		 * @brief ���콺 Ŭ���̺�Ʈ
		 * @details 
		 */
		popmenus.pop.get(index).show(e.getComponent(), e.getX(), e.getY());
	}
	
}
