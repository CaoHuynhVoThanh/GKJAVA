package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.ChiTietHoaDon;
import entities.ChiTietNhapHang;

public class ChiTietNhapHang_DAO {
	public static ArrayList<ChiTietNhapHang> timChiTietNhapHang(String maPNH) {
		Connection con = ConnectDB.getInstance().getConnection();
		String sql = "{call TimChiTietNhapHang(?)}";
		CallableStatement stmt = null;
		ArrayList<ChiTietNhapHang> dsChiTietPNH = new ArrayList<ChiTietNhapHang>();
		try {
			stmt = con.prepareCall(sql);
			stmt.setString(1, maPNH);
	        // Thực thi stored procedure
	        ResultSet rs = stmt.executeQuery();
	        // Duyệt qua kết quả trả về
	        while (rs.next()) {
	            String ma = rs.getString("maPhieuNhapHang");
	            String maSP = rs.getString("maSP");
	            int soLuongSP = rs.getInt("soLuongSP");
	            double donGia = rs.getDouble("donGia");
	            ChiTietNhapHang chiTietPNH = new ChiTietNhapHang(maPNH, maSP, soLuongSP, donGia);
	            dsChiTietPNH.add(chiTietPNH);
	  
	        } 
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dsChiTietPNH;
	}
}
