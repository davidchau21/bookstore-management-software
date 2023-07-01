package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.TacGia;
import entity.XuatXu;

public class Dao_XuatXu {
	public ArrayList<XuatXu> getAllXuatXu(){
		ArrayList<XuatXu> dsnv = new ArrayList<XuatXu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from XuatXu";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maXX = rs.getString("maXX");
				String tenNuocXX = rs.getString("tenNuocXX");
				XuatXu nv = new XuatXu(maXX, tenNuocXX);
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	public ArrayList<XuatXu> getXXTheoDK(String ma,String ten){
		ArrayList<XuatXu> ds = new ArrayList<XuatXu>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from XuatXu\r\n"
					+ "where maXX like ? and tenNuocXX like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ma);
			statement.setString(2,ten);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maXX = rs.getString("maXX");
				String tenNuocXX = rs.getString("tenNuocXX");
				XuatXu nv = new XuatXu(maXX, tenNuocXX);
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
	public String getXuatXuTheoMaXX(String maXX) {
		String sql = "Select tenNuocXX from XuatXu where maXX = ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		String ma = "";
		try {
			s = con.prepareStatement(sql);
			s.setString(1, maXX);
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
	public String getMaXXTheoXX(String xx) {
		String ma = "";
		String sql = "Select maXX from XuatXu where tenNuocXX like ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			s = con.prepareStatement(sql);
			s.setString(1, xx);
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
	public boolean xoaXX(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "delete XuatXu where maXX = ?";
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

	public boolean themXX(XuatXu tg) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into XuatXu values(?, ?)");
			stmt.setString(1, tg.getMaXX());
			stmt.setString(2, tg.getTenNuocXX());
			;
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

	public boolean suaXX(XuatXu tg) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "update XuatXu set tenNuocXX =? where maXX =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tg.getTenNuocXX());
			stmt.setString(2, tg.getMaXX());
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
}
