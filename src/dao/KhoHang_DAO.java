package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;

import connectDB.ConnectDB;
import entities.QuanLySanPham;
import entities.SanPham;
import frames.Application;

public class KhoHang_DAO {
	public static void removeItem(String id){
		Connection conn = ConnectDB.getInstance().getConnection();
        PreparedStatement pstmt = null;

        String sql = "Delete From ChiTietNhapHang Where maSP=?";
        		
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
	        pstmt.executeUpdate();
	        
	        sql = "Delete From ChiTietHoaDon Where MaSP=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.executeUpdate();
	        
	        sql = "DELETE FROM SanPham Where maSP=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void addItem(SanPham x) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        conn = ConnectDB.getInstance().getConnection();

        String sql = "INSERT INTO SanPham (maSP, tenSP, giaNhap, giaBan, nhaCungCap, ngayCapNhat, soLuongTon) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, x.getMaSP()); 
	        pstmt.setString(2, x.getTenSP());  
	        pstmt.setDouble(3, x.getGiaNhap());
	        pstmt.setDouble(4, x.getGiaBan());
	        pstmt.setString(5, x.getNhaCC());
	        pstmt.setDate(6, Date.valueOf(x.getThoiGian()));
	        pstmt.setInt(7, x.getSoLuongTon());

	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(SanPham x) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        conn = ConnectDB.getInstance().getConnection();

        String sql = "Update SanPham set tenSP=?, giaNhap=?, giaBan=?, nhaCungCap=?, ngayCapNhat=? where maSP=?";
        
        try {
			pstmt = conn.prepareStatement(sql);
			 pstmt.setString(6, x.getMaSP()); 
	        pstmt.setString(1, x.getTenSP());        
	        pstmt.setDouble(2, x.getGiaNhap());
	        pstmt.setDouble(3, x.getGiaBan());
	        pstmt.setString(4, x.getNhaCC());
	        pstmt.setDate(5, Date.valueOf(x.getThoiGian()));
	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sinhHoaDonNhap(QuanLySanPham x) {
		String sql = "SELECT COUNT(*) FROM PhieuNhapHang;";
		int num=0;
		Connection con = ConnectDB.getInstance().getConnection();
        System.out.println("Kết nối thành công!");
        Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	num=rs.getInt(1)+1;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String ma = String.format("PN%05d", num);
        sql = "Insert into PhieuNhapHang (maPhieuNhapHang, maNV, thoiGian, tongSoLuong, thanhTien) Values (?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ma);
	        pstmt.setString(2, Application.account.getMaNV());
	        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
	        pstmt.setTimestamp(3, timestamp);
	        pstmt.setInt(4, x.getTongSo());
	        pstmt.setDouble(5, x.tinhTongTien());
	        pstmt.executeUpdate();
	        
	        sql = "Insert into ChiTietNhapHang (maPhieuNhapHang, maSP, soLuongSP, donGia) Values (?, ?, ?, ?)";
	        pstmt = con.prepareStatement(sql);
	        for (SanPham sp: x.getDs()) {
	        	pstmt.setString(1, ma);
	            pstmt.setString(2, sp.getMaSP());
	            pstmt.setInt(3, sp.getSoLuongTon());
	            pstmt.setDouble(4, sp.getGiaNhap());
	            pstmt.executeUpdate();
	            CallableStatement cs = con.prepareCall("{CALL GetSLSP(?, ?)}");
	            cs.setString(1, sp.getMaSP());
	            cs.registerOutParameter(2, Types.INTEGER);
	            cs.execute();
	            int slTon = cs.getInt(2);
	            SanPham_DAO.capNhatSoLuongTon(slTon, sp.getMaSP());
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static QuanLySanPham filter(String sql) {
		QuanLySanPham ds = new QuanLySanPham();
		Connection con = ConnectDB.getInstance().getConnection();
        System.out.println("Kết nối thành công!");
        Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	String ma = rs.getString(1);
	        	String name = rs.getString(2);
	        	Double price1 = rs.getDouble(3);
	        	Double price2 = rs.getDouble(4);
	        	String nguon = rs.getString(5);
	        	Date date = rs.getDate(6);
	        	int slton = rs.getInt(7);
	        	ds.themSP(new SanPham(ma, name, price2, price1, slton, nguon, date.toLocalDate()));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ds;
	}
	
	public static QuanLySanPham sort(String cond){
		QuanLySanPham ds = new QuanLySanPham();
        Connection con = ConnectDB.getInstance().getConnection();
        System.out.println("Kết nối thành công!");
        Statement st;
        String sql = "select * from SanPham order by";
        if (cond.equals("Ngày thêm mới nhất")) sql+=" ngayCapNhat ASC";
        else if (cond.equals("Ngày thêm cũ nhất")) sql+=" ngayCapNhat DESC";
        else if (cond.equals("Giá tăng dần")) sql+=" giaBan DESC";
        else if (cond.equals("Giá giảm dần")) sql+=" giaBan ASC";
        else if (cond.equals("Số lượng tăng dần")) sql+=" soLuongTon DESC";
        else if (cond.equals("Số lượng giảm dần")) sql+=" soLuongTon ASC";
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		       
	        while(rs.next()) {
	        	String id = rs.getString(1);
	        	String name = rs.getString(2);
	        	Double price1 = rs.getDouble(3);
	        	Double price2 = rs.getDouble(4);
	        	int slton = rs.getInt(5);
	        	String nguon = rs.getString(6);
	        	Date date = rs.getDate(7);
	        	System.out.println(id);
	        	ds.themSP(new SanPham(id, name, price1, price2, slton, nguon, date.toLocalDate()));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return ds;
	}
}
