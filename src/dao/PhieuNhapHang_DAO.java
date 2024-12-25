package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.PhieuNhapHang;

public class PhieuNhapHang_DAO {
	public static ArrayList<PhieuNhapHang> layTatCaHoaDon() {
		ArrayList<PhieuNhapHang> dsPNH = new ArrayList<PhieuNhapHang>();
		Connection con = ConnectDB.getInstance().getConnection();
		String sql = "{call layTatCaPhieuNhapHang}";
		CallableStatement stmt = null;
		
		try {
			stmt = con.prepareCall(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	String maPNH = rs.getString("maPhieuNhapHang");
	        	String maNV = rs.getString("maNV");
	        	String tenNV = NhanVien_DAO.getNV(maNV).getTenNV();
	        	Timestamp timestamp = rs.getTimestamp("thoiGian");
	        	LocalDateTime thoiGian = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	        	int soLuongSP = rs.getInt("tongSoLuong");
	        	double thanhTien = rs.getDouble("thanhTien");
	        	PhieuNhapHang pnh = new PhieuNhapHang(maPNH, maNV, tenNV, thoiGian, soLuongSP, thanhTien);
	        	dsPNH.add(pnh);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return dsPNH;
	}
}
