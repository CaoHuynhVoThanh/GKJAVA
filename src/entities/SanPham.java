package entities;

import java.time.LocalDate;

public class SanPham {
	private String maSP, tenSP, nhaCC;
	private int soLuongTon;
	private Double giaNhap, giaBan;
	private LocalDate getthoiGianCapNhat;
	public static Double profit = 0.3;
	
	public SanPham(String maSP, String tenSP, Double giaNhap, Double giaBan, int soLuongTon, String nhaCC, LocalDate getthoiGianCapNhat) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuongTon = soLuongTon;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.nhaCC = nhaCC;
		this.getthoiGianCapNhat = getthoiGianCapNhat;
	}
	
	public Double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(Double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public LocalDate getThoiGian() {
		return getthoiGianCapNhat;
	}

	public void setThoiGian(LocalDate thoiGian) {
		this.getthoiGianCapNhat = thoiGian;
	}


	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getNhaCC() {
		return nhaCC;
	}

	public void setNhaCC(String nhaCC) {
		this.nhaCC = nhaCC;
	}

	public String getTinhTrang() {
		if (this.soLuongTon>5) return "Còn hàng";
		else if (this.soLuongTon<=5 && this.soLuongTon>=3) return "Thiếu hàng"; 
		return "Hết hàng";
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public Double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	
	public void updateGiaBan() {
		this.giaBan=this.giaNhap+this.giaNhap*profit;
	}
	
	
	public Object[] toObj() {
		return new Object[] {this.maSP, this.tenSP, this.giaNhap, this.giaBan, this.soLuongTon, this.getTinhTrang(), this.nhaCC, this.getthoiGianCapNhat};
	}
	
	public Object[] toObj2() {
		return new Object[] {this.maSP, this.tenSP, this.giaNhap, this.giaBan, this.soLuongTon, this.nhaCC};
	}
	
	public boolean same(SanPham y) {
		return (this.getMaSP().equals(y.getMaSP())) 
				&& (this.getTenSP().equals(y.getTenSP()))
				&& (Math.abs(this.getGiaNhap()-y.getGiaNhap())<0.001)
				&& (this.getNhaCC().equals(y.getNhaCC()));
	}
	
	
	
}
