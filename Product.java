import java.awt.Image;

import javax.swing.ImageIcon;

public class Product {
	int pid;
	String pname;
	int cost;
	Location location;
	Image pimage;
	String URL;
	void set(int pid,String pname,int cost,Location location,Image imageData,String URL){
		this.pid=pid;
		this.pname=pname;
		this.cost = cost;
		this.location=location;
		this.pimage = imageData;
		this.URL =URL;
	}
	void setPid(int pid) {
		this.pid=pid;
	}

	void setPname(String pname) {
		this.pname=pname;
	}

	void setCost(int cost) {
		this.cost = cost;
	}

	void setLocation(Location location) {
		this.location=location;
	}

	void setPimage(Image imageData) {
		this.pimage = imageData;
	}

	void setURL(String URL) {
		this.URL =URL;
	}

}
class Location{
	int x;
	int y;
	Location(int x,int y){
		this.x=x;
		this.y=y;
	}
}