package entity;

public class ChiTietHoaDon {
	private int soLuong;
	private SanPham sanPham;
	private HoaDon hoaDon;
	
	public ChiTietHoaDon(int soLuong, SanPham sanPham, HoaDon hoaDon) {
		super();
		this.soLuong = soLuong;
		this.sanPham = sanPham;
		this.hoaDon = hoaDon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	
	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuong=" + soLuong + ", sanPham=" + sanPham + ", hoaDon=" + hoaDon + "]";
	}

	public double tinhTien() {
		return soLuong*sanPham.tinhGiaBan();
	}
	
}
