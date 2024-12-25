package entities;

public class ChiTietNhapHang {
	private String maPNH;
	private String maSP;
	private int soLuongSP;
	private double donGia;
	public ChiTietNhapHang(String maPNH, String maSP, int soLuongSP, double donGia) {
		this.maPNH = maPNH;
		this.maSP = maSP;
		this.soLuongSP = soLuongSP;
		this.donGia = donGia;
	}
	public String getMaPNH() {
		return maPNH;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
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
