
import java.io.File;
import java.io.InputStream;

//@brief 물품 추가에 쓰이는 클래스
class ProAdmin_form {
	private String pid, cid;
	private String pname;
	private String cost;
	private String floor;
	private String category;
	private String x,y;
	private String url;
	private File image;

	public String getPid() {return pid;}
	public void setPid(String pid) {this.pid = pid;}
	
	public String getCid() {return cid;}
	public void setCid(String cid) {this.cid = cid;}

	public String getPname() {return pname;}
	public void setPname(String pname) {this.pname = pname;}

	public String getCost() {return cost;}
	public void setCost(String cost) {this.cost = cost;}
	
	public String getFloor() {return floor;}
	public void setFloor(String floor) {this.floor = floor;}
	
	public String getX() {return x;}
	public void setX(String x) {this.x = x;}
	
	public String getY() {return y;}
	public void setY(String y) {this.y = y;}
	
	public String getURL() {return url;}
	public void setURL(String url) {this.url = url;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	
	public File  getImage() {return image;}
	public void setImage(File image) {this.image = image;}
}