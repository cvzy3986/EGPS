import javax.swing.JFrame;

public class CheckThread  extends Thread{
	public void run(){
		Check frame = new Check();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
