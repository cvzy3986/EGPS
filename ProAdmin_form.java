//@brief 물품 추가에 쓰이는 클래스
class ProAdmin_form {
	String pid, cid;
	String pname;
	String cost;
	String floor;
	String category;
	String x,y;
	String url;
	String image;

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
	
	public String getImage() {return image;}
	public void setImage(String image) {this.category = image;}
}