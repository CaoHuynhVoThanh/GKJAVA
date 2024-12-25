package entities;

import java.util.ArrayList;

public class QuanLySanPham {
	private ArrayList<SanPham> ds;
	
	public QuanLySanPham() {
		super();
		this.ds = new ArrayList<>();
	}
	
	public QuanLySanPham(ArrayList<SanPham> ds) {
		super();
		this.ds = ds;
	}

	public ArrayList<SanPham> getDs() {
		return ds;
	}

	public void setDs(ArrayList<SanPham> ds) {
		this.ds = ds;
	}
	
	public int timSP(String ma) {
		for (SanPham x: ds) {
			if (x.getMaSP().equals(ma)) {
				return ds.indexOf(x);
			}
		}
		return -1;
	}
	
	public int getTongSo() {
		int s=0;
		for (SanPham x: ds) {
			s+=x.getSoLuongTon();
		}
		return s;
	}
	
	public void suaSL(SanPham x) {
		int idx = timSP(x.getMaSP());
		SanPham sp = ds.get(idx);
		sp.setSoLuongTon(sp.getSoLuongTon()+x.getSoLuongTon());
		ds.set(idx, sp);
	}
	
	public double tinhTongTien() {
		double tt = 0;
		for (SanPham x: ds) {
			tt+=x.getGiaNhap()*x.getSoLuongTon();
		}
		return tt;
	}
	
	public boolean has(SanPham x) {
		for (SanPham k: ds) {
			if (x.same(k)) return true;
		}
		return false;
	}
	
	public void xoaSP(String ma) {
		int idx = timSP(ma);
		System.out.println(ds.size());
		System.out.println(idx);
		ds.remove(idx);
	}
	
	
	public void themSP(SanPham x) {
		this.ds.add(0, x);
	}
	
}
