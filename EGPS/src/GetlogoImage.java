import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class GetlogoImage {
	ImageIcon logo = null;
	GetlogoImage(Connection conn) {
		try {
			Statement stmt = conn.createStatement(); 
			ResultSet rset = stmt.executeQuery("Select image from logo where id=1");
			Image temp = null;
			while(rset.next()){
				temp = ImageIO.read(rset.getBlob(1).getBinaryStream());
			}
			Image temp2 = temp.getScaledInstance(441, 130, Image.SCALE_SMOOTH);
			logo = new ImageIcon(temp2);
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}