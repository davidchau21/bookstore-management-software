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
import until.Ngay;

public class Dao_KhachHang {
	Dao_DiaChi dao_DiaChi = new Dao_DiaChi();
	public Dao_KhachHang() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<KhachHang> getAllKhachHang(){
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT  KhachHang.maKhachHang, KhachHang.hoTen, KhachHang.sdt, KhachHang.gioiTinh, DiaChi.tinhTP, DiaChi.quanHuyen, DiaChi.phuongXa\r\n"
				+ "FROM  KhachHang INNER JOIN DiaChi ON KhachHang.maDC = DiaChi.maDC";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString("maKhachHang");
				String hoTen = rs.getString("hoTen");
				String sdt = rs.getString("sdt");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				DiaChi diaChi = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				KhachHang kh = new KhachHang(maNV, hoTen, sdt, diaChi, gioiTinh);
				ds.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<KhachHang> getNhanVienTheoDK(String ma,String hoT,String sd,String gt){
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "SELECT  KhachHang.maKhachHang, KhachHang.hoTen, KhachHang.sdt, KhachHang.gioiTinh, DiaChi.tinhTP, DiaChi.quanHuyen, DiaChi.phuongXa \r\n"
					+ "FROM  KhachHang INNER JOIN DiaChi ON KhachHang.maDC = DiaChi.maDC\r\n"
					+ "Where maKhachHang like ? and hoTen like ? and sdt like ? and gioiTinh like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ma);
			statement.setString(2,hoT);
			statement.setString(3,sd);
			statement.setString(4,"%"+gt+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maNV = rs.getString("maKhachHang");
				String hoTen = rs.getString("hoTen");
				String sdt = rs.getString("sdt");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				DiaChi diaChi = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				KhachHang kh = new KhachHang(maNV, hoTen, sdt, diaChi, gioiTinh);
				ds.add(kh);
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
	public KhachHang getKhachHangTheoMa(String ma) {
		KhachHang kh= null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from KhachHang where maKhachHang = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,ma);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString("maKhachHang");
				String hoTen = rs.getString("hoTen");
				String sdt = rs.getString("sdt");
				kh = new KhachHang(maNV, hoTen,sdt);
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
		return kh;
	}
	public boolean themKhachHang(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
//		if (getMaKHTheoSDT(kh.getSdt()) != "")
//			return false;
		int n=0;
		String sql = "Insert into KhachHang values(?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,kh.getMaKhachHang());
			stm.setString(2,kh.getHoTen());
			stm.setString(3, kh.getSdt());
			stm.setString(4, dao_DiaChi.getMaDCTheoTinhHuyenXa(kh.getDiaChi().getTinhTP(), kh.getDiaChi().getQuanHuyen(),
					kh.getDiaChi().getPhuongXa()));
			stm.setBoolean(5, kh.isGioiTinh());
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
	public boolean suaKhachHang(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
//		if (getMaKHTheoSDT(kh.getSdt()) != "")
//			return false;
		int n=0;
		String sql = "Update KhachHang set hoTen=?,sdt=?,maDC=?,gioiTinh=? where maKhachHang=?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(5,kh.getMaKhachHang());
			stm.setString(1,kh.getHoTen());
			stm.setString(2, kh.getSdt());
			stm.setString(3, dao_DiaChi.getMaDCTheoTinhHuyenXa(kh.getDiaChi().getTinhTP(), kh.getDiaChi().getQuanHuyen(),
					kh.getDiaChi().getPhuongXa()));
			stm.setBoolean(4, kh.isGioiTinh());
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
	public boolean xoaKhachHang(String nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Delete KhachHang where maKhachHang=?";
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
	public KhachHang getKHTheoSDT(String sd) {
		KhachHang nv= null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from KhachHang where sdt = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,sd);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maKH = rs.getString("maKhachHang");
				String hoTen = rs.getString("hoTen");
				String sdt = rs.getString("sdt");
				DiaChi dc = dao_DiaChi.timDiaChiTheoMa(rs.getString("maDC"));
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				nv = new KhachHang(maKH, hoTen, sdt, dc, gioiTinh);
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
	public String getMaKHTheoSDT(String sdt) {
		String sql = "select maKhachHang from KhachHang where sdt = ?";
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
	public ArrayList<KhachHang> thongKeKhachHang(int minTop) {
		ArrayList<KhachHang> ls = new ArrayList<KhachHang>();
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select top " + minTop + "KhachHang.maKhachHang, hoTen, gioiTinh, sdt, count(DISTINCT (HoaDon.maHoaDon)) as soHD, sum(ChiTietHoaDon.soLuong) as soSanPham\r\n"
					+ "from KhachHang join HoaDon on KhachHang.maKhachHang = HoaDon.maKhachHang \r\n"
					+ "join ChiTietHoaDon on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
					+ "group by KhachHang.maKhachHang, hoTen, gioiTinh, sdt \r\n"
					+ "order by soSanPham  desc";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gt = rs.getBoolean(3);
				String sdt = rs.getString(4);
				DiaChi soHD = new DiaChi(rs.getString(5)); // mượn cột dia chi để làm cột soHD
				KhachHang kh = new KhachHang(maKH, tenKH, sdt, soHD, gt);
				ls.add(kh);
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
	public ArrayList<KhachHang> thongKeKhachHang(Date tuNgay, Date toiNgay) {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select  KhachHang.maKhachHang, hoTen, gioiTinh, sdt, count (DISTINCT (HoaDon.maHoaDon)) as 'soHoaDon'\r\n"
					+ "												from KhachHang join HoaDon on KhachHang.maKhachHang = HoaDon.maKhachHang join ChiTietHoaDon on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
					+ "											where HoaDon.ngayLap >= ? and HoaDon.ngayLap <= ? and HoaDon.trangThai is null\r\n"
					+ "											group by KhachHang.maKhachHang, hoTen, gioiTinh, sdt";
			stmt = con.prepareStatement(sql);
			stmt.setTimestamp(1, Ngay.tuNgay(tuNgay));
			stmt.setTimestamp(2, Ngay.toiNgay(toiNgay));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				Boolean gioiTinh = rs.getBoolean(3);
				String sdt = rs.getString(4);
				DiaChi dc = new DiaChi(rs.getString(5)); //thay số hóa đơn
				KhachHang kh = new KhachHang(maKH, tenKH, sdt, dc, gioiTinh);
				dskh.add(kh);		
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
		return dskh;
	}
}
