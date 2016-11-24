//@brief 카테고리 추가, 삭제에 쓰이는 변수 클래스
class CateAdmin_form {
	String cid;
	String cname;
	String floor;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	
}