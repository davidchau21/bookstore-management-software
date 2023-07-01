package entity;

public class NhaXuatBan {
	private String maNXB;
	private String tenNXB;
	private String sdt;
	private DiaChi diaChi;
	public NhaXuatBan(String maNXB, String tenNXB, String sdt, DiaChi diaChi) {
		super();
		this.maNXB = maNXB;
		this.tenNXB = tenNXB;
		this.sdt = sdt;
		this.diaChi = diaChi;
	}
	public NhaXuatBan(String maNXB) {
		super();
		this.maNXB = maNXB;
	}

	public NhaXuatBan() {
		super();
	}
	public String getMaNXB() {
		return maNXB;
	}
	public void setMaNXB(String maNXB) {
		this.maNXB = maNXB;
	}
	public String getTenNXB() {
		return tenNXB;
	}
	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public DiaChi getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "NhaXuatBan [maNXB=" + maNXB + ", tenNXB=" + tenNXB + ", sdt=" + sdt + ", diaChi=" + diaChi + "]";
	}
	
}
