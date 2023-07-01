package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DiaChi;
import entity.NhaCungCap;

public class Dao_NhaCungCap {
	private Dao_DiaChi dao_DiaChi;

	public Dao_NhaCungCap() {
		// TODO Auto-generated constructor stub
		dao_DiaChi = new Dao_DiaChi();
	}

	public ArrayList<NhaCungCap> getAllNCC() {
		ArrayList<NhaCungCap> dsncc = new ArrayList<NhaCungCap>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select maNCC,tenNCC,sdt, phuongXa, quanHuyen, tinhTP from NhaCungCap join DiaChi on NhaCungCap.maDC = DiaChi.maDC";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNCC = rs.getString("maNCC");
				String tenNCC = rs.getString("tenNCC");
				String sdt = rs.getString("sdt");
				DiaChi diaChi = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, sdt, diaChi);
				dsncc.add(ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsncc;
	}
	public String getMaNCCTheoTen(String tenNCC) {
		String ma = "";
		String sql = "Select maNCC from NhaCungCap where tenNCC like ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			s = con.prepareStatement(sql);
			s.setString(1, tenNCC);
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
	public String getTenNCCTheoMa(String maLoai) {
		String sql = "Select tenNCC from NhaCungCap where maNCC = ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		String ma = "";
		try {
			s = con.prepareStatement(sql);
			s.setString(1, maLoai);
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
	public boolean themNCC(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		if(getMaNCCTheoSDT(ncc.getSdt()) != "") {
			return false;
		}
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhaCungCap values(?, ?, ?, ?)");
			stmt.setString(1, ncc.getMaNCC());
			stmt.setString(2, ncc.getTenNCC());
			stmt.setString(3, ncc.getSdt());
			stmt.setString(4, dao_DiaChi.getMaDCTheoTinhHuyenXa(ncc.getDiaChi().getTinhTP(),
					ncc.getDiaChi().getQuanHuyen(), ncc.getDiaChi().getPhuongXa()));
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean xoaNCC(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "delete NhaCungCap where maNCC = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean suaNCC(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
//		if(getMaNCCTheoSDT(ncc.getSdt()) != "") {
//			return false;
//		}
		int n = 0;
		String sql = "update NhaCungCap set tenNCC =?, sdt =?, maDC =? where maNCC =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getSdt());
			stmt.setString(3, dao_DiaChi.getMaDCTheoTinhHuyenXa(ncc.getDiaChi().getTinhTP(),
					ncc.getDiaChi().getQuanHuyen(), ncc.getDiaChi().getPhuongXa()));
			stmt.setString(4, ncc.getMaNCC());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public ArrayList<NhaCungCap> getNCCTheoMa(String ma) {
		ArrayList<NhaCungCap> dsncc = new ArrayList<NhaCungCap>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "select * from NhaCungCap join DiaChi on NhaCungCap.maDC = DiaChi.maDC where maNCC =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String sdt = rs.getString(3);
				DiaChi dc = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, sdt, dc);
				dsncc.add(ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsncc;
	}
	public ArrayList<NhaCungCap> getNCCTheoDK(String ma,String ten,String sd,String tinh){
		ArrayList<NhaCungCap> ds = new ArrayList<NhaCungCap>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "select maNCC,tenNCC,sdt, phuongXa, quanHuyen, tinhTP from NhaCungCap join DiaChi on NhaCungCap.maDC = DiaChi.maDC\r\n"
					+ "where maNCC like ? and tenNCC like ? and sdt like ? and tinhTP like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ma);
			statement.setString(2,ten);
			statement.setString(3,sd);
			statement.setString(4,"%"+tinh+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String sdt = rs.getString(3);
				DiaChi dc = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, sdt, dc);
				ds.add(ncc);
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
	public String getMaNCCTheoSDT(String sdt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "select maNCC from NhaCungCap where sdt = ?";
		String ma = "";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sdt);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			ma = rs.getString(1);
		} catch (SQLException e) {
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ma;
	}
	
}