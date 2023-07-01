package entity;

public class LoaiSanPham {
	private String maLoai;
	private String tenLoai;
	private String chiTiet;
	public LoaiSanPham(String maLoai, String tenLoai, String chiTiet) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.chiTiet = chiTiet;
	}
	
	public LoaiSanPham(String maLoai) {
		super();
		this.maLoai = maLoai;
	}

	public LoaiSanPham() {
		super();
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public String getChiTiet() {
		return chiTiet;
	}
	public void setChiTiet(String chiTiet) {
		this.chiTiet = chiTiet;
	}
	@Override
	public String toString() {
		return "LoaiSanPham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", chiTiet=" + chiTiet + "]";
	}
	
	
}

