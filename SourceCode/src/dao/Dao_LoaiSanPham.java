package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiSanPham;
import entity.TacGia;

public class Dao_LoaiSanPham {
	public ArrayList<String> getAllLoaiSP() {
		ArrayList<String> ds = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select tenLoai from LoaiSanPham Group by tenLoai";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String tenLoai = rs.getString("tenLoai");
				ds.add(tenLoai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public String getTenLoaiTheoMaSP(String maLoai) {
		String sql = "SELECT  LoaiSanPham.tenLoai\r\n" + "	FROM	LoaiSanPham INNER JOIN\r\n"
				+ "    SanPham ON LoaiSanPham.maLoai = SanPham.maLoai\r\n" + "Where maSanPham = ?";
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

	public ArrayList<LoaiSanPham> getAllloaiSP() {
		ArrayList<LoaiSanPham> dsloaisp = new ArrayList<LoaiSanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from LoaiSanPham";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maLoaisp = rs.getString(1);
				String tenLoai = rs.getString(2);
				String chiTiet = rs.getString(3);
				LoaiSanPham loaisp = new LoaiSanPham(maLoaisp, tenLoai, chiTiet);
				dsloaisp.add(loaisp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsloaisp;
	}

	public String getMaTheoChiTietLoai(String chiTiet) {
		String maLoai = "";
		String sql = "select maLoai from LoaiSanPham where chiTiet = N'" + chiTiet + "'";
		PreparedStatement statement = null;
		try {
			statement = ConnectDB.getConnection().prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			r.next();
			maLoai = r.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return maLoai;
	}

	public ArrayList<LoaiSanPham> getLSPTheoDK(String ma, String loai, String ct) {
		ArrayList<LoaiSanPham> ds = new ArrayList<LoaiSanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select * from LoaiSanPham\r\n" + "where maLoai like ? and tenLoai like ? and chiTiet like ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			statement.setString(2, "%" + loai + "%");
			statement.setString(3, "%" + ct + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String maLoaisp = rs.getString(1);
				String tenLoai = rs.getString(2);
				String chiTiet = rs.getString(3);
				LoaiSanPham loaisp = new LoaiSanPham(maLoaisp, tenLoai, chiTiet);
				ds.add(loaisp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}

	public ArrayList<String> getAllChiTietVPP() {
		ArrayList<String> ds = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select chiTiet from LoaiSanPham where tenLoai not like 'Sách'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String chiTiet = rs.getString("chiTiet");
				ds.add(chiTiet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<LoaiSanPham> getLoaiTheoDM(String dm) {
		ArrayList<LoaiSanPham> ds = new ArrayList<LoaiSanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from LoaiSanPham where tenLoai like N'" + dm + "'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maLoai = rs.getString("maLoai");
				String tenLoai = rs.getString("tenLoai");
				String chiTiet = rs.getString("chiTiet");
				LoaiSanPham lsp = new LoaiSanPham(maLoai, tenLoai, chiTiet);
				ds.add(lsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public String getChiTietTheoMaLoai(String maLoai) {
		String sql = "Select ChiTiet from LoaiSanPham where maLoai = ?";
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

	public String getMaLoaiTheoChiTiet(String chiTiet) {
		String ma = "";
		String sql = "Select maLoai from LoaiSanPham where chiTiet like ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			s = con.prepareStatement(sql);
			s.setString(1, chiTiet);
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

	public boolean themLoaiSP(LoaiSanPham lsp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LoaiSanPham values(?, ?, ?)");
			stmt.setString(1, lsp.getMaLoai());
			stmt.setString(2, lsp.getTenLoai());
			stmt.setString(3, lsp.getChiTiet());

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

	public boolean suaLoaiSP(LoaiSanPham lsp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "update LoaiSanPham set tenLoai =?, chiTiet =? where maLoai =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, lsp.getTenLoai());
			stmt.setString(2, lsp.getChiTiet());
			stmt.setString(3, lsp.getMaLoai());
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

	public boolean xoaLoaiSP(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "delete LoaiSanPham where maLoai = ?";
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

	public ArrayList<LoaiSanPham> getLoaiSach() {
		ArrayList<LoaiSanPham> list = new ArrayList<LoaiSanPham>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "select * from LoaiSanPham where tenLoai like 'Sách'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String tenloai = rs.getString(2);
				String chiTiet = rs.getString(3);
				LoaiSanPham lsp = new LoaiSanPham(ma, tenloai, chiTiet);
				list.add(lsp);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return list;
	}
}
