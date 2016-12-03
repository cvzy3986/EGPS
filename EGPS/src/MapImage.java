import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class MapImage extends JPanel{
	private Image floor;
	public void setImage(Image floor){
		this.floor=floor;
	}
	public void paint(Graphics g){
		if(floor != null){
			g.drawImage(floor,0,0,this);
		}
		if(EGPS.PRODUCT.x != -1 && EGPS.PRODUCT.y != -1){
			g.setColor(Color.RED);
			g.fillOval(EGPS.PRODUCT.x, EGPS.PRODUCT.y, 35, 35);
		}
	}
}