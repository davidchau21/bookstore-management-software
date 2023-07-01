package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;

public class Dao_ChiTietHoaDon {
	public ArrayList<ChiTietHoaDon> getAllCTHDTheoMa(String maHD){
		ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Select * from ChiTietHoaDon where maHoaDon like ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,maHD);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString("maSanPham");
				String maHoaDon = rs.getString("maHoaDon");
				int soLuong = rs.getInt("soLuong");
				ChiTietHoaDon cthd = new ChiTietHoaDon(soLuong, new SanPham(maSP), new HoaDon(maHoaDon));
				ds.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public boolean themCTHD(ChiTietHoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n=0;
		String sql = "Insert into ChiTietHoaDon values(?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1,hd.getSoLuong());
			stm.setString(2,hd.getSanPham().getMaSanPham());
			stm.setString(3,hd.getHoaDon().getMaHoaDon());
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
	public boolean xoaCTHD(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		String sql = "delete ChiTietHoaDon where maHoaDon=?";
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
}
