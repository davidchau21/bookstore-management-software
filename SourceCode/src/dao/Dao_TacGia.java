package dao;

import java.sql.Connection;
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
import entity.SanPham;
import entity.TacGia;
import entity.XuatXu;

public class Dao_TacGia {
	public ArrayList<TacGia> getAllTG(){
		ArrayList<TacGia> ds = new ArrayList<TacGia>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from TacGia";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maTG = rs.getString("maTG");
				String hoTen = rs.getString("hoTen");
				int namSinh = rs.getInt("namSinh");
				TacGia tg = new TacGia(maTG, hoTen, namSinh);
				ds.add(tg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public String getMaTheoTen(String tenTG) {
		String ma = "";
		String sql = "Select maTG from TacGia where hoTen like ?";
		PreparedStatement s = null;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			s = con.prepareStatement(sql);
			s.setString(1, tenTG);
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
	public ArrayList<TacGia> getTGTheoDK(String ma,String ten,String ns){
		ArrayList<TacGia> ds = new ArrayList<TacGia>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql = "Select * from TacGia\r\n"
					+ "where maTG like ? and hoTen like ? and namSinh like ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ma);
			statement.setString(2,ten);
			statement.setString(3,ns);
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String maTG = rs.getString("maTG");
				String hoTen = rs.getString("hoTen");
				int namSinh = rs.getInt("namSinh");
				TacGia tg = new TacGia(maTG, hoTen, namSinh);
				ds.add(tg);
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
	public String getTenTGTheoMa(String maTG) {
		String tenTG = "";
		String sql = "select hoTen from TacGia where maTG = N'"+maTG+"'";
		PreparedStatement statement = null;
		try {
			statement = ConnectDB.getConnection().prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			while(r.next()) {
				tenTG = r.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tenTG;
	}
	public boolean xoaTG(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "delete TacGia where maTG = ?";
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

	public boolean themTacGia(TacGia tg) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into TacGia values(?, ?, ?)");
			stmt.setString(1, tg.getMaTG());
			stmt.setString(2, tg.getHoTen());
			stmt.setInt(3, tg.getNamSinh());
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

	public boolean suaTacGia(TacGia tg) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "update TacGia set hoTen =?, namSinh =? where maTG =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tg.getHoTen());
			stmt.setInt(2, tg.getNamSinh());
			stmt.setString(3, tg.getMaTG());
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

	public TacGia getTacGiaTheoMa(String ma) {
		TacGia tg = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "Select * from TacGia where maTG = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maTg = rs.getString(1);
				String hoTen = rs.getString(2);
				int ns = rs.getInt(3);
				tg = new TacGia(maTg, hoTen, ns);
				
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
		return tg;
	}
}
