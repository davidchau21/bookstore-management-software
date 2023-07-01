package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DiaChi;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.SanPham;
import entity.TacGia;
import entity.XuatXu;
import until.Ngay;

public class Dao_SanPham {
	private Dao_NhaXuatBan dao_nhaXB = new Dao_NhaXuatBan();
	private Dao_TacGia dao_tacGia = new Dao_TacGia();
	private Dao_LoaiSanPham dao_loaiSP = new Dao_LoaiSanPham();
	private Dao_NhaCungCap dao_nhaCC = new Dao_NhaCungCap();

	public Dao_SanPham() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public ArrayList<SanPham> getAllSach(){
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT        SanPham.maSanPham, SanPham.tenSanPham, SanPham.soLuong, SanPham.giaNhap, SanPham.namXB, SanPham.soTrang, SanPham.maNXB, SanPham.maTG, SanPham.moTa, "
				+ "SanPham.hinhanh, SanPham.maLoai, \r\n"
				+ "SanPham.maNCC\r\n"
				+ "FROM            LoaiSanPham INNER JOIN\r\n"
				+ "SanPham ON LoaiSanPham.maLoai = SanPham.maLoai INNER JOIN\r\n"
				+ "NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN\r\n"
				+ "NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN\r\n"
				+ "TacGia ON SanPham.maTG = TacGia.maTG";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, moTa, hinhAnh, loaiSp, ncc);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<SanPham> getAllSP(){
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from SanPham";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				int giamGia = rs.getInt("giamGia");
				Date ngayBD = rs.getDate("ngayBD");
				Date ngayKT = rs.getDate("ngayKT");
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, thuongHieu, xx, moTa, hinhAnh, loaiSp, ncc,giamGia,ngayBD,ngayKT);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<SanPham> getSachTheoTenVaTG(String ma,String ten,String tacGia,String danhMuc,String tenLoai){
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "SELECT SanPham.*\r\n"
				+ "FROM   SanPham INNER JOIN\r\n"
				+ "TacGia ON SanPham.maTG = TacGia.maTG INNER JOIN\r\n"
				+ "LoaiSanPham ON SanPham.maLoai = LoaiSanPham.maLoai\r\n"
				+ "Where tenSanPham like ? and hoTen like ? \r\n"
				+ "and tenLoai like ? and chiTiet like ? and maSanPham like ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,"%"+ten+"%");
			stm.setString(2,"%"+tacGia+"%");
			stm.setString(3,"%"+danhMuc+"%");
			stm.setString(4,"%"+tenLoai+"%");
			stm.setString(5,"%"+ma+"%");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, thuongHieu, xx, moTa, hinhAnh, loaiSp, ncc);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<SanPham> getSPTheoDanhMuc(String ma,String ten,String danhMuc,String tenLoai){
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "SELECT SanPham.*\r\n"
					+ "FROM   SanPham INNER JOIN\r\n"
					+ "       LoaiSanPham ON SanPham.maLoai = LoaiSanPham.maLoai\r\n"
					+ "Where tenSanPham like ?\r\n"
					+ "and tenLoai like ? and chiTiet like ? and maSanPham like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,"%"+ten+"%");
			statement.setString(2,"%"+danhMuc+"%");
			statement.setString(3,"%"+tenLoai+"%");
			statement.setString(4,"%"+ma+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, thuongHieu, xx, moTa, hinhAnh, loaiSp, ncc);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}
	public ArrayList<SanPham> getSPTheoTen(String ma,String ten){
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from SanPham Where tenSanPham like ? and maSanPham like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,"%"+ten+"%");
			statement.setString(2,"%"+ma+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, thuongHieu, xx, moTa, hinhAnh, loaiSp, ncc);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}
	public SanPham getSPTheoMa(String ma) {
		SanPham sp= null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from SanPham where maSanPham = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,ma);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				int giamGia = rs.getInt("giamGia");
				Date ngayBD = rs.getDate("ngayBD");
				Date ngayKT = rs.getDate("ngayKT");
				sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, thuongHieu, xx, moTa, hinhAnh, loaiSp, ncc,giamGia,ngayBD,ngayKT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sp;
	}
	public SanPham getSPTheoMaDangGiamGia(String ma) {
		SanPham sp= null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from SanPham where maSanPham = ? and giamGia is not null and ngayBD <= GetDate() and GetDate() <= ngayKT";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,ma);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				int giamGia = rs.getInt("giamGia");
				Date ngayBD = rs.getDate("ngayBD");
				Date ngayKT = rs.getDate("ngayKT");
				sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, thuongHieu, xx, moTa, hinhAnh, loaiSp, ncc,giamGia,ngayBD,ngayKT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sp;
	}
	public SanPham getSPTheoNgayGiamGia(String ma,String ngayGG) {
		SanPham sp= null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from SanPham where maSanPham = ? and giamGia is not null and ngayBD <= ? and ? <= ngayKT";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,ma);
			stm.setString(2,ngayGG);
			stm.setString(3,ngayGG);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				int namXB = rs.getInt("namXB");
				int soTrang= rs.getInt("soTrang");
				NhaXuatBan nxb = new NhaXuatBan(rs.getString("maNXB"));
				TacGia tg = new TacGia(rs.getString("maTG"));
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				int giamGia = rs.getInt("giamGia");
				Date ngayBD = rs.getDate("ngayBD");
				Date ngayKT = rs.getDate("ngayKT");
				sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, thuongHieu, xx, moTa, hinhAnh, loaiSp, ncc,giamGia,ngayBD,ngayKT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sp;
	}
	public boolean suaSoLuongSP(String maSP,int soLuong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Update SanPham set soLuong=? where maSanPham = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1,soLuong);
			stm.setString(2,maSP);
			n = stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public ArrayList<SanPham> getAllVPP() {
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from SanPham where maXX IS NOT NULL";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap,thuongHieu, xx, hinhAnh, loaiSp);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<SanPham> getVPPTheoDK(String tenS,String th,String x,String l){
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from SanPham where maXX IS NOT NULL\r\n"
					+ "and tenSanPham like ? and thuongHieu like ? and maXX like ? and maLoai like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,tenS);
			statement.setString(2,th);
			statement.setString(3,"%"+x+"%");
			statement.setString(4,"%"+l+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString("maSanPham");
				String tenSP = rs.getString("tenSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				String thuongHieu = rs.getString("thuongHieu");
				XuatXu xx = new XuatXu(rs.getString("maXX"));
				String hinhAnh = rs.getString("hinhanh");
				LoaiSanPham loaiSp = new LoaiSanPham(rs.getString("maLoai"));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap,thuongHieu, xx, hinhAnh, loaiSp);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}
	public boolean themVPP(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "insert into SanPham(maSanPham,tenSanPham,soLuong,giaNhap,thuongHieu,maXX,hinhanh,maLoai) values(?,?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,sp.getMaSanPham());
			stm.setString(2,sp.getTenSanPham());
			stm.setInt(3, sp.getSoLuong());
			stm.setDouble(4, sp.getGiaNhap());
			stm.setString(5, sp.getThuongHieu());
			stm.setString(6, sp.getXuatXu().getMaXX());
			stm.setString(7, sp.getHinhAnh());
			stm.setString(8,sp.getLoaiSP().getMaLoai());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean xoaVPP(String nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Delete SanPham where maSanPham=?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,nv);
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean suaVPP(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Update SanPham set tenSanPham=?,soLuong=?,giaNhap=?,thuongHieu=?,maXX=?,hinhAnh=?,maLoai=? where maSanPham=?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(8,sp.getMaSanPham());
			stm.setString(1,sp.getTenSanPham());
			stm.setInt(2, sp.getSoLuong());
			stm.setDouble(3, sp.getGiaNhap());
			stm.setString(4, sp.getThuongHieu());
			stm.setString(5, sp.getXuatXu().getMaXX());
			stm.setString(6, sp.getHinhAnh());
			stm.setString(7,sp.getLoaiSP().getMaLoai());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean suaGiamGia(String ma,String giam,String ngaybd,String ngaykt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Update SanPham set giamGia=?,ngayBD=?,ngayKT=? where maSanPham=?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(4,ma);
			stm.setString(1,giam);
			stm.setString(2, ngaybd);
			stm.setString(3, ngaykt);
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public String gethinhAnhTheoMa(String maSP) {
		String ma = "";
		String sql = "Select hinhAnh from SanPham where maSanPham =  ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			s = con.prepareStatement(sql);
			s.setString(1, maSP);
			ResultSet r = s.executeQuery();
			r.next();
			ma = r.getString(1);
		} catch (SQLException e) {
		} finally {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ma;
	}
	public ArrayList<SanPham> getListSach() {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT    SanPham.maSanPham, SanPham.tenSanPham, SanPham.soLuong, SanPham.giaNhap, SanPham.namXB, SanPham.soTrang, NhaXuatBan.maNXB, \r\n"
					+ "TacGia.maTG, NhaCungCap.maNCC, SanPham.moTa,hinhanh,LoaiSanPham.maLoai FROM         SanPham INNER JOIN\r\n"
					+ "TacGia ON SanPham.maTG = TacGia.maTG INNER JOIN\r\n"
					+ "NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN\r\n"
					+ "NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN\r\n"
					+ "LoaiSanPham ON SanPham.maLoai = LoaiSanPham.maLoai";
			Statement stmt = con.createStatement();

			// Thực thi câu lệnh sql trả về đối tượng ResualtSet
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				double giaNhap = rs.getDouble(4);
				int namXB = rs.getInt(5);
				int soTrang = rs.getInt(6);
				NhaXuatBan nxb = new NhaXuatBan(rs.getString(7));
				TacGia tg = new TacGia(rs.getString(8));
				NhaCungCap ncc = new NhaCungCap(rs.getString(9));
				String moTa = rs.getString(10);
				String hinhAnh = rs.getString(11);
				LoaiSanPham loaiSach = new LoaiSanPham(rs.getString(12));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, moTa,hinhAnh, loaiSach, ncc);
				dataList.add(sp);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataList;
	}
	public boolean themSach(SanPham sach) {
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = connection.prepareStatement("insert into SanPham "
					+ "(maSanPham,tenSanPham,soLuong,giaNhap,namXB,soTrang,maNXB,maTG,moTa,hinhanh,maLoai,maNCC) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(2, sach.getTenSanPham());
			stmt.setInt(3, sach.getSoLuong());
			stmt.setDouble(4, sach.getGiaNhap());
			stmt.setInt(5, sach.getNamXB());
			stmt.setInt(6, sach.getSoTrang());
			stmt.setString(7, sach.getNhaXB().getMaNXB());
			stmt.setString(8,sach.getTacGia().getMaTG());
			stmt.setString(9, sach.getMoTa());
			stmt.setString(10, sach.getHinhAnh());
			stmt.setString(11, sach.getLoaiSP().getMaLoai());
			stmt.setString(12, sach.getNhaCungCap().getMaNCC());
			stmt.setString(1, sach.getMaSanPham());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;

	}

	public boolean suaSach(SanPham sach) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "Update SanPham set tenSanPham=?,soLuong=?,giaNhap=?,namXB=?,"
				+ "soTrang=?,maNXB=?,maTG=?,moTa=?,hinhanh=?,maLoai=?,maNCC=? " + "where maSanPham=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sach.getTenSanPham());
			stmt.setInt(2, sach.getSoLuong());
			stmt.setDouble(3, sach.getGiaNhap());
			stmt.setInt(4, sach.getNamXB());
			stmt.setInt(5, sach.getSoTrang());
			stmt.setString(6, sach.getNhaXB().getMaNXB());
			stmt.setString(7,sach.getTacGia().getMaTG());
			stmt.setString(8, sach.getMoTa());
			stmt.setString(9, sach.getHinhAnh());
			stmt.setString(10, sach.getLoaiSP().getMaLoai());
			stmt.setString(11, sach.getNhaCungCap().getMaNCC());
			stmt.setString(12, sach.getMaSanPham());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean xoaSach(String s) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		String sql = "Delete SanPham where maSanPham=?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, s);
			n = stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public ArrayList<SanPham> getSachTheoDK(String tenS,String namxb,String l,String nhaXB,String maTG,String maNCC){
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "SELECT    SanPham.maSanPham, SanPham.tenSanPham, SanPham.soLuong, SanPham.giaNhap, SanPham.namXB, SanPham.soTrang, NhaXuatBan.maNXB, \r\n"
					+ "TacGia.maTG, NhaCungCap.maNCC, SanPham.moTa,hinhanh,LoaiSanPham.maLoai FROM         SanPham INNER JOIN\r\n"
					+ "TacGia ON SanPham.maTG = TacGia.maTG INNER JOIN\r\n"
					+ "NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN\r\n"
					+ "NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN\r\n"
					+ "LoaiSanPham ON SanPham.maLoai = LoaiSanPham.maLoai\r\n"
					+ "Where tenSanPham like ? and namXB like ? and LoaiSanPham.maLoai like ? and NhaXuatBan.maNXB like ? and TacGia.maTG like ? and NhaCungCap.maNCC like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,tenS);
			statement.setString(2,namxb);
			statement.setString(3,"%"+l+"%");
			statement.setString(4,"%"+nhaXB+"%");
			statement.setString(5,"%"+maTG+"%");
			statement.setString(6,"%"+maNCC+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				double giaNhap = rs.getDouble(4);
				int namXB = rs.getInt(5);
				int soTrang = rs.getInt(6);
				NhaXuatBan nxb = new NhaXuatBan(rs.getString(7));
				TacGia tg = new TacGia(rs.getString(8));
				NhaCungCap ncc = new NhaCungCap(rs.getString(9));
				String moTa = rs.getString(10);
				String hinhAnh = rs.getString(11);
				LoaiSanPham loaiSach = new LoaiSanPham(rs.getString(12));
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhap, namXB, soTrang, nxb, tg, moTa,hinhAnh, loaiSach, ncc);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}
	public ArrayList<SanPham> getSPTheoMaNCC(String mancc) {
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "SELECT        SanPham.maSanPham\r\n"
					+ "		FROM            NhaCungCap INNER JOIN\r\n"
					+ "		                         SanPham ON NhaCungCap.maNCC = SanPham.maNCC\r\n"
					+ "		Where SanPham.maNCC like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,mancc);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString("maSanPham");
				SanPham sp = new SanPham(maSP);
				ds.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}
	public ArrayList<SanPham> thongKeSPBanChay(Date tuNgay, Date toiNgay, int minSoLuong) {
		ArrayList<SanPham> ls = new ArrayList<SanPham>();
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			
			String sql = "select top " + minSoLuong +" SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong,	sum(ChiTietHoaDon.soLuong) as soLuongDaBan \r\n"
					+ "			from SanPham join ChiTietHoaDon on SanPham.maSanPham = ChiTietHoaDon.maSanPham\r\n"
					+ "						join HoaDon on ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
					+ "			where HoaDon.ngayLap >= ? and HoaDon.ngayLap <= ?\r\n"
					+ "			group by SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong \r\n"
					+ "			order by sum(ChiTietHoaDon.soLuong) desc";
			stmt = con.prepareStatement(sql);
			stmt.setTimestamp(1, Ngay.tuNgay(tuNgay));
			stmt.setTimestamp(2, Ngay.toiNgay(toiNgay));
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				LoaiSanPham loaisp = new LoaiSanPham(rs.getString(3));
				double giaNhap = rs.getDouble(4);
				int soLuongNhap = rs.getInt(5);
				NhaXuatBan nxb = new NhaXuatBan(rs.getString(6)); // Dùng thế cho soLuong Da Ban
				SanPham sp = new SanPham(maSP, tenSP, soLuongNhap, giaNhap, 0, 0, nxb, null, "", "", loaisp, null);
				ls.add(sp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ls;
	}
	public ArrayList<SanPham> thongKeSPBanChay(int minSoLuong) {
		ArrayList<SanPham> ls = new ArrayList<SanPham>();
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String soLuong ="";
			if(minSoLuong != 0) {
				soLuong = " and sum(ChiTietHoaDon.soLuong) >= " + minSoLuong + "\r\n";
			}
			String sql = "select top " + minSoLuong +" SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong,	sum(ChiTietHoaDon.soLuong) as soLuongDaBan \r\n"
					+ "			from SanPham join ChiTietHoaDon on SanPham.maSanPham = ChiTietHoaDon.maSanPham\r\n"
					+ "							join HoaDon on ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
					+ "			group by SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong \r\n"
					+ "			order by sum(ChiTietHoaDon.soLuong) desc";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				LoaiSanPham loaisp = new LoaiSanPham(rs.getString(3));
				double giaNhap = rs.getDouble(4);
				int soLuongNhap = rs.getInt(5);
				NhaXuatBan nxb = new NhaXuatBan(rs.getString(6)); // Dùng thế cho soLuong Da Ban
				SanPham sp = new SanPham(maSP, tenSP, soLuongNhap, giaNhap, 0, 0, nxb, null, "", "", loaisp, null);
				ls.add(sp);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ls;
	}
	public ArrayList<SanPham> nvthongKeSPBanChay(Date tuNgay, Date toiNgay, int minSoLuong,String maNV) {
		ArrayList<SanPham> ls = new ArrayList<SanPham>();
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			
			String sql = "select top " + minSoLuong +" SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong,	sum(ChiTietHoaDon.soLuong) as soLuongDaBan \r\n"
					+ "			from SanPham join ChiTietHoaDon on SanPham.maSanPham = ChiTietHoaDon.maSanPham\r\n"
					+ "						join HoaDon on ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
					+ "			where HoaDon.ngayLap >= ? and HoaDon.ngayLap <= ? and maNhanVien =?\r\n"
					+ "			group by SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong \r\n"
					+ "			order by sum(ChiTietHoaDon.soLuong) desc";
			stmt = con.prepareStatement(sql);
			stmt.setTimestamp(1, Ngay.tuNgay(tuNgay));
			stmt.setTimestamp(2, Ngay.toiNgay(toiNgay));
			stmt.setString(3, maNV);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				LoaiSanPham loaisp = new LoaiSanPham(rs.getString(3));
				double giaNhap = rs.getDouble(4);
				int soLuongNhap = rs.getInt(5);
				NhaXuatBan nxb = new NhaXuatBan(rs.getString(6)); // Dùng thế cho soLuong Da Ban
				SanPham sp = new SanPham(maSP, tenSP, soLuongNhap, giaNhap, 0, 0, nxb, null, "", "", loaisp, null);
				ls.add(sp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ls;
	}
	public ArrayList<SanPham> nvthongKeSPBanChay(int minSoLuong,String maNV) {
		ArrayList<SanPham> ls = new ArrayList<SanPham>();
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String soLuong ="";
			if(minSoLuong != 0) {
				soLuong = " and sum(ChiTietHoaDon.soLuong) >= " + minSoLuong + "\r\n";
			}
			String sql = "select top " + minSoLuong +" SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong,	sum(ChiTietHoaDon.soLuong) as soLuongDaBan \r\n"
					+ "			from SanPham join ChiTietHoaDon on SanPham.maSanPham = ChiTietHoaDon.maSanPham\r\n"
					+ "							join HoaDon on ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon where and maNhanVien = ? \r\n"
					+ "			group by SanPham.maSanPham, tenSanPham, maLoai, giaNhap, SanPham.soLuong \r\n"
					+ "			order by sum(ChiTietHoaDon.soLuong) desc";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				LoaiSanPham loaisp = new LoaiSanPham(rs.getString(3));
				double giaNhap = rs.getDouble(4);
				int soLuongNhap = rs.getInt(5);
				NhaXuatBan nxb = new NhaXuatBan(rs.getString(6)); // Dùng thế cho soLuong Da Ban
				SanPham sp = new SanPham(maSP, tenSP, soLuongNhap, giaNhap, 0, 0, nxb, null, "", "", loaisp, null);
				ls.add(sp);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ls;
	}
}
