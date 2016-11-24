import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
class ProductDrawImage extends JPanel{
	public void paint(Graphics g){
        g.drawImage(EGPS.PRODUCT.pimage,0,0,this);
	}
	ProductDrawImage(){
		addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	 java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
	        	 java.net.URI uri;
				try {
					uri = new java.net.URI(EGPS.PRODUCT.URL);
					desktop.browse(uri);
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (IOException ioe){
					ioe.printStackTrace();
				}
	        }
	    });
	}
}

