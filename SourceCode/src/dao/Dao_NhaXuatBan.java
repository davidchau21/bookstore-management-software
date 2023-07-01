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
import entity.NhaXuatBan;

public class Dao_NhaXuatBan {
	private Dao_DiaChi daoDiaChi;
	public Dao_NhaXuatBan() {
		daoDiaChi = new Dao_DiaChi();
	}
	public ArrayList<NhaXuatBan> getAllNXB() {
		ArrayList<NhaXuatBan> dsnxb = new ArrayList<NhaXuatBan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select maNXB,tenNXB,sdt, phuongXa, quanHuyen, tinhTP from NhaXuatBan join DiaChi on NhaXuatBan.maDC = DiaChi.maDC";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNXB = rs.getString("maNXB");
				String tenNXB = rs.getString("tenNXB");
				String sdt = rs.getString("sdt");
				DiaChi diaChi = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, sdt, diaChi);
				dsnxb.add(nxb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnxb;
	}
	public String getMaNXBTheoTen(String tenNXB) {
		String ma = "";
		String sql = "Select maNXB from NhaXuatBan where tenNXB like ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			s = con.prepareStatement(sql);
			s.setString(1, tenNXB);
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
	public String getTenNXBTheoMa(String maLoai) {
		String sql = "Select tenNXB from NhaXuatBan where maNXB = ?";
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
	public ArrayList<NhaXuatBan> getNXBTheoDK(String ma,String ten,String sd,String tinh){
		ArrayList<NhaXuatBan> ds = new ArrayList<NhaXuatBan>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "select maNXB,tenNXB,sdt, phuongXa, quanHuyen, tinhTP from NhaXuatBan join DiaChi on NhaXuatBan.maDC = DiaChi.maDC\r\n"
					+ "where maNXB like ? and tenNXB like ? and sdt like ? and tinhTP like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ma);
			statement.setString(2,ten);
			statement.setString(3,sd);
			statement.setString(4,"%"+tinh+"%");
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maNXB = rs.getString("maNXB");
				String tenNXB = rs.getString("tenNXB");
				String sdt = rs.getString("sdt");
				DiaChi diaChi = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
				NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, sdt, diaChi);
				ds.add(nxb);
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
	public boolean xoaNXB(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n= 0;
		String sql = "delete NhaXuatBan where maNXB = ?";
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
	public boolean themNXB(NhaXuatBan nxb) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		if(getMaNXBTheoSDT(nxb.getSdt()) != "") {
			return false;
		}
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhaXuatBan values(?, ?, ?, ?)");
			stmt.setString(1, nxb.getMaNXB());
			stmt.setString(2, nxb.getTenNXB());
			stmt.setString(3, nxb.getSdt());
			stmt.setString(4, daoDiaChi.getMaDCTheoTinhHuyenXa(nxb.getDiaChi().getTinhTP(),
					nxb.getDiaChi().getQuanHuyen(), nxb.getDiaChi().getPhuongXa()));
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
	
	public boolean suaNXB(NhaXuatBan nxb) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		if(getMaNXBTheoSDT(nxb.getSdt()) != "") {
			return false;
		}
		int n = 0;
		String sql = "update NhaXuatBan set tenNXB =?, sdt =?, maDC =? where maNXB =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nxb.getTenNXB());
			stmt.setString(2, nxb.getSdt());
			stmt.setString(3, daoDiaChi.getMaDCTheoTinhHuyenXa(nxb.getDiaChi().getTinhTP(),
					nxb.getDiaChi().getQuanHuyen(), nxb.getDiaChi().getPhuongXa()));
			stmt.setString(4, nxb.getMaNXB());
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
	public String getMaNXBTheoSDT(String sdt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "select maNXB from NhaXuatBan where sdt = ?";
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