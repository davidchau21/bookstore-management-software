package entity;

public class TaiKhoan {
	private String maTaiKhoan;
	private String matKhau;
	private NhanVien nhanVien;
	public TaiKhoan(String maTaiKhoan, String matKhau, NhanVien nhanVien) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}
	public TaiKhoan() {
		super();
	}
	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}
	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + "]";
	}
	
}
