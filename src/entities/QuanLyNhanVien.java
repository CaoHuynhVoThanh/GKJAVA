package entities;

import java.util.ArrayList;

public class QuanLyNhanVien {
	ArrayList<NhanVien> ds;

	public QuanLyNhanVien(ArrayList<NhanVien> ds) {
		super();
		this.ds = ds;
	}
	

	public QuanLyNhanVien() {
		ds = new ArrayList<>();
	}


	public ArrayList<NhanVien> getDS(){
		return this.ds;
	}
	
	public ArrayList<NhanVien> getDs() {
		return ds;
	}

	public void setDs(ArrayList<NhanVien> ds) {
		this.ds = ds;
	}
	
	public int timNV(String ma) {
		for (NhanVien x: ds) {
			if (x.getMaNV().equals(ma)) {
				return ds.indexOf(x);
			}
		}
		return -1;
	}
	
	public void xoaNV(String ma) {
		int idx = timNV(ma);
		ds.remove(idx);
	}
	
	
	public void themNV(NhanVien x) {
		this.ds.add(0, x);
	}
	
}
