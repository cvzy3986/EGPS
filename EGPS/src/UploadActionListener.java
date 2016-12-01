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
 * @brief �̹��� ���ε带 ���� Ŭ����
 * @details ���ϼ��ñ⸦ ����Ͽ� ������ �����ϰ� ��θ� �޾ƿ��� �̸��� �ؽ�Ʈ �ʵ忡 ���´�.
 * @param imageField : ������ ������ �̸��� ǥ�����ִ� �ؽ�Ʈ �ʵ�
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
		JFileChooser fc = new JFileChooser(); // ���� ���ñ⸦ ���
		fc.setFileFilter(new FileNameExtensionFilter("PNG ���� (*.PNG)","PNG"));
		fc.setFileFilter(new FileNameExtensionFilter("BMP ���� (*.BMP)","BMP"));
		fc.setFileFilter(new FileNameExtensionFilter("GIF ���� (*.GIF)","GIF"));
		fc.setFileFilter(new FileNameExtensionFilter("JPEG ���� (*.JPG)","JPG"));
		
		if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION){
			Add_Screen.filePath = fc.getSelectedFile().getPath(); 
			imageField.setText(fc.getSelectedFile().getName());
		}
		else return;
	}
}