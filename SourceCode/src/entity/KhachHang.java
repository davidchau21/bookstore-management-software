package entity;

public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private String sdt;
	private DiaChi diaChi;
	private boolean gioiTinh;
	public KhachHang(String maKhachHang, String hoTen, String sdt, DiaChi diaChi, boolean gioiTinh) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
	}
	public KhachHang(String maKhachHang, String hoTen,String sdt) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.sdt = sdt;
	}
	public KhachHang(String hoTen, String sdt) {
		super();
		this.hoTen = hoTen;
		this.sdt = sdt;
	}
	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}
	public KhachHang() {
		super();
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", sdt=" + sdt + ", diaChi=" + diaChi
				+ ", gioiTinh=" + gioiTinh + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKhachHang == null) ? 0 : maKhachHang.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		if (maKhachHang == null) {
			if (other.maKhachHang != null)
				return false;
		} else if (!maKhachHang.equals(other.maKhachHang))
			return false;
		return true;
	}
	
}