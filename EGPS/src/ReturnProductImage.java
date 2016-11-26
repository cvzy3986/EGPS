import java.awt.Image;
import java.sql.Blob;

import javax.imageio.ImageIO;

/**
 * @file ReturnProductImage.java
 * @brief 상품 이미지 리턴 클래스
 * @param temp : 이미지를 읽어서 저장하는 변수
 * @remark 이미지의 크기를 변환하여 반환한다.
 */

public class ReturnProductImage{
	static Image returnImage(Blob data) throws Exception{
		Image temp = ImageIO.read(data.getBinaryStream());
		Image temp2 = temp.getScaledInstance(305, 276, Image.SCALE_SMOOTH);
		return temp2;
	}
}
