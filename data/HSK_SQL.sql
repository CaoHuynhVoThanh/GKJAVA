create database QLBH


go
USE [QLBH]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHD] [varchar](50) NOT NULL,
	[maSP] [varchar](50) NOT NULL,
	[soLuongSP] [int] NULL,
	[donGia] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietNhapHang]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietNhapHang](
	[maPhieuNhapHang] [varchar](50) NOT NULL,
	[maSP] [varchar](50) NOT NULL,
	[soLuongSP] [int] NULL,
	[donGia] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhieuNhapHang] ASC,
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [varchar](50) NOT NULL,
	[maNV] [varchar](50) NULL,
	[thoiGian] [datetime] NULL,
	[thanhTien] [decimal](18, 2) NULL,
	[tongSoLuongSP] [int] NULL,
	[phuongThucThanhToan] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [varchar](50) NOT NULL,
	[tenNV] [nvarchar](100) NULL,
	[email] [varchar](100) NULL,
	[sdt] [varchar](10) NULL,
	[ngaySinh] [date] NULL,
	[chucVu] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuNhapHang]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuNhapHang](
	[maPhieuNhapHang] [varchar](50) NOT NULL,
	[maNV] [varchar](50) NULL,
	[thoiGian] [datetime] NULL,
	[tongSoLuong] [int] NULL,
	[thanhTien] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhieuNhapHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSP] [varchar](50) NOT NULL,
	[tenSP] [nvarchar](100) NULL,
	[giaBan] [decimal](18, 2) NULL,
	[giaNhap] [decimal](18, 2) NULL,
	[nhaCungCap] [nvarchar](100) NULL,
	[ngayCapNhat] [date] NULL,
	[soLuongTon] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maNV] [varchar](50) NOT NULL,
	[matKhau] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
GO
ALTER TABLE [dbo].[ChiTietNhapHang]  WITH CHECK ADD FOREIGN KEY([maPhieuNhapHang])
REFERENCES [dbo].[PhieuNhapHang] ([maPhieuNhapHang])
GO
ALTER TABLE [dbo].[ChiTietNhapHang]  WITH CHECK ADD FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[PhieuNhapHang]  WITH CHECK ADD FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
/****** Object:  StoredProcedure [dbo].[GetNhanVien]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetNhanVien]
    @ID varchar(50)
AS
BEGIN
    SELECT nv.*
    from NhanVien nv Join TaiKhoan tk On nv.maNV=tk.maNV
    Where nv.maNV=@ID;
END;
GO
/****** Object:  StoredProcedure [dbo].[GetSLSP]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetSLSP]
    @ID varchar(50),
	@SL int OUTPUT
AS
BEGIN
    select @SL = (COALESCE(b2.soLuongSP, 0)-COALESCE(b1.soLuongSP, 0))
from
       (Select sp.maSP, sp.tenSP, sp.giaNhap, sp.giaBan, SUM(cthd.soLuongSP) as soLuongSP, sp.nhaCungCap, sp.ngayCapNhat
        From SanPham sp Join ChiTietHoaDon cthd
        on sp.maSP=cthd.maSP
        Group By sp.maSP, sp.giaBan, sp.giaNhap, sp.ngayCapNhat, sp.tenSP, sp.nhaCungCap) b1
        Right Join
       (Select sp.maSP, sp.tenSP, sp.giaNhap, sp.giaBan, SUM(ctnh.soLuongSP) as soLuongSP, sp.nhaCungCap, sp.ngayCapNhat
        From SanPham sp Join ChiTietNhapHang ctnh
        on sp.maSP=ctnh.maSP
        Group By sp.maSP, sp.giaBan, sp.giaNhap, sp.ngayCapNhat, sp.tenSP, sp.nhaCungCap) b2
        on b1.maSP=b2.maSP
Where b2.maSP=@ID
END;
GO
/****** Object:  StoredProcedure [dbo].[layTatCaHoaDon]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[layTatCaHoaDon]
AS
BEGIN
    SELECT * FROM HoaDon;
END;
GO
/****** Object:  StoredProcedure [dbo].[layTatCaPhieuNhapHang]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[layTatCaPhieuNhapHang]
AS
BEGIN
    SELECT * FROM PhieuNhapHang;
END;
GO
/****** Object:  StoredProcedure [dbo].[layTop10SPTrongNgay]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[layTop10SPTrongNgay]
    @SaleDate DATE
AS
BEGIN
    SELECT TOP 10
        SP.maSP,
        SP.tenSP,
        SUM(CTHD.soLuongSP) AS totalQuantity
    FROM 
        SanPham SP
    JOIN 
        ChiTietHoaDon CTHD ON SP.maSP = CTHD.maSP
    JOIN 
        HoaDon HD ON CTHD.maHD = HD.maHD
    WHERE 
        CAST(HD.thoiGian AS DATE) = @SaleDate
    GROUP BY 
        SP.maSP, SP.tenSP
    ORDER BY 
        totalQuantity DESC;
END;
GO
/****** Object:  StoredProcedure [dbo].[layTop10SPTrongThang]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[layTop10SPTrongThang]
    @SaleDate DATE
AS
BEGIN
    -- L?y tháng và nam t? ngày truy?n vào
    DECLARE @Month INT = MONTH(@SaleDate);
    DECLARE @Year INT = YEAR(@SaleDate);

    -- Truy v?n l?y top 5 s?n ph?m bán ch?y nh?t trong tháng
    SELECT TOP 10 
        SP.maSP,
        SP.tenSP,
        SUM(CTHD.soLuongSP) AS totalQuantity
    FROM 
        SanPham SP
    JOIN 
        ChiTietHoaDon CTHD ON SP.maSP = CTHD.maSP
    JOIN 
        HoaDon HD ON CTHD.maHD = HD.maHD
    WHERE 
        MONTH(HD.thoiGian) = @Month
        AND YEAR(HD.thoiGian) = @Year
    GROUP BY 
        SP.maSP, SP.tenSP
    ORDER BY 
        totalQuantity DESC;
END;
GO
/****** Object:  StoredProcedure [dbo].[layTop10SPTrongTuan]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[layTop10SPTrongTuan]
@SaleDate DATE
AS
BEGIN
SET DATEFIRST 1;
-- Xác d?nh ngày d?u và ngày cu?i c?a tu?n t? ngày truy?n vào
DECLARE @StartOfWeek DATE = DATEADD(DAY, 1 - DATEPART(WEEKDAY, @SaleDate), @SaleDate);
DECLARE @EndOfWeek DATE = DATEADD(DAY, 7 - DATEPART(WEEKDAY, @SaleDate), @SaleDate);

-- Truy v?n l?y top 5 s?n ph?m bán ch?y nh?t trong tu?n
SELECT TOP 10
    SP.maSP,
    SP.tenSP,
    SUM(CTHD.soLuongSP) AS totalQuantity
FROM 
    SanPham SP
JOIN 
    ChiTietHoaDon CTHD ON SP.maSP = CTHD.maSP
JOIN 
    HoaDon HD ON CTHD.maHD = HD.maHD
WHERE 
    HD.thoiGian >= @StartOfWeek 
    AND HD.thoiGian <= @EndOfWeek
GROUP BY 
    SP.maSP, SP.tenSP
ORDER BY 
    totalQuantity DESC;
END;
GO
/****** Object:  StoredProcedure [dbo].[TimChiTietHoaDon]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TimChiTietHoaDon]
    @maHoaDon VARCHAR(50)
AS
BEGIN
    SELECT * FROM ChiTietHoaDon WHERE maHD = @maHoaDon;
END
GO
/****** Object:  StoredProcedure [dbo].[TimChiTietNhapHang]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TimChiTietNhapHang]
    @maPNH VARCHAR(50)
AS
BEGIN
    SELECT * FROM ChiTietNhapHang WHERE maPhieuNhapHang = @maPNH;
END
GO
/****** Object:  StoredProcedure [dbo].[TimSanPham]    Script Date: 11/11/2024 11:04:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TimSanPham] 
    @maSanPham NVARCHAR(10)
AS
BEGIN
    SELECT * FROM SanPham WHERE maSP = @maSanPham;
END
GO

/** Data **/
INSERT INTO NhanVien (maNV, tenNV, email, sdt, ngaySinh, chucVu)
VALUES
  ('NV00000', N'Admin', 'hihi@gmail.com', '012345678', '2024-01-01', N'Quản lý'),
  ('NV00001', N'Cao Huỳnh Võ Thanh', 'thanhcaovn23@gmail.com', '0987654321', '2004-01-01', N'Quản lý'),
  ('NV00002', N'Lê Vinh Quang', 'quankle@gmail.com', '0987654321', '2004-02-01', N'Nhân viên'),
  ('NV00003', N'Lâm Phát Đạt', 'phatdat@gmail.com', '0987654321', '2004-03-01', N'Nhân viên'),
  ('NV00004', N'Hihi', 'kkk@gmail.com', '0987654321', '2003-01-01', N'Nhân viên'),
  ('NV00005', N'Hihi Haha', 'kkk@gmail.com', '0987654321', '2003-01-01', N'Quản lý');

INSERT INTO TaiKhoan (maNV, matKhau)
VALUES
  ('NV00000', '00000000'),
  ('NV00001', 'caohuynhvothanh'),
  ('NV00002', 'levinhquang'),
  ('NV00003', 'lamphatdat'),
  ('NV00004', '0987654321'),
  ('NV00005', '0987654321');

INSERT INTO [dbo].[SanPham] (maSP, tenSP, giaBan, giaNhap, nhaCungCap, ngayCapNhat, soLuongTon)
VALUES
  ('SP00001', N'Coca Cola', 104000.00, 80000.00, N'CoaCola', '2023-09-24', 113),
  ('SP00002', N'Pepsi', 117000.00, 90000.00, N'Pepsico', '2023-11-20', 152),
  ('SP00003', N'Bánh su kem', 195000.00, 150000.00, N'Tiệm bánh Gia Phúc', '2020-01-06', 64),
  ('SP00004', N'Bánh tráng muối', 156000.00, 120000.00, N'Lò bánh tráng Hoàng Anh', '2019-12-27', 45),
  ('SP00005', N'Kem Yumi', 117000.00, 90000.00, N'Kem Pumo', '2018-07-04', 43),
  ('SP00006', N'Redbull', 130000.00, 100000.00, N'CTY Y', '2019-05-11', 105),
  ('SP00007', N'Aquafina', 91000.00, 70000.00, N'CTY Y', '2018-07-04', 170),
  ('SP00008', N'C2 vị chanh', 117000.00, 90000.00, N'CTY X', '2018-07-04', 74),
  ('SP00009', N'C2 vị dâu', 117000.00, 90000.00, N'CTY Y', '2018-07-04', 74),
  ('SP00010', N'C2 vị vải', 117000.00, 90000.00, N'CTY X', '2018-07-04', 73),
  ('SP00011', N'Bánh Oshi tắm cay', 559000.00, 43000.00, N'CTY Z', '2018-07-04', 52),
  ('SP00012', N'Bánh Oshi mực Philipin', 494000.00, 38000.00, N'CTY Z', '2018-07-04', 66),
  ('SP00013', N'Bánh Oshi tắm cay', 559000.00, 43000.00, N'CTY Z', '2018-07-04', 50),
  ('SP00014', N'Bông ngô phô mai', 845000.00, 65000.00, N'CTY Z', '2018-07-04', 36),
  ('SP00015', N'Bánh phồng tôm', 559000.00, 43000.00, N'CTY Z', '2018-07-04', 49),
  ('SP00016', N'VB 4 6 3', 975000.00, 75000.00, N'CTY A', '2018-07-04', 99),
  ('SP00017', N'Bút bi', 455000.00, 35000.00, N'CTY A', '2018-07-04', 124),
  ('SP00018', N'Bút chì', 975000.00, 75000.00, N'CTY A', '2018-07-04', 114),
  ('SP00019', N'Tẩy', 676000.00, 52000.00, N'CTY A', '2018-07-04', 61),
  ('SP00020', N'Hộp khăn ướt 20 tờ', 195000.00, 150000.00, N'CTY B', '2018-07-04', 53),
  ('SP00021', N'Khẩu trang 15 cái', 39000.00, 30000.00, N'CTY B', '2018-07-04', 72),
  ('SP00022', N'Dầu gội Sunsill', 845000.00, 65000.00, N'CTY C', '2018-07-04', 48),
  ('SP00023', N'Sữa tắm Lifebouy', 975000.00, 75000.00, N'CTY C', '2018-07-04', 15),
  ('SP00024', N'Sữa rửa mặt', 559000.00, 43000.00, N'CTY C', '2018-07-04', 72),
  ('SP00025', N'Nước tẩy trang', 169000.00, 13000.00, N'CTY C', '2018-07-04', 74),
  ('SP00026', N'Bánh mỳ chả bông, c...', 169000.00, 13000.00, N'CTY D', '2024-07-04', 74),
  ('SP00027', N'Cơm dưỡng châu', 455000.00, 35000.00, N'CTY D', '2024-07-04', 49),
  ('SP00028', N'Mỹ', 39000.00, 30000.00, 'CTY D', '2024-07-04', 50),
  ('SP00029', N'Bánh bao trứng cút', 26000.00, 20000.00, N'CTY D', '2024-07-04', 45),
  ('SP00030', N'Cơm cuộn', 195000.00, 150000.00, N'CTY D', '2024-07-04', 0);

INSERT INTO [dbo].[PhieuNhapHang] (maPhieuNhapHang, maNV, thoiGian, tongSoLuong, thanhTien)
VALUES
  ('PN00001', 'NV00000', '2024-11-09 00:00:00', 451, 60783000.00),
  ('PN00002', 'NV00001', '2024-11-09 00:00:00', 1804, 243132000.00);


INSERT INTO [dbo].[ChiTietNhapHang] (maPhieuNhapHang, maSP, soLuongSP, donGia)
VALUES
  ('PN00001', 'SP00001', 23, 8000.00),
  ('PN00001', 'SP00002', 31, 9000.00),
  ('PN00001', 'SP00003', 13, 15000.00),
  ('PN00001', 'SP00004', 9, 12000.00),
  ('PN00001', 'SP00005', 9, 9000.00),
  ('PN00001', 'SP00006', 21, 10000.00),
  ('PN00001', 'SP00007', 34, 7000.00),
  ('PN00001', 'SP00008', 15, 9000.00),
  ('PN00001', 'SP00009', 15, 9000.00),
  ('PN00001', 'SP00010', 15, 9000.00),
  ('PN00001', 'SP00011', 11, 4300.00),
  ('PN00001', 'SP00012', 14, 3800.00),
  ('PN00001', 'SP00013', 11, 4300.00),
  ('PN00001', 'SP00014', 8, 6500.00),
  ('PN00001', 'SP00015', 13, 4300.00),
  ('PN00001', 'SP00016', 20, 7500.00),
  ('PN00001', 'SP00017', 25, 3500.00),
  ('PN00001', 'SP00018', 23, 7500.00),
  ('PN00001', 'SP00019', 13, 5200.00),
  ('PN00001', 'SP00020', 11, 15000.00),
  ('PN00001', 'SP00021', 15, 30000.00),
  ('PN00001', 'SP00022', 10, 6500.00),
  ('PN00001', 'SP00023', 16, 7500.00),
  ('PN00001', 'SP00024', 15, 4300.00),
  ('PN00001', 'SP00025', 15, 1300.00),
  ('PN00001', 'SP00026', 15, 1300.00),
  ('PN00001', 'SP00027', 10, 3500.00),
  ('PN00001', 'SP00028', 10, 30000.00),
  ('PN00001', 'SP00029', 10, 20000.00),
  ('PN00001', 'SP00030', 13, 15000.00),
  ('PN00002', 'SP00001', 92, 8000.00),
  ('PN00002', 'SP00002', 124, 9000.00),
  ('PN00002', 'SP00003', 52, 15000.00),
  ('PN00002', 'SP00004', 36, 12000.00),
  ('PN00002', 'SP00005', 36, 9000.00),
  ('PN00002', 'SP00006', 84, 10000.00),
  ('PN00002', 'SP00007', 136, 7000.00),
  ('PN00002', 'SP00008', 60, 9000.00),
  ('PN00002', 'SP00009', 60, 9000.00),
  ('PN00002', 'SP00010', 60, 9000.00),
  ('PN00002', 'SP00011', 44, 4300.00),
  ('PN00002', 'SP00012', 56, 3800.00),
  ('PN00002', 'SP00013', 44, 4300.00),
  ('PN00002', 'SP00014', 32, 6500.00),
  ('PN00002', 'SP00015', 52, 4300.00),
  ('PN00002', 'SP00016', 80, 7500.00),
  ('PN00002', 'SP00017', 100, 3500.00),
  ('PN00002', 'SP00018', 92, 7500.00),
  ('PN00002', 'SP00019', 52, 5200.00),
  ('PN00002', 'SP00020', 44, 15000.00),
  ('PN00002', 'SP00021', 60, 30000.00),
  ('PN00002', 'SP00022', 40, 6500.00),
  ('PN00002', 'SP00023', 16, 7500.00),
  ('PN00002', 'SP00024', 60, 4300.00),
  ('PN00002', 'SP00025', 60, 1300.00),
  ('PN00002', 'SP00026', 60, 1300.00),
  ('PN00002', 'SP00027', 40, 3500.00),
  ('PN00002', 'SP00028', 40, 3000.00),
  ('PN00002', 'SP00029', 40, 2000.00),
  ('PN00002', 'SP00030', 52, 15000.00);


INSERT INTO [dbo].[HoaDon] (maHD, maNV, thoiGian, thanhTien, tongSoLuongSP, phuongThucThanhToan)
VALUES
  ('HD00001', 'NV00002', '2024-11-04 08:57:24.057', 37400.00, 6, N'Tiền mặt'),
  ('HD00002', 'NV00000', '2024-11-04 13:57:55.110', 1903000.00, 8, N'Tiền mặt'),
  ('HD00003', 'NV00001', '2024-11-04 20:58:54.737', 95000.00, 2, N'Tiền mặt'),
  ('HD00004', 'NV00000', '2024-11-05 09:59:09.093', 12700.00, 2, N'Ví điện tử'),
  ('HD00005', 'NV00003', '2024-11-05 13:59:09.093', 11000.00, 2, N'Thẻ ngân hàng'),
  ('HD00006', 'NV00000', '2024-11-05 22:59:35.857', 25800.00, 6, N'Tiền mặt'),
  ('HD00007', 'NV00002', '2024-11-06 10:59:55.117', 28000.00, 6, N'Tiền mặt'),
  ('HD00008', 'NV00002', '2024-11-06 14:59:55.117', 26100.00, 4, N'Ví điện tử'),
  ('HD00009', 'NV00002', '2024-11-06 18:59:55.117', 18000.00, 2, N'Tiền mặt'),
  ('HD00010', 'NV00001', '2024-11-06 10:59:55.117', 85400.00, 3, N'Tiền mặt'),
  ('HD00011', 'NV00003', '2024-11-06 14:59:55.117', 15000.00, 2, N'Tiền mặt'),
  ('HD00012', 'NV00003', '2024-11-06 18:59:55.117', 45000.00, 2, N'Ví điện tử'),
  ('HD00013', 'NV00002', '2024-11-07 10:59:55.117', 10800.00, 2, N'Tiền mặt'),
  ('HD00014', 'NV00002', '2024-11-07 13:59:55.117', 30100.00, 7, N'Ví điện tử'),
  ('HD00015', 'NV00001', '2024-11-07 18:59:55.117', 17500.00, 3, N'Thẻ ngân hàng'),
  ('HD00016', 'NV00002', '2024-11-08 10:59:55.117', 24000.00, 2, N'Ví điện tử'),
  ('HD00017', 'NV00000', '2024-11-08 13:59:55.117', 131000.00, 3, N'Ví điện tử'),
  ('HD00018', 'NV00001', '2024-11-08 21:50:16.213', 63000.00, 3, N'Ví điện tử'),
  ('HD00019', 'NV00000', '2024-11-09 09:50:40.377', 95000.00, 2, N'Ví điện tử'),
  ('HD00020', 'NV00003', '2024-11-09 15:51:08.800', 85800.00, 3, N'Ví điện tử'),
  ('HD00021', 'NV00002', '2024-11-09 19:51:28.610', 25800.00, 3, N'Ví điện tử'),
  ('HD00022', 'NV00000', '2024-11-11 10:47:27.123', 13065000.00, 67, N'Ví điện tử'),
  ('HD00023', 'NV00001', '2024-11-11 22:00:37.153', 23400.00, 2, N'Ví điện tử');

INSERT INTO [dbo].[ChiTietHoaDon] (maHD, maSP, soLuongSP, donGia)
VALUES
  ('HD00001', 'SP00001', 1, 8000.00),
  ('HD00001', 'SP00005', 2, 9000.00),
  ('HD00002', 'SP00012', 1, 4300.00),
  ('HD00002', 'SP00011', 1, 4300.00),
  ('HD00002', 'SP00024', 2, 4300.00),
  ('HD00002', 'SP00020', 5, 20000.00),
  ('HD00003', 'SP00021', 1, 30000.00),
  ('HD00003', 'SP00022', 1, 65000.00),
  ('HD00004', 'SP00018', 1, 7500.00),
  ('HD00004', 'SP00016', 1, 5200.00),
  ('HD00005', 'SP00016', 1, 7500.00),
  ('HD00005', 'SP00017', 1, 3500.00),
  ('HD00006', 'SP00015', 6, 4300.00),
  ('HD00007', 'SP00013', 5, 4300.00),
  ('HD00007', 'SP00014', 1, 6500.00),
  ('HD00008', 'SP00010', 2, 9000.00),
  ('HD00008', 'SP00011', 1, 4300.00),
  ('HD00008', 'SP00012', 1, 3800.00),
  ('HD00009', 'SP00008', 1, 9000.00),
  ('HD00009', 'SP00016', 1, 9000.00),
  ('HD00010', 'SP00019', 2, 5200.00),
  ('HD00010', 'SP00023', 1, 7500.00),
  ('HD00011', 'SP00023', 2, 7500.00),
  ('HD00012', 'SP00020', 1, 15000.00),
  ('HD00012', 'SP00021', 1, 30000.00),
  ('HD00013', 'SP00014', 1, 6500.00),
  ('HD00013', 'SP00015', 1, 4300.00),
  ('HD00014', 'SP00015', 7, 4300.00),
  ('HD00015', 'SP00001', 1, 8000.00),
  ('HD00015', 'SP00011', 1, 4300.00),
  ('HD00015', 'SP00016', 1, 5200.00),
  ('HD00016', 'SP00002', 1, 9000.00),
  ('HD00016', 'SP00003', 1, 15000.00),
  ('HD00017', 'SP00023', 1, 7500.00),
  ('HD00017', 'SP00024', 1, 4300.00),
  ('HD00017', 'SP00025', 1, 13000.00),
  ('HD00018', 'SP00026', 1, 13000.00),
  ('HD00018', 'SP00027', 1, 35000.00),
  ('HD00018', 'SP00030', 1, 15000.00),
  ('HD00019', 'SP00021', 1, 30000.00),
  ('HD00019', 'SP00022', 1, 65000.00),
  ('HD00020', 'SP00014', 1, 6500.00),
  ('HD00020', 'SP00015', 1, 4300.00),
  ('HD00020', 'SP00023', 1, 7500.00),
  ('HD00021', 'SP00014', 1, 6500.00),
  ('HD00021', 'SP00015', 1, 4300.00),
  ('HD00021', 'SP00020', 1, 15000.00),
  ('HD00022', 'SP00030', 67, 19500.00),
  ('HD00023', 'SP00002', 2, 11700.00);

--delete from [dbo].[ChiTietHoaDon]
--delete from [dbo].[ChiTietNhapHang]
--delete from [dbo].[TaiKhoan]
--delete from [dbo].[HoaDon]
--delete from [dbo].[PhieuNhapHang]
--delete from [dbo].[NhanVien]
--delete from [dbo].[SanPham]