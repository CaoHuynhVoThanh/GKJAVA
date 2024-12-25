package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.SanPham;

public class ChiTietHoaDon_DAO {
	public static boolean themChiTietHoaDon(ChiTietHoaDon chiTietHD) {
		int n = 0;
		ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO ChiTietHoaDon ( maHD, maSP, soLuongSP, donGia) VALUES (?,?,?,?)";
		try {
			pstm = conN.prepareStatement(sql);
			pstm.setString(1, chiTietHD.getMaHD());
			pstm.setString(2, chiTietHD.getMaSP());
			pstm.setInt(3,chiTietHD.getSoLuongSP());
			pstm.setDouble(4, chiTietHD.getDonGia());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public static ArrayList<ChiTietHoaDon> timChiTietHoaDon(String maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		String sql = "{call TimChiTietHoaDon(?)}";
		CallableStatement stmt = null;
		ArrayList<ChiTietHoaDon> dsChiTietHd = new ArrayList<ChiTietHoaDon>();
		try {
			stmt = con.prepareCall(sql);
			stmt.setString(1, maHD);
	        // Thực thi stored procedure
	        ResultSet rs = stmt.executeQuery();
	        // Duyệt qua kết quả trả về
	        while (rs.next()) {
	            String ma = rs.getString("maHD");
	            String maSP = rs.getString("maSP");
	            int soLuongSP = rs.getInt("soLuongSP");
	            double donGia = rs.getDouble("donGia");
	            ChiTietHoaDon chiTietHd = new ChiTietHoaDon(maHD, maSP, soLuongSP, donGia);
	            dsChiTietHd.add(chiTietHd);
	  
	        } 
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dsChiTietHd;
	}
}
