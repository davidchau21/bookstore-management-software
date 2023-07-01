/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;


import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.file.attribute.AclEntry;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.print.Doc;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.Chunk;
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
import com.toedter.calendar.DateUtil;

import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_KhachHang;
import dao.Dao_NhanVien;
import dao.Dao_SanPham;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import until.Ngay;
import until.Pair;
import until.Printer;

/**
 *
 * @author DAVID
 */
public class GUI_ThongKeDoanhThu extends javax.swing.JPanel  {

    private Dao_ChiTietHoaDon dao_ChiTietHoaDon = new Dao_ChiTietHoaDon();
	private Dao_SanPham dao_SanPham = new Dao_SanPham();
	private DefaultTableModel modelTKDT;
	private Dao_HoaDon dao_HoaDon = new Dao_HoaDon();
	private DecimalFormat df = new DecimalFormat("#,#00 VND");
	public Dao_NhanVien dao_nv = new Dao_NhanVien();
	public Dao_KhachHang dao_kh = new Dao_KhachHang();
	private Date tuNgay;
	private Date toiNgay;
	private SimpleDateFormat dft;
	/**
     * Creates new form GUI_ThongKeDoanhTHu
     */
    public GUI_ThongKeDoanhThu() {
        initComponents();
        pnThangNam.setVisible(false);
        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser2.setDateFormatString("dd/MM/yyyy");
        
        btnLamMoi.addActionListener((e)->{
        	cbLoaiTK.setSelectedIndex(0); 
        	xoaBang();
        	txtTongHD.setText(""); // tong hoa don
        	jLabel4.setText(""); // doanh thu
        	jLabel5.setText(""); // loi nhuan
        	jLabel6.setText("");// von
        	jDateChooser1.setDate(null);
        	jDateChooser2.setDate(null);
        });
        btnThongKe.addActionListener((e) -> {
        	long ml = System.currentTimeMillis();
        	Date now = new Date(ml);
      
        	dft = new SimpleDateFormat("dd/MM/yyyy"); 
        	 // lấy ra giá trị ngày dưới dạng java.sql.Date
        	java.util.Date ngayBD= new java.util.Date();
        	java.util.Date ngayKT= new java.util.Date();
        	
        	
        	tuNgay=new Date(ml); // hom nay
        	toiNgay=new Date(ml);
        	
        	if(cbLoaiTK.getSelectedIndex() == 0) {
        		
        		try {
        			ngayBD =  jDateChooser1.getDate();
            		ngayKT = jDateChooser2.getDate();
            		
            		tuNgay = new Date(ngayBD.getTime());
            		toiNgay = new Date(ngayKT.getTime());
				} catch (Exception e2) {
					renderLoadData(); // hom nay
					JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu kết thúc!\n Thống kê cho ngày hôm nay");
				}
        		
        		if(ngayBD == null && ngayKT == null || tuNgay == null && toiNgay == null) {
        			renderLoadData();
        		}
        		
        		
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
            	
        	}else if (cbLoaiTK.getSelectedIndex() == 3) { // Theo thang
        		int thang = jMonthChooser1.getMonth()+1;
        		int nam = jYearChooser1.getYear();
        		 Pair<Date, Date> range = Ngay.getRangeMonth(thang, nam);
        		 tuNgay = range.getElement0();
        		 toiNgay = range.getElement1();
        		 
        		
        	}else if(cbLoaiTK.getSelectedIndex() == 4) { // Theo Nam
        		int nam = jYearChooser1.getYear();
        		Pair<Date, Date> range = Ngay.getRangeYear(nam);
       		 	tuNgay = range.getElement0();
       		 	toiNgay = range.getElement1();
        	}
        	try {
        		renderData(tuNgay, toiNgay);
        		
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
        	
        });
        
        cbLoaiTK.addActionListener((e)->{
        	if(cbLoaiTK.getSelectedIndex()==0) {
        		lbNgayBD.setVisible(true);
        		jDateChooser1.setVisible(true);
        		lbNgayKt.setVisible(true);
        		jDateChooser2.setVisible(true);
        		pnThangNam.setVisible(false);
        	}else if(cbLoaiTK.getSelectedIndex()==1 || cbLoaiTK.getSelectedIndex()==2) {
        		lbNgayBD.setVisible(false);
        		jDateChooser1.setVisible(false);
        		lbNgayKt.setVisible(false);
        		jDateChooser2.setVisible(false);
        		pnThangNam.setVisible(false);
        	}else if(cbLoaiTK.getSelectedIndex()==3) {
        		lbNgayBD.setVisible(false);
        		jDateChooser1.setVisible(false);
        		lbNgayKt.setVisible(false);
        		jDateChooser2.setVisible(false);
        		pnThangNam.setVisible(true);
        		lbThang.setVisible(true);
        		jMonthChooser1.setVisible(true);
        		lbNam.setVisible(true);
        		jYearChooser1.setVisible(true);
        	}else if(cbLoaiTK.getSelectedIndex()==4) {
        		lbNgayBD.setVisible(false);
        		jDateChooser1.setVisible(false);
        		lbNgayKt.setVisible(false);
        		jDateChooser2.setVisible(false);
        		pnThangNam.setVisible(true);
        		lbThang.setVisible(false);
        		jMonthChooser1.setVisible(false);
        	}
        });
        
        btnInBC.addActionListener((e) ->{
        	
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
				
				Paragraph tieuDe = new Paragraph("Báo Cáo Doanh Thu\n",textFont24);
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
				Paragraph prTenNV = new Paragraph("Tên nhân viên thống kê: "+ dao_nv.getNhanVienTheoMa(GUI_DangNhap.getTxtTaiKhoan().getText()).getHoTen(),textFont);
				Paragraph 	prSDT =	new Paragraph("Số điện thoại: "+ dao_nv.getNhanVienTheoMa(GUI_DangNhap.getTxtTaiKhoan().getText()).getSdt(),textFont);
				doc.add(prTenNV); 
				doc.add(prSDT);
				
				int page = doc.getPageNumber()+1;
				Paragraph prTrang = new Paragraph("Trang số: " +page,textFont);
				prTrang.setAlignment(Element.ALIGN_RIGHT);
				if (page > 1) {
					doc.add(prTrang);
				}
				
				doc.add(new Paragraph("\n"));
				
				PdfPTable tbl = new PdfPTable(new float[] {2.0f,3.0f,5.0f,5.0f,3.0f,3.0f,6.0f});
				tbl.setWidthPercentage(100.0f);
				// adding header
				PdfPCell c1 = new PdfPCell(new Phrase(" STT", textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	Mã hóa đơn",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(" 	Tên nhân viên	",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(" 	Tên khách hàng	",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("  Ngày lập",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	 Số lượng",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	 Tổng tiền	",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				for (int i = 0; i < tableTK.getRowCount(); i++) {
					String maHD = tableTK.getValueAt(i, 0).toString();
					String tenNV = tableTK.getValueAt(i, 1).toString();
					String tenKH = tableTK.getValueAt(i, 2).toString();
					String ngayLap = tableTK.getValueAt(i, 3).toString();
					String SLSP = tableTK.getValueAt(i, 4).toString();
					String tongTien = tableTK.getValueAt(i, 5).toString();
					tbl.addCell(new Phrase((i+1)+"",textFont));
					tbl.addCell(new Phrase(maHD,textFont));
					tbl.addCell(new Phrase(tenNV,textFont));
					tbl.addCell(new Phrase(tenKH,textFont));
					tbl.addCell(new Phrase(ngayLap,textFont));
					tbl.addCell(new Phrase(SLSP,textFont));
					tbl.addCell(new Phrase(tongTien,textFont));
				}
				doc.add(tbl);
				Paragraph prDoanhThu = new Paragraph("Tổng doanh thu: "+ jLabel4.getText(),textFont12);
				prDoanhThu.setAlignment(Element.ALIGN_RIGHT);
				doc.add(prDoanhThu);
				Paragraph t = new Paragraph("-----------------------------------------------------");
				t.setAlignment(Element.ALIGN_RIGHT);
				doc.add(t);
				Paragraph prLoiNhuan = new Paragraph("Lợi nhuận: "+ jLabel5.getText(),textFont12);
				prLoiNhuan.setAlignment(Element.ALIGN_RIGHT);
				doc.add(prLoiNhuan);
				
				
			} catch (FileNotFoundException | DocumentException e1) {
				// TODO Auto-generated catch block
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
    

	public double tinhTongTien(HoaDon hd) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		double tong = 0;
		ArrayList<ChiTietHoaDon> lsCTHD = dao_ChiTietHoaDon.getAllCTHDTheoMa(((HoaDon) hd).getMaHoaDon());
		for (ChiTietHoaDon ct : lsCTHD) {
			SanPham sp = dao_SanPham.getSPTheoMa(ct.getSanPham().getMaSanPham());
			double giaBan = sp.tinhGiaBan(); 
			if (dao_SanPham.getSPTheoNgayGiamGia(sp.getMaSanPham(), sdf.format(hd.getNgayLap()))!=null) {
				double giamGia = dao_SanPham.getSPTheoNgayGiamGia(sp.getMaSanPham(), sdf.format(hd.getNgayLap())).getGiamGia();
				giaBan = sp.tinhGiaBan()*(1-giamGia/100);
			}
			tong += giaBan * ct.getSoLuong();
		}
		return tong + tong * 0.05;
	}
    
    public int tongSoLuong(HoaDon hd) {
		int tong = 0;
		ArrayList<ChiTietHoaDon> lsCTHD = dao_ChiTietHoaDon.getAllCTHDTheoMa(((HoaDon) hd).getMaHoaDon());
		for (ChiTietHoaDon cthd : lsCTHD) {
			int tongSL = cthd.getSoLuong();
			tong+= tongSL;
		}
		return tong;
	}
    
    public void xoaBang() {
    	modelTKDT.setRowCount(0);
		tableTK.clearSelection();
		modelTKDT.getDataVector().removeAllElements();
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
        pnDSTK = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTK = new javax.swing.JTable();
        pnChucNang = new javax.swing.JPanel();
        pnThangNam = new javax.swing.JPanel();
        lbThang = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        lbNam = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        btnThongKe = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        lbLoaiTK = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lbNgayBD = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        lbNgayKt = new javax.swing.JLabel();
        cbLoaiTK = new javax.swing.JComboBox<>();
        pnTK = new javax.swing.JPanel();
        pnDoanhThu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnLoiNhuan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnVon = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbTongHD = new javax.swing.JLabel();
        txtTongHD = new javax.swing.JLabel();
        btnInBC = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTieuDe.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lbTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTieuDe.setText("Thống Kê Doanh Thu");

        pnDSTK.setBackground(new java.awt.Color(255, 255, 255));
        pnDSTK.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách thống kê"));

        tableTK.setModel(modelTKDT = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Tên Nhân Viên", "Tên Khách Hàng", "Ngày Lập", "Số lượng đã bán", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableTK.setRowHeight(30);
        tableTK.setSelectionBackground(new java.awt.Color(51, 153, 255));
        jScrollPane1.setViewportView(tableTK);

        javax.swing.GroupLayout pnDSTKLayout = new javax.swing.GroupLayout(pnDSTK);
        pnDSTK.setLayout(pnDSTKLayout);
        pnDSTKLayout.setHorizontalGroup(
            pnDSTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
        );
        pnDSTKLayout.setVerticalGroup(
            pnDSTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pnChucNang.setBackground(new java.awt.Color(255, 255, 255));

        pnThangNam.setBackground(new java.awt.Color(255, 255, 255));

        lbThang.setText("Tháng");

        lbNam.setText("Năm");

        javax.swing.GroupLayout pnThangNamLayout = new javax.swing.GroupLayout(pnThangNam);
        pnThangNam.setLayout(pnThangNamLayout);
        pnThangNamLayout.setHorizontalGroup(
            pnThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThangNamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThangNamLayout.createSequentialGroup()
                        .addComponent(lbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThangNamLayout.createSequentialGroup()
                        .addComponent(lbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        pnThangNamLayout.setVerticalGroup(
            pnThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThangNamLayout.createSequentialGroup()
                .addGroup(pnThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbThang, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(pnThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        
        btnThongKe.setBackground(new java.awt.Color(51, 153, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setText("Thống kê");
        btnThongKe.setIcon(new ImageIcon("img\\analytics-20.png"));

        btnLamMoi.setBackground(new java.awt.Color(51, 153, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setIcon(new ImageIcon("img\\sync-20.png"));

        lbLoaiTK.setText("Thống kê theo");

        lbNgayBD.setText("Ngày bắt đầu");

        lbNgayKt.setText("Ngày kết thúc");

        cbLoaiTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ngày", "Ngày hôm nay", "Ngày hôm qua", "Theo tháng ", "Theo năm" }));

        javax.swing.GroupLayout pnChucNangLayout = new javax.swing.GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(
            pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnThangNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnChucNangLayout.createSequentialGroup()
                                .addComponent(lbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbLoaiTK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
                                .addComponent(lbNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnChucNangLayout.createSequentialGroup()
                                .addComponent(lbNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnChucNangLayout.setVerticalGroup(
            pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnChucNangLayout.createSequentialGroup()
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(pnChucNangLayout.createSequentialGroup()
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)))
                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lbNgayKt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(pnThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnTK.setBackground(new java.awt.Color(255, 255, 255));

        pnDoanhThu.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("$Doanh Thu");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnDoanhThuLayout = new javax.swing.GroupLayout(pnDoanhThu);
        pnDoanhThu.setLayout(pnDoanhThuLayout);
        pnDoanhThuLayout.setHorizontalGroup(
            pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDoanhThuLayout.createSequentialGroup()
                .addGroup(pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDoanhThuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDoanhThuLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pnDoanhThuLayout.setVerticalGroup(
            pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnLoiNhuan.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("$Lợi Nhuận");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnLoiNhuanLayout = new javax.swing.GroupLayout(pnLoiNhuan);
        pnLoiNhuan.setLayout(pnLoiNhuanLayout);
        pnLoiNhuanLayout.setHorizontalGroup(
            pnLoiNhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLoiNhuanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnLoiNhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLoiNhuanLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnLoiNhuanLayout.setVerticalGroup(
            pnLoiNhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLoiNhuanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnVon.setBackground(new java.awt.Color(255, 102, 51));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("$Vốn");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnVonLayout = new javax.swing.GroupLayout(pnVon);
        pnVon.setLayout(pnVonLayout);
        pnVonLayout.setHorizontalGroup(
            pnVonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnVonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnVonLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnVonLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnVonLayout.setVerticalGroup(
            pnVonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lbTongHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongHD.setForeground(new java.awt.Color(0, 0, 255));
        lbTongHD.setText("Tổng số hóa đơn:");

        txtTongHD.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTongHD.setForeground(new java.awt.Color(0, 0, 255));
        
        btnInBC.setBackground(new java.awt.Color(51, 153, 255));
        btnInBC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnInBC.setForeground(new java.awt.Color(255, 255, 255));
        btnInBC.setText("In báo cáo");
        btnInBC.setIcon(new ImageIcon("img\\print-18.png"));

        javax.swing.GroupLayout pnTKLayout = new javax.swing.GroupLayout(pnTK);
        pnTK.setLayout(pnTKLayout);
        pnTKLayout.setHorizontalGroup(
            pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKLayout.createSequentialGroup()
                .addGroup(pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnTKLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(pnLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(pnVon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnTKLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnInBC, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnTKLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lbTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTKLayout.setVerticalGroup(
            pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnVon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(pnTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(btnInBC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnDSTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(pnDSTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInBC;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cbLoaiTK;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel lbLoaiTK;
    private javax.swing.JLabel lbNam;
    private javax.swing.JLabel lbNgayBD;
    private javax.swing.JLabel lbNgayKt;
    private javax.swing.JLabel lbThang;
    private javax.swing.JLabel lbTieuDe;
    private javax.swing.JLabel lbTongHD;
    private javax.swing.JPanel pnChucNang;
    private javax.swing.JPanel pnDSTK;
    private javax.swing.JPanel pnDoanhThu;
    private javax.swing.JPanel pnLoiNhuan;
    private javax.swing.JPanel pnTK;
    private javax.swing.JPanel pnThangNam;
    private javax.swing.JPanel pnVon;
    private javax.swing.JTable tableTK;
    private javax.swing.JLabel txtTongHD;
    // End of variables declaration//GEN-END:variables
	private int soLuongSP;
	private double doanhThu;
	private double soVon;
	
	public void renderLoadData() {
		soLuongSP = 0;
		doanhThu = 0;
		soVon = 0;
		ArrayList<HoaDon> listHD = new Dao_HoaDon().chiTiet(Ngay.homNay(), Ngay.homNay());
		modelTKDT.setRowCount(0);
		double tong = 0;
		int slHD = 0;
		for (HoaDon hd : listHD) {
			ArrayList<ChiTietHoaDon> lsCTHD = dao_ChiTietHoaDon.getAllCTHDTheoMa(((HoaDon) hd).getMaHoaDon());
			for (ChiTietHoaDon ct : lsCTHD) {
				SanPham sp = dao_SanPham.getSPTheoMa(ct.getSanPham().getMaSanPham());;
				int sl = ct.getSoLuong();
				double tongVon = sp.getGiaNhap();
				double tv = sl * tongVon;
				soVon += tv;
			}
			tong = tinhTongTien(hd);
			soLuongSP = tongSoLuong(hd);
			modelTKDT.addRow(new Object[] { hd.getMaHoaDon(), hd.getNhanVien().getHoTen(),
					hd.getKhachHang().getHoTen(), hd.getNgayLap(), soLuongSP,tong});
			slHD += 1;
			doanhThu += tong;
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		txtTongHD.setText(String.valueOf(slHD).toString());
		jLabel4.setText(until.Currency.format(doanhThu).toString());
		jLabel5.setText(until.Currency.format(doanhThu - soVon).toString());
		jLabel6.setText(until.Currency.format(soVon).toString());
	}
    public void renderData(Date tuNgay, Date toiNgay)  throws SQLException{
    	soLuongSP = 0;
		doanhThu = 0;
		soVon = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<HoaDon> listHD = new Dao_HoaDon().chiTiet(tuNgay, toiNgay);
		modelTKDT.setRowCount(0);
		double tong = 0;
		int slHD = 0;
		for (HoaDon hd : listHD) {
			ArrayList<ChiTietHoaDon> lsCTHD = dao_ChiTietHoaDon.getAllCTHDTheoMa(hd.getMaHoaDon());
			for (ChiTietHoaDon ct : lsCTHD) {
				SanPham sp = dao_SanPham.getSPTheoMa(ct.getSanPham().getMaSanPham());;
				int sl = ct.getSoLuong();
				double tongVon = sp.getGiaNhap();
				double tv = sl * tongVon;
				soVon += tv;
				
			}
//			Locale localeVN = new Locale("vi", "VN");
//	    	NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
//	    	String str1 = currencyVN.format(tong);
			tong = tinhTongTien(hd);
			soLuongSP = tongSoLuong(hd);
			modelTKDT.addRow(new Object[] { hd.getMaHoaDon(), hd.getNhanVien().getHoTen(),
					hd.getKhachHang().getHoTen(), sdf.format(hd.getNgayLap()), soLuongSP, tong});
			slHD += 1;
			doanhThu += tong;
		}
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelTKDT);
		tableTK.setRowSorter(sorter);
		List <RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
//		sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);
//		tableTK.setAutoCreateRowSorter(true);
		
		
		txtTongHD.setText(String.valueOf(slHD).toString());
		jLabel4.setText(until.Currency.format(doanhThu).toString());
		jLabel5.setText(until.Currency.format(doanhThu - soVon).toString());
		jLabel6.setText(until.Currency.format(soVon).toString());
	}
    public JPanel getContentPane() {
    	return this.getContentPane();
    }
}
