package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class Dao_TaiKhoan {
	Dao_NhanVien dao_NhanVien = new Dao_NhanVien();
	public Dao_TaiKhoan() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public TaiKhoan getTaiKhoanTheoTKMK(String maTK,String pw) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		TaiKhoan tk = null;
		String sql = "Select * from TaiKhoan where maTK = ? and matKhau = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maTK);
			stmt.setString(2, pw);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				tk = new TaiKhoan(maTK, maTK,new NhanVien(maTK));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	public TaiKhoan getTaiKhoanTheoMaNV(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		TaiKhoan tk = null;
		String sql = "select * from TaiKhoan where maNhanVien = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tk = new TaiKhoan(maNV, sql, new NhanVien(maNV));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	
	public ArrayList<String> getAllTaiKhoan(){
		ArrayList<String> ds = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select maTk from TaiKhoan";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String tenLoai = rs.getString("maTK");
				ds.add(tenLoai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public boolean themTaiKhoan(TaiKhoan nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Insert into TaiKhoan values(?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,nv.getMaTaiKhoan());
			stm.setString(2,nv.getMatKhau());
			stm.setString(3, nv.getMaTaiKhoan());
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
	public boolean xoaTaiKhoan(String nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Delete TaiKhoan where maTK=?";
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
	public boolean resetPass(String mk, String sdt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		String sql = "UPDATE TaiKhoan set matKhau = ? "
				+ "from TaiKhoan join NhanVien on NhanVien.maNhanVien = TaiKhoan.maNhanVien "
				+ "where NhanVien.sdt = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, mk);
			stm.setString(2, sdt);
			n = stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
}
