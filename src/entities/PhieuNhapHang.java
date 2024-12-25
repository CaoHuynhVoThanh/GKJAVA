package entities;
//có chỉnh sửa thêm constructor
import java.time.LocalDateTime;

public class PhieuNhapHang {
	private String maPNH;
	private String maNV;
	private String tenNV;
	private LocalDateTime thoiGian;
	private int tongSoLuongSP;
	private double thanhTien;
	public PhieuNhapHang(int maPNH, String maNV, String tenNV, LocalDateTime thoiGian, int tongSoLuongSP, double thanhTien) {
		this.maPNH = generateNextPNH(maPNH);
		this.maNV = maNV;
		this.tenNV = maNV;
		this.thoiGian = thoiGian;
		this.tongSoLuongSP = tongSoLuongSP;
		this.thanhTien = thanhTien;
	}
	
	public PhieuNhapHang(String maPNH, String maNV, String tenNV, LocalDateTime thoiGian, int tongSoLuongSP,
			double thanhTien) {
		this.maPNH = maPNH;
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.thoiGian = thoiGian;
		this.tongSoLuongSP = tongSoLuongSP;
		this.thanhTien = thanhTien;
	}

	public String getMaPNH() {
		return maPNH;
	}
	public String getMaNV() {
		return maNV;
	}
	public LocalDateTime getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}
	public int getTongSoLuongPNH() {
		return tongSoLuongSP;
	}
	public void setTongSoLuongPNH(int tongSoLuongSP) {
		this.tongSoLuongSP = tongSoLuongSP;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public int getTongSoLuongSP() {
		return tongSoLuongSP;
	}
	public void setTongSoLuongSP(int tongSoLuongSP) {
		this.tongSoLuongSP = tongSoLuongSP;
	}
	public String generateNextPNH(int currentNumber) {
        // Chuyển đổi int thành chuỗi với định dạng "HD" theo sau là 6 chữ số, ví dụ "HD000001"
        String maPNH = String.format("PN%05d", currentNumber);
        return maPNH;
    }
}
