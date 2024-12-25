package entities;

import java.time.LocalDate;

public class NhanVien {
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", email=" + email + ", chucVu=" + chucVu + ", sdt="
				+ sdt + ", ngaySinh=" + ngaySinh + "]";
	}

	private String maNV, tenNV, email, chucVu, sdt;
	private LocalDate ngaySinh;

	public NhanVien(String maNV, String tenNV, String chucVu,  LocalDate ngaySinh, String email, String sdt) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.chucVu = chucVu;
		this.sdt = sdt;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	public Object[] toObj() {
		return new Object[] {this.maNV, this.tenNV, this.chucVu, this.ngaySinh, this.email, this.sdt};
	}
}
