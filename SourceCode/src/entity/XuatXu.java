package entity;

public class XuatXu {
	private String maXX;
	private String tenNuocXX;
	public XuatXu(String maXX, String tenNuocXX) {
		super();
		this.maXX = maXX;
		this.tenNuocXX = tenNuocXX;
	}
	
	public XuatXu(String maXX) {
		super();
		this.maXX = maXX;
	}

	public XuatXu() {
		super();
	}
	public String getMaXX() {
		return maXX;
	}
	public void setMaXX(String maXX) {
		this.maXX = maXX;
	}
	public String getTenNuocXX() {
		return tenNuocXX;
	}
	public void setTenNuocXX(String tenNuocXX) {
		this.tenNuocXX = tenNuocXX;
	}
	@Override
	public String toString() {
		return "XuatXu [maXX=" + maXX + ", tenNuocXX=" + tenNuocXX + "]";
	}
	
}
