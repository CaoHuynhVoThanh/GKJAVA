package entities;

public class ChiTietHoaDon {
	private String maHD;
	private String maSP;
	private int soLuongSP;
	private double donGia;
	public ChiTietHoaDon(String maHD, String maSP, int soLuongSP, double donGia) {
		this.maSP = maSP;
		this.maHD = maHD;
		this.soLuongSP = soLuongSP;
		this.donGia = donGia;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maNV) {
		this.maHD = maNV;
	}
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
}
