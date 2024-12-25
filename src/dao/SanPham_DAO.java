package dao;

import java.sql.Timestamp;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.SanPham;

public class SanPham_DAO {
	public static SanPham timSanPham(String maSP) {
		Connection con = ConnectDB.getInstance().getConnection();
		String sql = "{call TimSanPham(?)}";
		CallableStatement stmt = null;
		SanPham sp =null;
		try {
			stmt = con.prepareCall(sql);
			stmt.setString(1, maSP);
	        // Thực thi stored procedure
	        ResultSet rs = stmt.executeQuery();
	        // Duyệt qua kết quả trả về
	        if (rs.next()) {
	            String ma = rs.getString("maSP");
	            String ten = rs.getString("tenSP");
	            double giaBan = rs.getDouble("giaBan");
	            double giaNhap = rs.getDouble("giaNhap");
	            String nhaCungCap = rs.getString("nhaCungCap");
	            Date date = rs.getDate("ngayCapNhat");
	            int soLuongTon = rs.getInt("soLuongTon");
	            sp = new SanPham(ma, ten, giaNhap, giaBan, soLuongTon, nhaCungCap, date.toLocalDate());
	        } else {
	        	System.out.println("không tồn tại mã sản phẩm");
	        	return null;
	        }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sp;
	}
	public static void capNhatSoLuongTon(int newSoLuongTon, String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		String sql = "UPDATE SanPham SET soLuongTon = ? WHERE maSP = ?";
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, newSoLuongTon);
	        pstm.setString(2, ma);
	        pstm.executeUpdate();

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
	}
	public static ArrayList<String> laytop5SPTrongNgay(String selectDate) {
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "{call layTop10SPTrongNgay(?)}";
        CallableStatement stmt = null;
        ArrayList<String> s = new ArrayList<String>();
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1,selectDate);
            ResultSet rs = stmt.executeQuery();
            int top =0;
            while (rs.next()) {
            	top++;
            	String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                int soLuongSP = rs.getInt("totalQuantity");
                s.add( String.format("%d,%s,%s,%d", top, maSP, tenSP, soLuongSP));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return s;
    }
	public static ArrayList<String> layTop10SPTrongTuan(String selectDate) {
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "{call layTop10SPTrongTuan(?)}";
        CallableStatement stmt = null;
        ArrayList<String> s = new ArrayList<String>();
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1,selectDate);
            ResultSet rs = stmt.executeQuery();
            int top =0;
            while (rs.next()) {
            	top++;
            	String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                int soLuongSP = rs.getInt("totalQuantity");
                s.add( String.format("%d,%s,%s,%d", top, maSP, tenSP, soLuongSP));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return s;
    }
	public static ArrayList<String> laytop5SPTrongThang(String selectDate) {
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "{call layTop10SPTrongThang(?)}";
        CallableStatement stmt = null;
        ArrayList<String> s = new ArrayList<String>();
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1,selectDate);
            ResultSet rs = stmt.executeQuery();
            
            int top =0;
            while (rs.next()) {
            	top++;
            	String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                int soLuongSP = rs.getInt("totalQuantity");
                s.add( String.format("%d,%s,%s,%d", top, maSP, tenSP, soLuongSP));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return s;
    }
}