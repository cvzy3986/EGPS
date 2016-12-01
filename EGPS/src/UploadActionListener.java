import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * 
 * @author ParkJiyong
 * @brief 이미지 업로드를 위한 클래스
 * @details 파일선택기를 사용하여 파일을 선택하고 경로를 받아오고 이름을 텍스트 필드에 적는다.
 * @param imageField : 선택한 파일의 이름을 표시해주는 텍스트 필드
 *
 */
public class UploadActionListener implements ActionListener{
	JTextField imageField;
	UploadActionListener(JTextField imageField){
		this.imageField = imageField;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser fc = new JFileChooser(); // 파일 선택기를 사용
		fc.setFileFilter(new FileNameExtensionFilter("PNG 파일 (*.PNG)","PNG"));
		fc.setFileFilter(new FileNameExtensionFilter("BMP 파일 (*.BMP)","BMP"));
		fc.setFileFilter(new FileNameExtensionFilter("GIF 파일 (*.GIF)","GIF"));
		fc.setFileFilter(new FileNameExtensionFilter("JPEG 파일 (*.JPG)","JPG"));
		
		if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION){
			Add_Screen.filePath = fc.getSelectedFile().getPath(); 
			imageField.setText(fc.getSelectedFile().getName());
		}
		else return;
	}
}