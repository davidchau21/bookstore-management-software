package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DiaChi;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import until.Ngay;

public class Dao_HoaDon {
	public ArrayList<HoaDon> getFull(){
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from HoaDon";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<HoaDon> getAllHD(){
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from HoaDon where trangThai is null";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<HoaDon> getALLHDNVKH() {
		ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select top 20 maHoaDon, ngayLap, NhanVien.hoTen, chucVu ,KhachHang.hoTen,KhachHang.sdt \r\n"
				+ "from HoaDon join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang join NhanVien on NhanVien.maNhanVien = HoaDon.maNhanVien \r\n"
				+ "where trangThai is null\r\n"
				+ "Order by ngayLap desc";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				Date ngayLap = rs.getDate(2);
				NhanVien nv = new NhanVien(rs.getString(3), rs.getString(4));
				KhachHang kh = new KhachHang(rs.getString(5), rs.getString(6));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;
	}
	public ArrayList<HoaDon> getALLDHCHO() {
		ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select maHoaDon, ngayLap, NhanVien.hoTen, chucVu ,KhachHang.hoTen,KhachHang.sdt from HoaDon join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang join NhanVien on NhanVien.maNhanVien = HoaDon.maNhanVien where trangThai is not null";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				Date ngayLap = rs.getDate(2);
				NhanVien nv = new NhanVien(rs.getString(3), rs.getString(4));
				KhachHang kh = new KhachHang(rs.getString(5), rs.getString(6));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;
	}
	public ArrayList<HoaDon> getHDTheoDK(String ma,String nl,String tenNV,String tenKH){
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "select maHoaDon, ngayLap, NhanVien.hoTen, chucVu ,KhachHang.hoTen,KhachHang.sdt \r\n"
					+ "	from HoaDon join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang join NhanVien on NhanVien.maNhanVien = HoaDon.maNhanVien\r\n"
					+ "	Where maHoaDon like ? and ngayLap like ? and NhanVien.hoTen like ? and KhachHang.hoTen like ? and trangThai is null";
			statement=con.prepareStatement(sql);
			statement.setString(1,ma);
			statement.setString(2,nl);
			statement.setString(3,tenNV);
			statement.setString(4,tenKH);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString(1);
				Date ngayLap = rs.getDate(2);
				NhanVien nv = new NhanVien(rs.getString(3), rs.getString(4));
				KhachHang kh = new KhachHang(rs.getString(5), rs.getString(6));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
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
	public boolean xoaHoaDon(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		String sql = "delete HoaDon where maHoaDon=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean themHD(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Insert into HoaDon(maHoaDon,ngayLap,maNhanVien,maKhachHang) values(?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,hd.getMaHoaDon());
			stm.setDate(2,hd.getNgayLap());
			stm.setString(3,hd.getNhanVien().getMaNhanVien());
			stm.setString(4,hd.getKhachHang().getMaKhachHang());
//			stm.setString(4, dao_DiaChi.getMaDCTheoTinhHuyenXa(nv.getDiaChi().getTinhTP(), nv.getDiaChi().getQuanHuyen(),
//					nv.getDiaChi().getPhuongXa()));
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
	public boolean themHDCho(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Insert into HoaDon values(?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,hd.getMaHoaDon());
			stm.setDate(2,hd.getNgayLap());
			stm.setString(3,hd.getNhanVien().getMaNhanVien());
			stm.setString(4,hd.getKhachHang().getMaKhachHang());
			stm.setString(5, hd.getTrangThai());
//			stm.setString(4, dao_DiaChi.getMaDCTheoTinhHuyenXa(nv.getDiaChi().getTinhTP(), nv.getDiaChi().getQuanHuyen(),
//					nv.getDiaChi().getPhuongXa()));
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
	public HoaDon getHDTheoMa(String ma) {
		HoaDon sp= null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from HoaDon where maHoaDon = ? and trangThai is null";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,ma);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				sp = new HoaDon(maHD, ngayLap, nv, kh);
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
	public ArrayList<HoaDon> getHDTheoMaKH(String maKh) {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "SELECT        HoaDon.*\r\n"
				+ "		FROM            HoaDon INNER JOIN\r\n"
				+ "		                         KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
				+ "								 Where HoaDon.maKhachHang = ? and trangThai is null";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,maKh);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<HoaDon>getHDChoTheoDK(String ma,String ten,String sdt) {
			ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
			ConnectDB.getInstance();
			Connection con=ConnectDB.getConnection();
			PreparedStatement statement=null;
			try {
		String sql = "SELECT HoaDon.maHoaDon, HoaDon.ngayLap, HoaDon.maNhanVien, HoaDon.maKhachHang, HoaDon.trangThai, KhachHang.hoTen, KhachHang.sdt\r\n"
				+ "FROM     HoaDon INNER JOIN\r\n"
				+ "                  KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
				+ "where maHoaDon like ? and hoTen like ? and sdt like ? and trangThai is not null";
		statement=con.prepareStatement(sql);
		statement.setString(1,ma);
		statement.setString(2,ten);
		statement.setString(3,sdt);
		ResultSet rs=statement.executeQuery();
		
		while (rs.next()) {
			String maHD = rs.getString(1);
			Date ngayLap = rs.getDate(2);
			NhanVien nv = new NhanVien(rs.getString(3));
			KhachHang kh = new KhachHang(rs.getString(6), rs.getString(7));
			HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
			ds.add(hd);
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
	public boolean suaTrangThai(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "update HoaDon set trangThai = null where maHoaDon = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,hd.getMaHoaDon());
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
	public ArrayList<HoaDon> getHDTuNgayDBDenKT(String ngayBD,String ngayKT,String maNV){
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from HoaDon where ngayLap >= ? and ngayLap <= ? and trangThai is null and maNhanVien = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ngayBD);
			statement.setString(2,ngayKT);
			statement.setString(3, maNV);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<HoaDon> getDSHDHomNay(String ngay,String maNV){
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from HoaDon where ngayLap = ? and trangThai is null and maNhanVien = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ngay);
			statement.setString(2, maNV);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<HoaDon> getDSHDTheoThang(String thang,String nam,String maNV){
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from HoaDon where MONTH(ngayLap) = ? and YEAR(ngayLap) = ? and trangThai is null and maNhanVien = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,thang);
			statement.setString(2,nam);
			statement.setString(3, maNV);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<HoaDon> getDSHDTheoNam(String nam,String maNV){
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from HoaDon where YEAR(ngayLap) = ? and trangThai is null and maNhanVien = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,nam);
			statement.setString(2, maNV);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString("maHoaDon");
				Date ngayLap = rs.getDate("ngayLap");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
				ds.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<HoaDon> chiTiet(Date d1, Date d2) {
		ArrayList<HoaDon> ls = new ArrayList<HoaDon>();
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select HoaDon.maHoaDon, NhanVien.hoTen, KhachHang.hoTen, ngayLap\r\n"
					+ "from HoaDon  join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "					join ChiTietHoaDon on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
					+ "					join NhanVien on HoaDon.maNhanVien = NhanVien.maNhanVien\r\n"
					+ "					where ngayLap >= ? and ngayLap <= ? and HoaDon.trangThai is null\r\n"
					+ "					group by HoaDon.maHoaDon, NhanVien.hoTen, KhachHang.hoTen, ngayLap\r\n"
					+ "					order by ngayLap";
			stmt = con.prepareStatement(sql);	
			stmt.setTimestamp(1, Ngay.tuNgay(d1));
			stmt.setTimestamp(2, Ngay.toiNgay(d2));
//			stmt.setDate(1, d1);
//			stmt.setDate(2, d2);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maHD = rs.getString(1);
//				int soLuong = rs.getInt(5);
				NhanVien nv = new NhanVien(rs.getString(2), "");
				KhachHang kh = new KhachHang(rs.getString(3), "");
				Timestamp ts = rs.getTimestamp("ngayLap");
				Date ngayLap = new Date(ts.getTime());
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
            	ls.add(hd);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	 public ArrayList<HoaDon> getHDTheoMaNV(String manv) {
			ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stm = null;
			String sql = "SELECT        HoaDon.*\r\n"
					+ "		FROM            HoaDon INNER JOIN\r\n"
					+ "		                         NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien\r\n"
					+ "								 Where HoaDon.maNhanVien = ? and HoaDon.trangThai is null";
			try {
				stm = con.prepareStatement(sql);
				stm.setString(1,manv);
				ResultSet rs = stm.executeQuery();
				while(rs.next()) {
					String maHD = rs.getString("maHoaDon");
					Date ngayLap = rs.getDate("ngayLap");
					NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
					KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
					HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
					ds.add(hd);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ds;
		}
	        public ArrayList<HoaDon> getHDTheoMaKHNgay(Date d1, Date d2,String makh) {
			ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stm = null;
			String sql = "select HoaDon.*		from HoaDon  join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "					where ngayLap >= ? and ngayLap <= ? and KhachHang.maKhachHang = ? and trangThai is null";
			try {
				stm = con.prepareStatement(sql);
				stm.setTimestamp(1, Ngay.tuNgay(d1));
				stm.setTimestamp(2, Ngay.toiNgay(d2));
				stm.setString(3, makh);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					String maHD = rs.getString("maHoaDon");
					Date ngayLap = rs.getDate("ngayLap");
					NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
					KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
					HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);
					ds.add(hd);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ds;
		}
}
