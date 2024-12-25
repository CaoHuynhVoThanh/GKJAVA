package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.HoaDon;

public class HoaDon_DAO {
	public static boolean themHoaDon(HoaDon hoaDon) {
		int n = 0;
		ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO HoaDon ( maHD, maNV, thoiGian, thanhTien, tongSoLuongSP, phuongThucThanhToan) VALUES (?,?,?,?,?,?)";
		try {
			pstm = conN.prepareStatement(sql);
			pstm.setString(1, hoaDon.getMaHD());
			pstm.setString(2, hoaDon.getMaNV());
			pstm.setTimestamp(3, Timestamp.valueOf(hoaDon.getThoiGian()));
			pstm.setDouble(4, hoaDon.getThanhTien());
			pstm.setInt(5, hoaDon.getTongSoLuongSP());
			pstm.setString(6, hoaDon.getPhuongThucTT());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public static ArrayList<HoaDon> layTatCaHoaDon() {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		String sql = "{call layTatCaHoaDon}";
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	String maHD = rs.getString("maHD");
	        	String maNV = rs.getString("maNV");
	        	Timestamp timestamp = rs.getTimestamp("thoiGian");
	        	LocalDateTime thoiGian = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	        	int soLuongSP = rs.getInt("tongSoLuongSP");
	        	double thanhTien = rs.getDouble("thanhTien");
	        	String phuongThucTT = rs.getString("phuongThucThanhToan");
	        	HoaDon hd = new HoaDon(maHD, maNV, thoiGian, soLuongSP, thanhTien, phuongThucTT);
	        	dsHD.add(hd);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return dsHD;
	}
	
}
