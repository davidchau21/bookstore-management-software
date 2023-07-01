package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectDB.ConnectDB;

public class Dao_PhatSinhMa {
	public String getMaNV() {
		String maNV="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('NV', RIGHT(CONCAT('000',ISNULL(right(max(maNhanVien),3),0) + 1),3)) from [dbo].[NhanVien] where maNhanVien like 'NV%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNV = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNV;
	}
	public String getMaSP() {
		String maNV="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('SP', RIGHT(CONCAT('000',ISNULL(right(max(maSanPham),3),0) + 1),3)) from [dbo].[SanPham] where maSanPham like 'SP%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNV = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNV;
	}
	public String getMaKH() {
		String maNV="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('KH', RIGHT(CONCAT('000',ISNULL(right(max(maKhachHang),3),0) + 1),3)) from [dbo].[KhachHang] where maKhachHang like 'KH%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNV = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNV;
	}
	public String getMaHD() {
		String maNV="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('HD', RIGHT(CONCAT('000',ISNULL(right(max(maHoaDon),3),0) + 1),3)) from [dbo].[HoaDon] where maHoaDon like 'HD%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNV = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNV;
	}
	public String getMaNCC() {
		String maNCC="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('NCC', RIGHT(CONCAT('000',ISNULL(right(max(maNCC),3),0) + 1),3)) from [dbo].[NhaCungCap] where maNCC like 'NCC%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNCC = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNCC;
	}
	public String getMaNXB() {
		String maNXB="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('NXB', RIGHT(CONCAT('000',ISNULL(right(max(maNXB),3),0) + 1),3)) from [dbo].[NhaXuatBan] where maNXB like 'NXB%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maNXB = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNXB;
	}
	public String getMaTG() {
		String maTG="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('TG', RIGHT(CONCAT('000',ISNULL(right(max(maTG),3),0) + 1),3)) from [dbo].[TacGia] where maTG like 'TG%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maTG = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maTG;
	}
	public String getMaHoaDon() {
		String maHD="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('HD', RIGHT(CONCAT('000',ISNULL(right(max(maHD),3),0) + 1),3)) from [dbo].[HoaDon] where maHD like 'HD%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maHD = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maHD;
	}
	public String getMaLoaiSP() {
		String maLoaiSP="";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('L', RIGHT(CONCAT('000',ISNULL(right(max(maLoai),3),0) + 1),3)) from [dbo].[LoaiSanPham] where maLoai like 'L%'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				maLoaiSP = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maLoaiSP;
	}
}
