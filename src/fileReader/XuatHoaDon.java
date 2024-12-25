package fileReader;
// có chỉnh sửa thêm thông tin
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.NhanVien;
import entities.SanPham;

public class XuatHoaDon {
	private static final String FILENAME = "src/file/dulieu.txt";
	public static void writeInvoiceToFile(ArrayList<ChiTietHoaDon> dsChiTietHoaDon, HoaDon hd) throws IOException, SQLException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
        	NhanVien nv = NhanVien_DAO.getNV(hd.getMaNV());
            // Tiêu đề hóa đơn
        	writer.write("=============3 ELEVEN MARKET ============\n");
        	writer.write("Số 12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Hồ Chí Minh\n");
            writer.write("============ HÓA ĐƠN BÁN HÀNG ============\n");
            writer.write(String.format("Thời gian: %s\n", hd.getThoiGian().format(formatter)));
            writer.write(String.format("Nhân viên thực hiện: %s\n", nv.getTenNV()));
            writer.write("===========================\n");
            writer.write(String.format("%-10s %-20s %-10s %-10s %-10s\n", "Mã SP", "Tên SP", "Số lượng", "Giá", "Thành tiền"));
            
            // Ghi thông tin sản phẩm
            double tongTien = 0;
            int soLuong = 0;
            for (ChiTietHoaDon chiTietHD : dsChiTietHoaDon) {
            	System.out.println(chiTietHD.getMaHD() + chiTietHD.getMaSP());
            	SanPham sp = SanPham_DAO.timSanPham(chiTietHD.getMaSP());
                double thanhTien = chiTietHD.getSoLuongSP() * chiTietHD.getDonGia();
                tongTien += thanhTien;
                soLuong += chiTietHD.getSoLuongSP();
                writer.write(String.format("%-10s %-20s %-10d %-10.2f %-10.2f\n",
                        sp.getMaSP(), sp.getTenSP(), chiTietHD.getSoLuongSP(), sp.getGiaBan(), thanhTien));
            }
            writer.write("===========================\n");
            writer.write(String.format("Tổng số sản phẩm: %d\n", soLuong));
            writer.write(String.format("Tổng tiền: %.2f\n", tongTien));
            writer.write("===========================\n");
        }
    }
}
