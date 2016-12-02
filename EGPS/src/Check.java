import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

public class Check extends JFrame {
	static public ArrayList<Image> FLOORS = new ArrayList<>();
	private JPanel contentPane;

	public Check() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1133, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String floorfile =null;
		File sourceImage = null;
		for(int i=1;i<=3;i++){
			floorfile = i+".png";
			sourceImage =  new File(floorfile);
			try {
				Image temp = ImageIO.read(sourceImage);
				Image temp2 = temp.getScaledInstance(1000, 490, Image.SCALE_SMOOTH);
				FLOORS.add(temp2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JLabel xLabel = new JLabel("X\uC88C\uD45C");
		xLabel.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		xLabel.setBounds(55, 533, 257, 65);
		contentPane.add(xLabel);
		
		DrawImage panel = new DrawImage();
		panel.addMouseListener(new Mousepanel(xLabel));
		panel.setBounds(50, 34, 1000, 490);
		contentPane.add(panel);
		
		JButton Floor1 = new JButton("1\uCE35");
		Floor1.setBounds(495, 555, 174, 65);
		Floor1.addActionListener(new ButtonAction(0,panel));
		contentPane.add(Floor1);
		
		JButton Floor2 = new JButton("2\uCE35");
		Floor2.setBounds(688, 555, 188, 65);
		Floor2.addActionListener(new ButtonAction(1,panel));
		contentPane.add(Floor2);
		
		JButton Floor3 = new JButton("3\uCE35");
		Floor3.setBounds(888, 555, 162, 65);
		Floor3.addActionListener(new ButtonAction(2,panel));
		contentPane.add(Floor3);
	}
}
class DrawImage extends JPanel{
	Image image;
	DrawImage(){
		image = Check.FLOORS.get(0);
	}
	void setImage(int index){
		image = Check.FLOORS.get(index);
	}
	public void paint(Graphics g){
        g.drawImage(image,0,0,this);
        
	}
}
class ButtonAction implements ActionListener{
	int index;
	DrawImage panel;
	ButtonAction(int index,DrawImage panel){
		this.index=index;
		this.panel=panel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		panel.setImage(index);
		panel.repaint();
	}
	
}
class Mousepanel extends JPanel implements MouseListener, MouseMotionListener{
	JLabel xLabel;
	Mousepanel(JLabel xLabel){
		this.xLabel=xLabel;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		xLabel.setText(" X : " + arg0.getX() + "Y : " + arg0.getY());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		xLabel.setText(" X : " + arg0.getX() + "  Y : " + arg0.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		xLabel.setText(" X : " + arg0.getX() + "  Y : " + arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		xLabel.setText(" X : " + arg0.getX() + "  Y : " + arg0.getY());
        
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		xLabel.setText(" X : " + arg0.getX() + "Y : " + arg0.getY());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		xLabel.setText(" X : " + arg0.getX() + "  Y : " + arg0.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		xLabel.setText(" X : " + arg0.getX() + "Y : " + arg0.getY());
	}
	
}
