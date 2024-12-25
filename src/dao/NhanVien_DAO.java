package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectDB.ConnectDB;
import entities.NhanVien;
import entities.QuanLyNhanVien;
import entities.QuanLySanPham;
import entities.SanPham;

public class NhanVien_DAO {    
	public static QuanLyNhanVien filter(String sql){
        Connection con;
        QuanLyNhanVien ds = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			System.out.println("Kết nối thành công!");
	        Statement st = con.createStatement();
	        
	        ds = new QuanLyNhanVien();
	        ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	String ma = rs.getString(1);
	        	String name = rs.getString(2);
	        	String role = rs.getString(6);
	        	String email = rs.getString(3);
	        	String sdt = rs.getString(4);
	        	Date date = rs.getDate(5);
	        	ds.themNV(new NhanVien(ma, name, role, date.toLocalDate(), email, sdt));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return ds; 
	}
	public static void addItem(NhanVien x){
		Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectDB.getInstance().getConnection();
			String sql = "INSERT INTO NhanVien (maNV, tenNV, email, sdt, ngaySinh, chucVu) VALUES (?, ?, ?, ?, ?, ?)";

	        pstmt = con.prepareStatement(sql);
	        System.out.println(x.toString());
	        pstmt.setString(1, x.getMaNV()); 
	        pstmt.setString(2, x.getTenNV());
	        pstmt.setString(3, x.getEmail());
	        pstmt.setString(4, x.getSdt());
	        pstmt.setDate(5, Date.valueOf(x.getNgaySinh()));
	        pstmt.setString(6, x.getChucVu());

	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean ckmk(String ma, String mk){
		Connection con;
		try {
			con = ConnectDB.getInstance().getConnection();
			System.out.println("Kết nối thành công!");
	        Statement st = con.createStatement();
	        String sql = "Select matKhau From TaiKhoan Where maNV=N'"+ma+"' and matKhau=N'"+mk+"'";
	        ResultSet rs = st.executeQuery(sql);
	        if (!rs.next()) {
	        	con.close();
	        	return false;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static void update(NhanVien x){
		Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectDB.getInstance().getConnection();
			String sql = "Update NhanVien set tenNV=?, email=?, sdt=?, ngaySinh=?, chucVu=? where maNV=?";

	        pstmt = con.prepareStatement(sql);
	        
	        System.out.println(x.getEmail());
	        pstmt.setString(6, x.getMaNV()); 
	        pstmt.setString(1, x.getTenNV());  
	        pstmt.setString(2, x.getEmail());
	        pstmt.setString(3, x.getSdt());
	        pstmt.setDate(4, Date.valueOf(x.getNgaySinh()));
	        pstmt.setString(5, x.getChucVu());

	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createPassword(String ma, String pass){
		Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectDB.getInstance().getConnection();
			String sql = "Insert into TaiKhoan (maNV, matKhau) VALUES (?, ?)";

	        pstmt = con.prepareStatement(sql);
	         
	        pstmt.setString(1, ma);  
	        pstmt.setString(2, pass);

	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
	}
	
	public static void changePassword(String ma, String pass) {
		Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectDB.getInstance().getConnection();
			String sql = "Update TaiKhoan set matKhau=? where maNV=?";

	        pstmt = con.prepareStatement(sql);    

	        pstmt.setString(2, ma);  
	        pstmt.setString(1, pass);

	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static NhanVien getNV(String id){
		Connection con = null;
		NhanVien nv = null;
        try {
        	con = ConnectDB.getInstance().getConnection();
			 String sql = "{CALL GetNhanVien(?)}";
		        CallableStatement cs = con.prepareCall(sql);
		        cs.setString(1, id);
		        ResultSet rs = cs.executeQuery();
		        
		        if (!rs.next()) {
		        	return nv;
		        }
		    	String ma = rs.getString(1);
		    	String ten = rs.getString(2);
		    	String email = rs.getString(3);
		    	String sdt = rs.getString(4);
		    	Date date = rs.getDate(5);
		    	String chucvu = rs.getString(6);
		    	nv = new NhanVien(ma, ten, chucvu, date.toLocalDate(), email, sdt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return nv;
 
	}
	
	public static NhanVien checkLog(String id, String mk){
		Connection con = null;
		NhanVien nv = null;
        try {
        	con = ConnectDB.getInstance().getConnection();
			String sql = "Select nv.* from NhanVien nv Join TaiKhoan tk On nv.maNV=tk.maNV Where nv.maNV='"+id+"' and tk.matKhau=N'"+mk+"'";
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        
	        
	        if (!rs.next()) {
	        	return nv;
	        }
	       
	    	String ma = rs.getString(1);
	    	String ten = rs.getString(2);
	    	String email = rs.getString(3);
	    	String sdt = rs.getString(4);
	    	Date date = rs.getDate(5);
	    	String chucvu = rs.getString(6);
	    	System.out.println(ma);
	    	nv = new NhanVien(ma, ten, chucvu, date.toLocalDate(), email, sdt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return nv;
	}
	
	public static void removeNV(String id){
		Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectDB.getInstance().getConnection();
			String sql;
	        
	        sql = "DELETE FROM TaiKhoan Where maNV=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.executeUpdate();
	             
	        sql = "UPDATE PhieuNhapHang SET maNV = 'NV00000' WHERE maNV=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.executeUpdate();
	        
	        sql = "DELETE FROM NhanVien Where maNV=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
        
        
	}
	
	public static QuanLyNhanVien loadData() {
		QuanLyNhanVien ds = new QuanLyNhanVien();
        Connection con;
		try {
			con = ConnectDB.getInstance().getConnection();
			System.out.println("Kết nối thành công!");
	        Statement st = con.createStatement();
	        String sql = "select * from NhanVien Order By maNV DESC";
	        ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	String ma = rs.getString(1);
	        	String ten = rs.getString(2);
	        	String email = rs.getString(3);
	        	String sdt = rs.getString(4);
	        	Date date = rs.getDate(5);
	        	String chucvu = rs.getString(6);
	        	System.out.println(ma);
	        	ds.themNV(new NhanVien(ma, ten, chucvu, date.toLocalDate(), email, sdt));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ds;
	}
}
