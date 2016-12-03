import java.awt.Image;
import java.sql.Blob;

import javax.imageio.ImageIO;

class ReturnProductImage{
	static Image returnImage(Blob data) throws Exception{
		Image temp = ImageIO.read(data.getBinaryStream());
		Image temp2 = temp.getScaledInstance(305, 270, Image.SCALE_SMOOTH);
		return temp2;
	}
}
