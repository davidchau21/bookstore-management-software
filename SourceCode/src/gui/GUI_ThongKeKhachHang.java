/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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

import connectDB.ConnectDB;
import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_KhachHang;
import dao.Dao_NhanVien;
import dao.Dao_SanPham;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.SanPham;
import until.Currency;
import until.Ngay;
import until.Pair;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EtchedBorder;

/**
 *
 * @author DELL
 */
public class GUI_ThongKeKhachHang extends javax.swing.JPanel implements ActionListener {
	private DefaultTableModel modelKhachHang;
	private Dao_ChiTietHoaDon daoChiTietHoaDon = new Dao_ChiTietHoaDon();
	private Dao_SanPham daoSanPham = new Dao_SanPham();
	private Dao_HoaDon daoHoaDon = new Dao_HoaDon();
	private Dao_KhachHang daoKhachHang = new Dao_KhachHang();
	private Dao_NhanVien daoNhanVien = new Dao_NhanVien();
	private SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Creates new form ban
	 */
	public GUI_ThongKeKhachHang() {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
		}
		setBackground(Color.WHITE);
		initComponents();
		cboThongKe.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnInThongKe.addActionListener(this);

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		pnDSKH = new javax.swing.JPanel();
		pnDSKH.setBounds(430, 77, 986, 635);
		jScrollPane = new javax.swing.JScrollPane();
		tblKhachHang = new javax.swing.JTable();
		pnThongKe = new javax.swing.JPanel();
		pnThongKe.setBounds(12, 87, 412, 301);
		jLabel6 = new javax.swing.JLabel();
		jLabel6.setBounds(82, 23, 74, 32);
		cboThongKe = new javax.swing.JComboBox<>();
		cboThongKe.setBounds(160, 25, 185, 32);
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setBounds(82, 75, 73, 26);
		jdNgayBatDau = new com.toedter.calendar.JDateChooser();
		jdNgayBatDau.setBounds(159, 75, 186, 26);
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setBounds(82, 119, 73, 26);
		jdNgayKetThuc = new com.toedter.calendar.JDateChooser();
		jdNgayKetThuc.setBounds(159, 119, 186, 26);
		jLabel5 = new javax.swing.JLabel();
		jLabel5.setBounds(82, 163, 73, 26);
		jMonth = new com.toedter.calendar.JMonthChooser();
		jMonth.setBounds(159, 163, 126, 26);
		jLabel7 = new javax.swing.JLabel();
		jLabel7.setBounds(82, 207, 73, 26);
		jYear = new com.toedter.calendar.JYearChooser();
		jYear.setBounds(159, 207, 126, 26);
		btnThongKe = new javax.swing.JButton();
		btnThongKe.setBounds(83, 257, 130, 34);
		btnInThongKe = new javax.swing.JButton();
		btnInThongKe.setBounds(234, 257, 130, 34);
		lblTieuDe = new javax.swing.JLabel();
		lblTieuDe.setBounds(688, 19, 245, 40);
		jSeparator1 = new javax.swing.JSeparator();
		jSeparator1.setBounds(12, 399, 414, 11);
		jLabel8 = new javax.swing.JLabel();
		jLabel8.setBounds(68, 437, 298, 36);
		jLabel9 = new javax.swing.JLabel();
		jLabel9.setBounds(51, 503, 155, 36);
		lblMaKH = new javax.swing.JLabel();
		lblMaKH.setBounds(212, 503, 195, 36);
		jLabel11 = new javax.swing.JLabel();
		jLabel11.setBounds(51, 560, 155, 36);
		lblTenKH = new javax.swing.JLabel();
		lblTenKH.setBounds(216, 560, 195, 36);
		jLabel15 = new javax.swing.JLabel();
		jLabel15.setBounds(51, 606, 155, 36);
		jLabel16 = new javax.swing.JLabel();
		jLabel16.setBounds(51, 662, 155, 36);
		lblTongTien = new javax.swing.JLabel();
		lblTongTien.setBounds(212, 662, 195, 36);
		lblHoaDon = new javax.swing.JLabel();
		lblHoaDon.setBounds(216, 606, 195, 36);

		setBackground(new java.awt.Color(255, 255, 255));
		setPreferredSize(new java.awt.Dimension(1426, 722));

		pnDSKH.setBackground(new java.awt.Color(255, 255, 255));
		pnDSKH.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		tblKhachHang.setModel(modelKhachHang = new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Số hóa đơn",
				"Tổng tiền" }) 
		{
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        }
				);
		jScrollPane.setViewportView(tblKhachHang);

		javax.swing.GroupLayout gl_pnDSKH = new javax.swing.GroupLayout(pnDSKH);
		pnDSKH.setLayout(gl_pnDSKH);
		gl_pnDSKH.setHorizontalGroup(gl_pnDSKH.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				gl_pnDSKH.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
						.addContainerGap()));
		gl_pnDSKH.setVerticalGroup(gl_pnDSKH.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(gl_pnDSKH.createSequentialGroup().addComponent(jScrollPane).addContainerGap()));

		pnThongKe.setBackground(new java.awt.Color(255, 255, 255));
		pnThongKe.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
		jLabel6.setText("Thống kê :");

		cboThongKe.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ngày", "Theo tháng", "Theo năm" }));
		jMonth.setEnabled(false);
		jYear.setEnabled(false);

		jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
		jLabel3.setText("Từ ngày :");

		jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
		jLabel4.setText("Đến ngày :");

		jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
		jLabel5.setText("Tháng :");

		jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
		jLabel7.setText("Năm :");

		btnThongKe.setBackground(new java.awt.Color(0, 153, 204));
		btnThongKe.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
		btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
		btnThongKe.setIcon(new ImageIcon("img\\analytics-20.png"));
		btnThongKe.setText("Thống kê");
		btnThongKe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThongKeActionPerformed(evt);
			}
		});

		btnInThongKe.setBackground(new java.awt.Color(0, 153, 204));
		btnInThongKe.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
		btnInThongKe.setForeground(new java.awt.Color(255, 255, 255));
		btnInThongKe.setIcon(new javax.swing.ImageIcon("img\\print-18.png"));
		btnInThongKe.setText("In thống kê");
		btnInThongKe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInThongKeActionPerformed(evt);
			}
		});

		lblTieuDe.setBackground(new java.awt.Color(255, 255, 255));
		lblTieuDe.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
		lblTieuDe.setText("Thống kê khách hàng");

		jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
		jSeparator1.setForeground(new java.awt.Color(0, 102, 255));

		jLabel8.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
		jLabel8.setText("Khách hàng mua sản phẩm nhiều nhất");

		jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jLabel9.setText("Mã khách hàng :");

		lblMaKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

		jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jLabel11.setText("Tên khách hàng :");

//		jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
//		jLabel13.setText("Số sản phẩm đã mua :");

		lblTenKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

		jLabel15.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jLabel15.setText("Số hóa đơn :");

		jLabel16.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jLabel16.setText("Tổng tiền:");

		lblTongTien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

		lblHoaDon.setFont(new java.awt.Font("SansSerif", 0, 14));
		setLayout(null);
		add(jLabel8);
		add(jLabel15);
		add(jLabel9);
		add(jLabel11);
		add(jLabel16);
		add(lblHoaDon);
		add(lblTenKH);
		add(lblMaKH);
		add(lblTongTien);
		add(pnThongKe);
		pnThongKe.setLayout(null);
		pnThongKe.add(jLabel7);
		pnThongKe.add(jYear);
		pnThongKe.add(jLabel5);
		pnThongKe.add(jLabel3);
		pnThongKe.add(jLabel4);
		pnThongKe.add(jMonth);
		pnThongKe.add(jdNgayBatDau);
		pnThongKe.add(jdNgayKetThuc);
		pnThongKe.add(jLabel6);
		pnThongKe.add(cboThongKe);
		pnThongKe.add(btnThongKe);
		pnThongKe.add(btnInThongKe);
		add(jSeparator1);
		add(pnDSKH);
		add(lblTieuDe);
	}// </editor-fold>

	private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnInThongKeActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnInThongKe;
	private javax.swing.JButton btnThongKe;
	private javax.swing.JComboBox<String> cboThongKe;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private com.toedter.calendar.JMonthChooser jMonth;
	private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JSeparator jSeparator1;
	private com.toedter.calendar.JYearChooser jYear;
	private com.toedter.calendar.JDateChooser jdNgayBatDau;
	private com.toedter.calendar.JDateChooser jdNgayKetThuc;
	private javax.swing.JLabel lblHoaDon;
	private javax.swing.JLabel lblMaKH;
	private javax.swing.JLabel lblTenKH;
	private javax.swing.JLabel lblTieuDe;
	private javax.swing.JLabel lblTongTien;
	private javax.swing.JPanel pnDSKH;
	private javax.swing.JPanel pnThongKe;
	private javax.swing.JTable tblKhachHang;
	private Date tuNgay;
	private Date toiNgay;

	// End of variables declaration
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cboThongKe)) {
			if (cboThongKe.getSelectedItem().toString().equals("Theo ngày")) {
				jMonth.setEnabled(false);
				jYear.setEnabled(false);
				jdNgayBatDau.setEnabled(true);
				jdNgayKetThuc.setEnabled(true);

			} else if (cboThongKe.getSelectedItem().toString().equals("Theo tháng")) {
				jdNgayBatDau.setEnabled(false);
				jdNgayKetThuc.setEnabled(false);
				jMonth.setEnabled(true);
				jYear.setEnabled(true);
			} else if (cboThongKe.getSelectedItem().toString().equals("Theo năm")) {
				jdNgayBatDau.setEnabled(false);
				jdNgayKetThuc.setEnabled(false);
				jMonth.setEnabled(false);
				jYear.setEnabled(true);
			}
		} else if (o.equals(btnThongKe)) {
			long ml = System.currentTimeMillis();
			Date now = new Date(ml);

			java.util.Date ngayBD = new java.util.Date();
			java.util.Date ngayKT = new java.util.Date();

			tuNgay = new Date(ml);
			toiNgay = new Date(ml);

			if (cboThongKe.getSelectedIndex() == 0) {
				ngayBD = jdNgayBatDau.getDate();
				ngayKT = jdNgayKetThuc.getDate();

				tuNgay = new Date(ngayBD.getTime());
				toiNgay = new Date(ngayKT.getTime());

				if (tuNgay.after(now)) {
					JOptionPane.showMessageDialog(this, "Nhập ngày bắt đầu trước ngày hiện tại");
					return;
				}
				if (tuNgay.after(toiNgay)) {
					JOptionPane.showMessageDialog(this, "Nhập ngày bắt đầu phải trước ngày kết thúc");
					return;
				}
			} else if (cboThongKe.getSelectedIndex() == 1) {

				int thang = jMonth.getMonth() + 1;
				int nam = jYear.getYear();
				Pair<Date, Date> range = Ngay.getRangeMonth(thang, nam);
				tuNgay = range.getElement0();
				toiNgay = range.getElement1();
			} else if (cboThongKe.getSelectedIndex() == 2) {
				int nam = jYear.getYear();
				Pair<Date, Date> range = Ngay.getRangeYear(nam);
				tuNgay = range.getElement0();
				toiNgay = range.getElement1();
			}
			try {
				loadData(tuNgay, toiNgay);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if(o.equals(btnInThongKe)) {
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
				
				Paragraph tieuDe = new Paragraph("Thống Kê Khách Hàng\n",textFont24);
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
				Paragraph prTenNV = new Paragraph("Tên nhân viên thống kê: "+ daoNhanVien.getNhanVienTheoMa(GUI_DangNhap.getTxtTaiKhoan().getText()).getHoTen(),textFont);
				Paragraph 	prSDT =	new Paragraph("SĐT: "+ daoNhanVien.getNhanVienTheoMa(GUI_DangNhap.getTxtTaiKhoan().getText()).getSdt(),textFont);
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
				c1 = new PdfPCell(new Phrase("	Mã khách hàng",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(" 	Tên khách hàng",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(" 	Giới tính",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("  Số điện thoại",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	 Số hóa đơn",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				c1 = new PdfPCell(new Phrase("	 Tổng tiền",textFont));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbl.addCell(c1);
				for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
					String maKH = tblKhachHang.getValueAt(i, 0).toString();
					String tenKH = tblKhachHang.getValueAt(i, 1).toString();
					String gt = tblKhachHang.getValueAt(i, 2).toString();
					String sdt = tblKhachHang.getValueAt(i, 3).toString();
					String sohd = tblKhachHang.getValueAt(i, 4).toString();
					String tongTien = tblKhachHang.getValueAt(i, 5).toString();
					tbl.addCell(new Phrase((i+1)+"",textFont));
					tbl.addCell(new Phrase(maKH,textFont));
					tbl.addCell(new Phrase(tenKH,textFont));
					tbl.addCell(new Phrase(gt,textFont));
					tbl.addCell(new Phrase(sdt,textFont));
					tbl.addCell(new Phrase(sohd,textFont));
					tbl.addCell(new Phrase(tongTien,textFont));
				}
				doc.add(tbl);
				Paragraph prTitle = new Paragraph("Khác hàng mua nhiều nhất",textFont20);
				prTitle.setAlignment(Element.ALIGN_LEFT);
				doc.add(prTitle);
				Paragraph t = new Paragraph("-----------------------------------------------------");
				t.setAlignment(Element.ALIGN_LEFT);
				doc.add(t);
				
				Paragraph prMaKH = new Paragraph("Mã khách hàng: "+ lblMaKH.getText(),textFont12);
				prMaKH.setAlignment(Element.ALIGN_LEFT);
				doc.add(prMaKH);
				
				Paragraph prTenKH = new Paragraph("Tên khách hàng: "+ lblTenKH.getText(),textFont12);
				prTenKH.setAlignment(Element.ALIGN_LEFT);
				doc.add(prTenKH);
				
				Paragraph prSoHD = new Paragraph("Số hóa đơn: "+ lblHoaDon.getText(),textFont12);
				prSoHD.setAlignment(Element.ALIGN_LEFT);
				doc.add(prSoHD);
				
				doc.add(t);
				Paragraph prTongTien = new Paragraph("Tổng tiền mua: "+ lblTongTien.getText(),textFont12);
				prTongTien.setAlignment(Element.ALIGN_LEFT);
				doc.add(prTongTien);
				
			} catch (FileNotFoundException | DocumentException e1) {
				JOptionPane.showMessageDialog(null, "In không thành công");
			}
        	if(doc != null && x != JFileChooser.CANCEL_OPTION) {
        		JOptionPane.showMessageDialog(this, "In thành công");
			}
        	doc.close();
        	File f = new File(path);
        	openPDF(f.getName());
		}

	}

	public void loadData(Date tuNgay, Date toiNgay) {
		double tong =0;
		modelKhachHang.setRowCount(0);
		ArrayList<KhachHang> dskh = daoKhachHang.thongKeKhachHang(tuNgay, toiNgay);
		for (KhachHang kh : dskh) {
			String gt = "Nam";
			if (kh.isGioiTinh())
				gt = "Nữ";
			tong = tinhTongTien(tuNgay, toiNgay, kh);
			modelKhachHang.addRow(new Object[] { kh.getMaKhachHang(), kh.getHoTen(), gt, kh.getSdt(),
					kh.getDiaChi().getMaDC(), tong});

		}
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblKhachHang.getModel());
		tblKhachHang.setRowSorter(sorter);
		List <RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);
		
		lblMaKH.setText(tblKhachHang.getValueAt(0, 0).toString());
		lblTenKH.setText(tblKhachHang.getValueAt(0, 1).toString());
		lblHoaDon.setText(tblKhachHang.getValueAt(0, 4).toString());
		lblTongTien.setText(tblKhachHang.getValueAt(0, 5).toString());
	}

	/**
	 * tính tổng tiền 1 hóa đơn
	 * 
	 * @param tongTien
	 * @param hd
	 * @return
	 */
	public double tongTienCTHD(HoaDon hd) {
		double tong = 0;
		ArrayList<ChiTietHoaDon> lsCTHD = daoChiTietHoaDon.getAllCTHDTheoMa(hd.getMaHoaDon());
		for (ChiTietHoaDon ct : lsCTHD) {
			SanPham sp = daoSanPham.getSPTheoMa(ct.getSanPham().getMaSanPham());
			tong += sp.tinhGiaBan() * ct.getSoLuong();
		}
		return tong;
	}

	public double tinhTongTien(Date tuNgay, Date toiNgay,KhachHang kh) {
		double tong = 0;
		ArrayList<HoaDon> lsHoaDon = daoHoaDon.getHDTheoMaKHNgay(tuNgay, toiNgay, kh.getMaKhachHang());
		for (HoaDon hd : lsHoaDon) {
			tong += tongTienCTHD(hd);
		}
		return tong;
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
}
