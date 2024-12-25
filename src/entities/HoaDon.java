package entities;
// có chỉnh sửa thêm contructor
import java.time.LocalDateTime;

public class HoaDon {
	private String maHD;
	private String maNV;
	private LocalDateTime thoiGian;
	private int tongSoLuongSP;
	private double thanhTien;
	private String phuongThucTT;
	
	public HoaDon(int maHD, String maNV, LocalDateTime thoiGian, int tongSoLuongSP, double thanhTien,
			String phuongThucTT) {
		this.maHD = generateNextHoaDon(maHD);
		this.maNV = maNV;
		this.thoiGian = thoiGian;
		this.tongSoLuongSP = tongSoLuongSP;
		this.thanhTien = thanhTien;
		this.phuongThucTT = phuongThucTT;
	}
	
	public HoaDon(String maHD, String maNV, LocalDateTime thoiGian, int tongSoLuongSP, double thanhTien, String phuongThucTT) {
		this.maHD = maHD;
		this.maNV = maNV;
		this.thoiGian = thoiGian;
		this.tongSoLuongSP = tongSoLuongSP;
		this.thanhTien = thanhTien;
		this.phuongThucTT = phuongThucTT;
	}

	public String generateNextHoaDon(int currentNumber) {
        // Chuyển đổi int thành chuỗi với định dạng "HD" theo sau là 6 chữ số, ví dụ "HD000001"
        String maHoaDon = String.format("HD%05d", currentNumber);
        return maHoaDon;
    }
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDateTime getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}
	public int getTongSoLuongSP() {
		return tongSoLuongSP;
	}
	public void setTongSoLuongSP(int tongSoLuongSP) {
		this.tongSoLuongSP = tongSoLuongSP;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public String getPhuongThucTT() {
		return phuongThucTT;
	}
	public void setPhuongThucTT(String phuongThucTT) {
		this.phuongThucTT = phuongThucTT;
	}
	
}
