package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connectDB.ConnectDB;
import entity.DiaChi;

public class Dao_DiaChi {
	
	public Vector<String> getAllTinh() {
		Vector<String> v = new Vector<>();
		v.add("Tỉnh/Thành phố");
		String sql = "select tinhTP from DiaChi group by tinhTP";
		PreparedStatement statement = null;
		try {
			statement = ConnectDB.getConnection().prepareStatement(sql);

			ResultSet r = statement.executeQuery();

			while (r.next()) {
				v.add(r.getString(1 ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public Vector<String> getAllHuyenTheoTinh(String tinh) {
		Vector<String> v = new Vector<>();
		v.add("Quận/Huyện");
		String sql = "select quanHuyen from DiaChi where tinhTP = N'"+tinh+"' group by quanHuyen";
		PreparedStatement statement = null;
		try {
			statement = ConnectDB.getConnection().prepareStatement(sql);

			ResultSet r = statement.executeQuery();

			while (r.next()) {
				v.add(r.getString(1 ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public Vector<String> getAllPhuongXaTheoTinhVaHuyen(String tinh, String huyen) {
		Vector<String> v = new Vector<>();
		v.add("Phường/Xã");
		String sql = "select phuongXa from DiaChi where quanHuyen = N'"+huyen+"' and tinhTP = N'"+tinh+"' group by phuongXa";
		PreparedStatement statement = null;
		try {
			statement = ConnectDB.getConnection().prepareStatement(sql);

			ResultSet r = statement.executeQuery();

			while (r.next()) {
				v.add(r.getString(1 ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public String getMaDCTheoTinhHuyenXa(String tinh, String huyen, String xa) {
		String ma = "";
		String sql = "select maDC from DiaChi where tinhTP = N'"+tinh+"' and quanHuyen = N'"+huyen+"' and phuongXa = N'"+xa+"'";
		PreparedStatement statement = null;
		try {
			statement = ConnectDB.getConnection().prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			r.next();
			ma = r.getString(1);
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
		return ma;
	}
	
	//---------Diem-------------------
	public DiaChi timDiaChiTheoMa(String maDC) {

		String sql = "select tinhTP, quanHuyen, phuongXa from DiaChi where maDC=?";
		PreparedStatement statement = null;
		DiaChi dc = new DiaChi();

		try {

			statement = ConnectDB.getConnection().prepareStatement(sql);
			statement.setString(1, maDC);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				dc = new DiaChi(rs.getString("tinhTP"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return dc;
	}
	//--------------------------------
}

