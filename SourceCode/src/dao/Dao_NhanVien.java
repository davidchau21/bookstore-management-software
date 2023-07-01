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
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.SanPham;
import entity.TacGia;
import entity.XuatXu;
import until.Ngay;

public class Dao_NhanVien {
	private Dao_DiaChi dao_DiaChi;
	public Dao_NhanVien() {
		// TODO Auto-generated constructor stub
		dao_DiaChi = new Dao_DiaChi();
	}
	public ArrayList<NhanVien> getAllNhanVien(){
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT NhanVien.maNhanVien, NhanVien.hoTen, NhanVien.ngaySinh, NhanVien.gioiTinh, NhanVien.cccd, NhanVien.sdt, NhanVien.chucVu, DiaChi.tinhTP, DiaChi.quanHuyen, DiaChi.phuongXa\r\n"
				+ "FROM   DiaChi INNER JOIN NhanVien ON DiaChi.maDC = NhanVien.maDC";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString("maNhanVien");
				String hoTen = rs.getString("hoTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String cccd = rs.getString("cccd");
				String sdt = rs.getString("sdt");
				String chuVu = rs.getString("chucVu");
				DiaChi diaChi = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, diaChi, gioiTinh, cccd, sdt, chuVu);
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	public ArrayList<NhanVien> getNhanVienTheoDK(String hoT,String sd,String ccc,String gt){
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "SELECT NhanVien.maNhanVien, NhanVien.hoTen, NhanVien.ngaySinh, NhanVien.gioiTinh, NhanVien.cccd, NhanVien.sdt, NhanVien.chucVu, DiaChi.tinhTP, DiaChi.quanHuyen, DiaChi.phuongXa \r\n"
					+ "	FROM   DiaChi INNER JOIN NhanVien ON DiaChi.maDC = NhanVien.maDC\r\n"
					+ "	Where hoTen like ? and sdt like ? and cccd like ? and gioiTinh like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,hoT);
			statement.setString(2,sd);
			statement.setString(3,ccc);
			statement.setString(4,"%"+gt+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maNV = rs.getString("maNhanVien");
				String hoTen = rs.getString("hoTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String cccd = rs.getString("cccd");
				String sdt = rs.getString("sdt");
				String chuVu = rs.getString("chucVu");
				DiaChi diaChi = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, diaChi, gioiTinh, cccd, sdt, chuVu);
				ds.add(nv);
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
	public NhanVien getNhanVienTheoMa(String ma) {
		NhanVien nv= null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from NhanVien where maNhanVien = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,ma);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString("maNhanVien");
				String hoTen = rs.getString("hoTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String cccd = rs.getString("cccd");
				String sdt = rs.getString("sdt");
				String chuVu = rs.getString("chucVu");
				DiaChi diaChi = new DiaChi(rs.getString("maDC"));
				nv = new NhanVien(maNV, hoTen, ngaySinh, diaChi, gioiTinh, cccd, sdt, chuVu);
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
		return nv;
	}
	public boolean themNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		if (getMaNVTheoCMND(nv.getCccd()) != "")
			return false;
		if (getMaNVTheoSDT(nv.getSdt()) != "")
			return false;
		int n=0;
		String sql = "Insert into NhanVien values(?,?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,nv.getMaNhanVien());
			stm.setString(2,nv.getHoTen());
			stm.setDate(3,nv.getNgaySinh());
			stm.setString(4, dao_DiaChi.getMaDCTheoTinhHuyenXa(nv.getDiaChi().getTinhTP(), nv.getDiaChi().getQuanHuyen(),
					nv.getDiaChi().getPhuongXa()));
			stm.setBoolean(5, nv.isGioiTinh());
			stm.setString(6, nv.getCccd());
			stm.setString(7, nv.getSdt());
			stm.setString(8, nv.getChucVu());
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
	public boolean suaNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
//		if (getMaNVTheoCMND(nv.getCccd()) != "")
//			return false;
//		if (getMaNVTheoSDT(nv.getSdt()) != "")
//			return false;
		int n=0;
		String sql = "Update NhanVien set hoTen=?,ngaySinh=?,maDC=?,gioiTinh=?,cccd=?,sdt=?,chucVu=? where maNhanVien=?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,nv.getHoTen());
			stm.setDate(2,nv.getNgaySinh());
			stm.setString(3, dao_DiaChi.getMaDCTheoTinhHuyenXa(nv.getDiaChi().getTinhTP(), nv.getDiaChi().getQuanHuyen(),
					nv.getDiaChi().getPhuongXa()));
			stm.setBoolean(4, nv.isGioiTinh());
			stm.setString(5, nv.getCccd());
			stm.setString(6, nv.getSdt());
			stm.setString(7, nv.getChucVu());
			stm.setString(8,nv.getMaNhanVien());
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
	public boolean xoaNhanVien(String nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Delete NhanVien where maNhanVien=?";
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
	public String getMaNVTheoSDT(String sdt) {
		String sql = "select maNhanVien from NhanVien where sdt = ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		String ma = "";
		try {
			s = con.prepareStatement(sql);
			s.setString(1, sdt);
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
	
	public String getMaNVTheoCMND(String cmnd) {
		String sql = "select maNhanVien from NhanVien where cmnd = ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		String ma = "";
		try {
			s = con.prepareStatement(sql);
			s.setString(1, cmnd);
			ResultSet r = s.executeQuery();
			r.next();
			ma = r.getString("maNhanVien");
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
	public String getChucVuTheoMa(String ma) {
		String sql = "select chucVu from NhanVien where maNhanVien = ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		String cv = "";
		try {
			s = con.prepareStatement(sql);
			s.setString(1, ma);
			ResultSet r = s.executeQuery();
			r.next();
			cv = r.getString(1);
		} catch (SQLException e) {
		} finally {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cv;
	}
	 public ArrayList<NhanVien> thongKeNhanVien(Date tuNgay, Date toiNgay) {
			ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
			PreparedStatement stmt = null;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			try {
				String sql = "select  NhanVien.maNhanVien, hoTen, ngaySinh,gioiTinh, chucVu, sdt, count (DISTINCT (HoaDon.maHoaDon)) as N'Số hóa đơn', SUM(soLuong) as N'Số sản phẩm'\r\n"
						+ "							from NhanVien join HoaDon on NhanVien.maNhanVien = HoaDon.maNhanVien join ChiTietHoaDon on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
						+ "\r\n"
						+ "							where HoaDon.ngayLap >= ? and HoaDon.ngayLap <= ? and HoaDon.trangThai is null\r\n"
						+ "							group by NhanVien.maNhanVien, hoTen, ngaySinh, chucVu, sdt, gioiTinh\r\n"
						+ "							order by sum(ChiTietHoaDon.soLuong) desc";
				stmt = con.prepareStatement(sql);
				stmt.setTimestamp(1, Ngay.tuNgay(tuNgay));
				stmt.setTimestamp(2, Ngay.toiNgay(toiNgay));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String maNV = rs.getString(1);
					String tenNV = rs.getString(2);
					Date ngaySinh = rs.getDate(3);
					Boolean gioiTinh = rs.getBoolean(4);
					String chucVu = rs.getString(5);
					String sdt = rs.getString(6);
					DiaChi dc = new DiaChi(rs.getString(7)); //thay số hóa đơn
					String cccd = rs.getString(8);//thay số sản phẩm
					NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, dc, gioiTinh, cccd, sdt, chucVu);
					dsnv.add(nv);
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
			return dsnv;
		}
}
