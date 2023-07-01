/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_LoaiSanPham;
import dao.Dao_NhaCungCap;
import dao.Dao_NhaXuatBan;
import dao.Dao_NhanVien;
import dao.Dao_SanPham;
import dao.Dao_TacGia;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;
import until.Ngay;
import until.Pair;

/**
 *
 * @author DAVID
 */
public class GUI_NVThongKeSanPham extends javax.swing.JPanel {
	private DefaultTableModel modelTK;
	 private Dao_ChiTietHoaDon dao_ChiTietHoaDon = new Dao_ChiTietHoaDon();
	private static Dao_SanPham dao_SanPham= new Dao_SanPham();
	private static Dao_LoaiSanPham dao_LoaiSanPham = new Dao_LoaiSanPham();
	private static Dao_TacGia dao_TacGia = new Dao_TacGia();
	private static Dao_NhaCungCap dao_NhaCungCap = new Dao_NhaCungCap();
	private static Dao_NhaXuatBan dao_NhaXuatBan = new Dao_NhaXuatBan();
	private static Dao_NhanVien dao_NhanVien = new Dao_NhanVien();
	private Map<SanPham, Integer> list;
	private int soLuongDaBan;
	private DecimalFormat df = new DecimalFormat("#,#00 VND");
	private SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
	private Date tuNgay;
	private Date toiNgay;
    /**
     * Creates new form GUI_ThongKeDoanhTHu
     */
    public GUI_NVThongKeSanPham() throws Exception {
        initComponents();
       
        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser2.setDateFormatString("dd/MM/yyyy");
        lbThang.setVisible(false);
		jMonthChooser1.setVisible(false);
		lbNam.setVisible(false);
		jYearChooser1.setVisible(false);
		
		btnLamMoi.addActionListener((e) -> {
			cbLoaiTK.setSelectedIndex(0);
			xoaBang();
			jDateChooser1.setDate(null);
        	jDateChooser2.setDate(null);
        	txtTongSP.setText("");
		});
		
		btnThongKe.addActionListener((e)->{
			long ml = System.currentTimeMillis();
			Date now = new Date(ml);
			
			SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy"); // hom nay
			 // lấy ra giá trị ngày dưới dạng java.sql.Date
        	java.util.Date ngayBD= new java.util.Date();
        	java.util.Date ngayKT= new java.util.Date();
        	
        	
        	tuNgay=new Date(ml); // hom nay
        	toiNgay=new Date(ml);
        	
        	if(cbLoaiTK.getSelectedIndex() == 0) {
        		
        		ngayBD =  jDateChooser1.getDate();
        		ngayKT = jDateChooser2.getDate();
        		
        		tuNgay = new Date(ngayBD.getTime());
        		toiNgay = new Date(ngayKT.getTime());
        		
        		if(tuNgay.after(now)) {
	        		JOptionPane.showMessageDialog(this, "Từ ngày không hợp lệ");
	                return;
	        	}
	        	if(toiNgay.after(now)) {
	        		JOptionPane.showMessageDialog(this, "Tới ngày không hợp lệ");
	                return;
	        	}
	        	if(tuNgay.after(toiNgay)) {
	        		JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
	                return;
	        	}
        	}else if(cbLoaiTK.getSelectedIndex() == 2) { // hom qua
        		LocalDate today = LocalDate.now();
        		LocalDate yesterday = today.minusDays(1);
        		
              	tuNgay = Date.valueOf(yesterday);
              	toiNgay = Date.valueOf(yesterday);
        	}else if(cbLoaiTK.getSelectedIndex() == 3) { // Theo thang
        		int thang = jMonthChooser1.getMonth()+1;
        		int nam = jYearChooser1.getYear();
        		 Pair<Date, Date> range = Ngay.getRangeMonth(thang, nam);
        		 tuNgay = range.getElement0();
        		 toiNgay = range.getElement1();
        	}else if(cbLoaiTK.getSelectedIndex() == 4) { // theo nam
        		int nam = jYearChooser1.getYear();
        		Pair<Date, Date> range = Ngay.getRangeYear(nam);
       		 	tuNgay = range.getElement0();
       		 	toiNgay = range.getElement1();
        	}
        	
        	try {
				renderData(tuNgay, toiNgay);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
        cbLoaiTK.addActionListener((e)->{
        	if(cbLoaiTK.getSelectedIndex()==0) {
        		lbNgayBD.setVisible(true);
        		jDateChooser1.setVisible(true);
        		lbNgayKt.setVisible(true);
        		jDateChooser2.setVisible(true);
        		lbThang.setVisible(false);
        		jMonthChooser1.setVisible(false);
        		lbNam.setVisible(false);
        		jYearChooser1.setVisible(false);
        	}else if(cbLoaiTK.getSelectedIndex()==1 || cbLoaiTK.getSelectedIndex()==2) {
        		lbNgayBD.setVisible(false);
        		jDateChooser1.setVisible(false);
        		lbNgayKt.setVisible(false);
        		jDateChooser2.setVisible(false);
        		lbThang.setVisible(false);
        		jMonthChooser1.setVisible(false);
        		lbNam.setVisible(false);
        		jYearChooser1.setVisible(false);
        	}else if(cbLoaiTK.getSelectedIndex()==3) {
        		lbNgayBD.setVisible(false);
        		jDateChooser1.setVisible(false);
        		lbNgayKt.setVisible(false);
        		jDateChooser2.setVisible(false);
        		lbThang.setVisible(true);
        		jMonthChooser1.setVisible(true);
        		lbNam.setVisible(true);
        		jYearChooser1.setVisible(true);
        	}else if(cbLoaiTK.getSelectedIndex()==4) {
        		lbNgayBD.setVisible(false);
        		jDateChooser1.setVisible(false);
        		lbNgayKt.setVisible(false);
        		jDateChooser2.setVisible(false);
        		lbThang.setVisible(false);
        		jMonthChooser1.setVisible(false);
        		lbNam.setVisible(true);
        		jYearChooser1.setVisible(true);
        	}
        });
        btnInBC.addActionListener((e)->{
        	DecimalFormat df = new DecimalFormat("#,#00 VND");
			String path = "";
        	JFileChooser j = new JFileChooser(System.getProperty("user.dir") + "\\hoadon\\");
        	j.setDialogTitle("Print file");
        	int x = j.showSaveDialog(this);
        	if(x==JFileChooser.APPROVE_OPTION) {
        		path = j.getSelectedFile().getPath();
        	}
        	
        	com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED, 10); // 10 is the size
        	com.itextpdf.text.Font textFont12 = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED, 12); // 12 is the size
        	com.itextpdf.text.Font textFont20 = FontFactory.getFont("font/SVN-Arial.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20); // 20 is the size
			com.itextpdf.text.Font textFont24 = FontFactory.getFont("font/SVN-Arial.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 24); // 24 is the size
			
        	Document doc = new Document();
        	try {
				PdfWriter.getInstance(doc, new FileOutputStream(path+".pdf"));
				doc.open();
				Paragraph Name = new Paragraph("Nhà Sách Thiên Văn\n", textFont20);
				Name.setAlignment(Element.ALIGN_LEFT);
				doc.add(Name);
				Paragraph tt = new Paragraph("--------------------------------------------");
				tt.setAlignment(Element.ALIGN_LEFT);
				doc.add(tt);
				
				DateFormat dftt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				java.util.Date date = new java.util.Date();
				Paragraph time = new Paragraph(dftt.format(date).toString(),textFont);
				time.setAlignment(Element.ALIGN_RIGHT);
				doc.add(time);
				
				Paragraph DiaChi = new Paragraph("Địa chỉ: 12 Nguyễn Văn Bảo, Quận Gò Vấp, Thành phố Hồ Chí Minh\n",textFont);
				DiaChi.setAlignment(Element.ALIGN_RIGHT);
				doc.add(DiaChi);
				Paragraph SDT = new Paragraph("Số điện thoại: 0338556837\n",textFont);
				SDT.setAlignment(Element.ALIGN_RIGHT);
				doc.add(SDT);
				
				Paragraph tieuDe = new Paragraph("Thống Kê Sản Phẩm\n",textFont24);
				tieuDe.setAlignment(Element.ALIGN_CENTER);
				doc.add(tieuDe);
				Paragraph starLine = new Paragraph(
						"=========================================================================================",
						textFont);
				doc.add(starLine);
				
				// Thông tin ngày báo cáo
				Paragraph paragrapTuNgay = new Paragraph("Từ ngày: " + dft.format(tuNgay) +"- đến ngày: "+ dft.format(toiNgay),textFont);
				paragrapTuNgay.setAlignment(Element.ALIGN_CENTER);
				doc.add(paragrapTuNgay);
				doc.add(starLine); 
				
				// Thông tin nhân viên báo cáo
				Paragraph prTenNV = new Paragraph("Tên nhân viên thống kê: "+ dao_NhanVien.getNhanVienTheoMa(GUI_DangNhap.getTxtTaiKhoan().getText()).getHoTen(),textFont);
				Paragraph 	prSDT =	new Paragraph("Số điện thoại: "+ dao_NhanVien.getNhanVienTheoMa(GUI_DangNhap.getTxtTaiKhoan().getText()).getSdt(),textFont);
				doc.add(prTenNV); 
				doc.add(prSDT);
				
				int page = doc.getPageNumber()+1;
				Paragraph prTrang = new Paragraph("Trang số: " +page,textFont);
				prTrang.setAlignment(Element.ALIGN_RIGHT);
				if (page > 1) {
					doc.add(prTrang);
				}
				doc.add(new Paragraph("\n"));
				
				PdfPTable tbl = new PdfPTable(new float[] {2.0f,3.0f,5.0f,5.0f,5.0f,4.0f,4.0f});
				tbl.setWidthPercentage(100.0f);
				// adding header
				PdfPCell c1 = new PdfPCell(new Phrase(" STT", textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	Mã sản phẩm",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(" 	Tên sản phẩm",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(" 	Tên loại",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("  Đơn giá",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	 Số lượng tồn",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	 Số lượng đã bán",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				for (int i = 0; i < tableBanChay.getRowCount(); i++) {
					String maSP = tableBanChay.getValueAt(i, 0).toString();
					String tenSP = tableBanChay.getValueAt(i, 1).toString();
					String loai = tableBanChay.getValueAt(i, 2).toString();
					String gia = tableBanChay.getValueAt(i, 3).toString();
					String ton = tableBanChay.getValueAt(i, 4).toString();
					String daBan = tableBanChay.getValueAt(i, 5).toString();
					tbl.addCell(new Phrase((i+1)+"",textFont));
					tbl.addCell(new Phrase(maSP,textFont));
					tbl.addCell(new Phrase(tenSP,textFont));
					tbl.addCell(new Phrase(loai,textFont));
					tbl.addCell(new Phrase(gia,textFont));
					tbl.addCell(new Phrase(ton,textFont));
					tbl.addCell(new Phrase(daBan,textFont));
					
				}
				doc.add(tbl);
				Paragraph t = new Paragraph("-----------------------------------------------------");
				t.setAlignment(Element.ALIGN_RIGHT);
				doc.add(t);
				
				Paragraph prTongTien = new Paragraph("Tổng số lượng sản phẩm đã bán: "+ txtTongSP.getText(),textFont12);
				prTongTien.setAlignment(Element.ALIGN_RIGHT);
				doc.add(prTongTien);
			
				doc.add(t);
				
			} catch (FileNotFoundException | DocumentException e1) {
				JOptionPane.showMessageDialog(null, "In không thành công");
			}
        	if(doc != null && x != JFileChooser.CANCEL_OPTION) {
        		JOptionPane.showMessageDialog(this, "In thành công");
			}
        	doc.close();
        	File f = new File(path);
        	openPDF(f.getName());
        });
    }
    
    public int tongSoLuong(HoaDon hd) {
		int tong = 0;
		ArrayList<ChiTietHoaDon> lsCTHD = dao_ChiTietHoaDon.getAllCTHDTheoMa(hd.getMaHoaDon());
		for (ChiTietHoaDon cthd : lsCTHD) {
			int tongSL = cthd.getSoLuong();
			tong+= tongSL;
		}
		return tong;
	}
    
    public void renderLoadData() {
		ArrayList<SanPham> list = dao_SanPham.nvthongKeSPBanChay(30,GUI_DangNhap.getTxtTaiKhoan().getText());
		modelTK.setRowCount(0);
		tableBanChay.clearSelection();
		modelTK.getDataVector().removeAllElements();
		soLuongDaBan = 0;
		int soLuongSP = 0;
		
		for (SanPham sp : list) {
			String loaisp = dao_LoaiSanPham.getChiTietTheoMaLoai(sp.getLoaiSP().getMaLoai());
			modelTK.addRow(new Object[] {
					sp.getMaSanPham(),sp.getTenSanPham(), loaisp,sp.getGiaNhap(),sp.getSoLuong(),sp.getNhaXB().getMaNXB()
			});
			// lay cot nxb bien thanh cot soLuongDaBan ben sql da xu ly roi
			soLuongSP += Integer.parseInt(sp.getNhaXB().getMaNXB());
		}
		txtTongSP.setText(String.valueOf(soLuongSP));
		tableBanChay.revalidate();
		tableBanChay.repaint();
	}
    
    public void renderData(Date tuNgay, Date toiNgay) {
		long soNgay = Ngay.tinhKhoangNgay(tuNgay, toiNgay);
		int minSoLuong = 0;
		if(soNgay <= 3) {
			minSoLuong = 5;
		}else if(soNgay <= 7)
			minSoLuong = 15;
		else if(soNgay <= 15)
			minSoLuong = 30;
		else if(soNgay <= 30)
			minSoLuong = 60;
		else if(soNgay <= 100)
			minSoLuong = 100;
		else
			minSoLuong = 200;
		ArrayList<SanPham> list = dao_SanPham.nvthongKeSPBanChay(tuNgay, toiNgay, minSoLuong,GUI_DangNhap.getTxtTaiKhoan().getText());
		modelTK.setRowCount(0);
		tableBanChay.clearSelection();
		modelTK.getDataVector().removeAllElements();
		soLuongDaBan = 0;
		int soLuongSP = 0;
		
		for (SanPham sp : list) {
			String loaisp = dao_LoaiSanPham.getChiTietTheoMaLoai(sp.getLoaiSP().getMaLoai());
			modelTK.addRow(new Object[] {
					sp.getMaSanPham(),sp.getTenSanPham(), loaisp,df.format(sp.getGiaNhap()),sp.getSoLuong(),sp.getNhaXB().getMaNXB()
			});
			// lay cot nxb bien thanh cot soLuongDaBan ben sql da xu ly roi
			soLuongSP += Integer.parseInt(sp.getNhaXB().getMaNXB());
		}
		txtTongSP.setText(String.valueOf(soLuongSP));
		tableBanChay.revalidate();
		tableBanChay.repaint();
	}
    public void xoaBang() {
    	modelTK.setRowCount(0);
		tableBanChay.clearSelection();
		modelTK.getDataVector().removeAllElements();
	}
    public static void openPDF(String path) {
        try {
        	if(new File(System.getProperty("user.dir") + "\\hoadon\\" + path + ".pdf").exists()) {
        		Process p = Runtime
        				.getRuntime()
        				.exec("rundll32 url.dll, FileProtocolHandler " + System.getProperty("user.dir") + "\\hoadon\\" + path +".pdf");
        	}else {
        		JOptionPane.showMessageDialog(null, "File is not Exits");
        	}
        }catch (Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTieuDe = new javax.swing.JLabel();
        pnChucNang = new javax.swing.JPanel();
        pnThangNam = new javax.swing.JPanel();
        btnThongKe = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        lbLoaiTK = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lbNgayBD = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        lbNgayKt = new javax.swing.JLabel();
        cbLoaiTK = new javax.swing.JComboBox<>();
        lbThang = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        lbNam = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        pnTK = new javax.swing.JPanel();
        lbTongSP = new javax.swing.JLabel();
        txtTongSP = new javax.swing.JLabel();
        btnInBC = new javax.swing.JButton();
        pnTKBanChay = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBanChay = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTieuDe.setBackground(new java.awt.Color(255, 255, 255));
        lbTieuDe.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lbTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTieuDe.setText("Thống Kê Sản Phẩm");

        pnChucNang.setBackground(new java.awt.Color(255, 255, 255));

        pnThangNam.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnThangNamLayout = new javax.swing.GroupLayout(pnThangNam);
        pnThangNam.setLayout(pnThangNamLayout);
        pnThangNamLayout.setHorizontalGroup(
            pnThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnThangNamLayout.setVerticalGroup(
            pnThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );
        
        btnThongKe.setBackground(new java.awt.Color(51, 153, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new javax.swing.ImageIcon("img\\analytics-20.png"));
        btnThongKe.setText("Thống kê");
        
        btnLamMoi.setBackground(new java.awt.Color(51, 153, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon("img\\sync-20.png"));
        btnLamMoi.setText("Làm mới");

        lbLoaiTK.setText("Thống kê theo");

        lbNgayBD.setText("Ngày bắt đầu");

        lbNgayKt.setText("Ngày kết thúc");

        cbLoaiTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ngày", "Ngày hôm nay", "Ngày hôm qua", "Theo tháng ", "Theo năm" }));

        lbThang.setText("Tháng");

        lbNam.setText("Năm");

        javax.swing.GroupLayout pnChucNangLayout = new javax.swing.GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(
            pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnChucNangLayout.createSequentialGroup()
                        .addComponent(pnThangNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnChucNangLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lbNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        pnChucNangLayout.setVerticalGroup(
            pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNgayKt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnChucNangLayout.createSequentialGroup()
                                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnChucNangLayout.createSequentialGroup()
                        .addComponent(lbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(95, 95, 95)
                .addComponent(pnThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnTK.setBackground(new java.awt.Color(255, 255, 255));
        pnTK.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbTongSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongSP.setForeground(new java.awt.Color(0, 0, 255));
        lbTongSP.setText("Tổng số sản phẩm đã bán:");

        txtTongSP.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTongSP.setForeground(new java.awt.Color(0, 0, 255));
        txtTongSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        btnInBC.setBackground(new java.awt.Color(51, 153, 255));
        btnInBC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnInBC.setForeground(new java.awt.Color(255, 255, 255));
        btnInBC.setIcon(new javax.swing.ImageIcon("img\\print-18.png"));
        btnInBC.setText("In báo cáo");

        javax.swing.GroupLayout pnTKLayout = new javax.swing.GroupLayout(pnTK);
        pnTK.setLayout(pnTKLayout);
        pnTKLayout.setHorizontalGroup(
            pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKLayout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(lbTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(txtTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnInBC, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTKLayout.setVerticalGroup(
            pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTKLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        pnTKBanChay.setBackground(new java.awt.Color(255, 255, 255));
        pnTKBanChay.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách thống kê"));

        tableBanChay.setModel(modelTK = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Tên loại", "Đơn giá", "Số lượng tồn", "Số lượng đã bán"
            }
        ));
        tableBanChay.setRowHeight(30);
        tableBanChay.setSelectionBackground(new java.awt.Color(51, 153, 255));
        jScrollPane1.setViewportView(tableBanChay);

        javax.swing.GroupLayout pnTKBanChayLayout = new javax.swing.GroupLayout(pnTKBanChay);
        pnTKBanChay.setLayout(pnTKBanChayLayout);
        pnTKBanChayLayout.setHorizontalGroup(
            pnTKBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKBanChayLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1423, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTKBanChayLayout.setVerticalGroup(
            pnTKBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnTKBanChay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(550, 550, 550)
                .addComponent(pnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnTKBanChay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInBC;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cbLoaiTK;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel lbLoaiTK;
    private javax.swing.JLabel lbNam;
    private javax.swing.JLabel lbNgayBD;
    private javax.swing.JLabel lbNgayKt;
    private javax.swing.JLabel lbThang;
    private javax.swing.JLabel lbTieuDe;
    private javax.swing.JLabel lbTongSP;
    private javax.swing.JPanel pnChucNang;
    private javax.swing.JPanel pnTK;
    private javax.swing.JPanel pnTKBanChay;
    private javax.swing.JPanel pnThangNam;
    private javax.swing.JTable tableBanChay;
    private javax.swing.JLabel txtTongSP;
    // End of variables declaration//GEN-END:variables
}
