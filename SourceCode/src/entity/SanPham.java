package entity;

import java.sql.Date;

public class SanPham {
	private String maSanPham;
	private String tenSanPham;
	private int soLuong;
	private double giaNhap;
	private int namXB;
	private int soTrang;
	private NhaXuatBan nhaXB;
	private TacGia tacGia;
	private String thuongHieu;
	private XuatXu xuatXu;
	private String moTa;
	private String hinhAnh;
	private LoaiSanPham loaiSP;
	private NhaCungCap nhaCungCap;
	private int giamGia;
	private Date ngayBD;
	private Date ngayKT;
	
	public SanPham(String maSanPham, String tenSanPham, int soLuong, double giaNhap, int namXB, int soTrang,
			NhaXuatBan nhaXB, TacGia tacGia, String thuongHieu, XuatXu xuatXu, String moTa, String hinhAnh,
			LoaiSanPham loaiSP, NhaCungCap nhaCungCap, int giamGia, Date ngayBD, Date ngayKT) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.namXB = namXB;
		this.soTrang = soTrang;
		this.nhaXB = nhaXB;
		this.tacGia = tacGia;
		this.thuongHieu = thuongHieu;
		this.xuatXu = xuatXu;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.loaiSP = loaiSP;
		this.nhaCungCap = nhaCungCap;
		this.giamGia = giamGia;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
	}
	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}
	public SanPham(String maSanPham, String tenSanPham, int soLuong, double giaNhap, int namXB, int soTrang,
			NhaXuatBan nhaXB, TacGia tacGia, String thuongHieu, XuatXu xuatXu, String moTa, String hinhAnh,
			LoaiSanPham loaiSP, NhaCungCap nhaCungCap) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.namXB = namXB;
		this.soTrang = soTrang;
		this.nhaXB = nhaXB;
		this.tacGia = tacGia;
		this.thuongHieu = thuongHieu;
		this.xuatXu = xuatXu;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.loaiSP = loaiSP;
		this.nhaCungCap = nhaCungCap;
	}
	public SanPham(String maSanPham, String tenSanPham, int soLuong, double giaNhap, int namXB, int soTrang,
			NhaXuatBan nhaXB, TacGia tacGia, String moTa, String hinhAnh, LoaiSanPham loaiSP, NhaCungCap nhaCungCap) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.namXB = namXB;
		this.soTrang = soTrang;
		this.nhaXB = nhaXB;
		this.tacGia = tacGia;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.loaiSP = loaiSP;
		this.nhaCungCap = nhaCungCap;
	}
	public SanPham(String maSanPham, String tenSanPham, int soLuong, double giaNhap, String thuongHieu, XuatXu xuatXu,
			String hinhAnh, LoaiSanPham loaiSP) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.thuongHieu = thuongHieu;
		this.xuatXu = xuatXu;
		this.hinhAnh = hinhAnh;
		this.loaiSP = loaiSP;
	}
	public SanPham() {
		super();
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public int getNamXB() {
		return namXB;
	}
	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}
	public int getSoTrang() {
		return soTrang;
	}
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
	public NhaXuatBan getNhaXB() {
		return nhaXB;
	}
	public void setNhaXB(NhaXuatBan nhaXB) {
		this.nhaXB = nhaXB;
	}
	public TacGia getTacGia() {
		return tacGia;
	}
	public void setTacGia(TacGia tacGia) {
		this.tacGia = tacGia;
	}
	public String getThuongHieu() {
		return thuongHieu;
	}
	public void setThuongHieu(String thuongHieu) {
		this.thuongHieu = thuongHieu;
	}
	public XuatXu getXuatXu() {
		return xuatXu;
	}
	public void setXuatXu(XuatXu xuatXu) {
		this.xuatXu = xuatXu;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public LoaiSanPham getLoaiSP() {
		return loaiSP;
	}
	public void setLoaiSP(LoaiSanPham loaiSP) {
		this.loaiSP = loaiSP;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	
	public int getGiamGia() {
		return giamGia;
	}
	public void setGiamGia(int giamGia) {
		this.giamGia = giamGia;
	}
	public Date getNgayBD() {
		return ngayBD;
	}
	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}
	public Date getNgayKT() {
		return ngayKT;
	}
	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}
	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", soLuong=" + soLuong + ", giaNhap="
				+ giaNhap + ", namXB=" + namXB + ", soTrang=" + soTrang + ", nhaXB=" + nhaXB + ", tacGia=" + tacGia
				+ ", thuongHieu=" + thuongHieu + ", xuatXu=" + xuatXu + ", moTa=" + moTa + ", hinhAnh=" + hinhAnh
				+ ", loaiSP=" + loaiSP + ", nhaCungCap=" + nhaCungCap + "]";
	}
	public double tinhGiaBan() {
		return giaNhap + giaNhap*0.1;
	}
}
