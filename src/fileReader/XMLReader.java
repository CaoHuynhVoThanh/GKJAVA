package fileReader;

import org.w3c.dom.*;

import entities.QuanLySanPham;
import entities.SanPham;

import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    public static QuanLySanPham ReadXML(String filePath) {
        QuanLySanPham ds = new QuanLySanPham();

        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("SanPham");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String id = eElement.getAttribute("id");
                    String tenSanPham = eElement.getElementsByTagName("tenSanPham").item(0).getTextContent();
                    double giaNhap = Double.parseDouble(eElement.getElementsByTagName("giaNhap").item(0).getTextContent());
                    int soLuong = Integer.parseInt(eElement.getElementsByTagName("soLuong").item(0).getTextContent());
                    String nguonHang = eElement.getElementsByTagName("nguonHang").item(0).getTextContent();
                    LocalDate date = LocalDate.parse(eElement.getElementsByTagName("thoiGian").item(0).getTextContent());
                 
                    SanPham sanPham = new SanPham(id, tenSanPham, giaNhap, giaNhap+giaNhap*SanPham.profit, soLuong, nguonHang, date);
                    ds.themSP(sanPham);
                }
            }

        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Đường dẫn không hợp lệ");
        }
        return ds;
    }
}

