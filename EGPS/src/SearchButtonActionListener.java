import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;

/**
 * @file SearchButtonActionListener.java
 * @brief 검색버튼 이벤트 클래스
 * @param textField : 검색어를 저장 할 텍스트필드
 * @remark 검색어를 저장하여 버튼클릭 시 새 창을 띄우는 함수를 호출하는 클래스이다.
 */


public class SearchButtonActionListener implements ActionListener{
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
