/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_SanPham;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

/**
 *
 * @author TuanPC
 */
public class GUI_HoaDon extends javax.swing.JPanel implements ActionListener{
	private Dao_HoaDon daoHoaDon;
	private DefaultTableModel modelHoaDon;
    private Dao_ChiTietHoaDon dao_ChiTietHoaDon = new Dao_ChiTietHoaDon();
    private Dao_SanPham dao_SanPham = new Dao_SanPham();
    private DecimalFormat df = new DecimalFormat("#,#00 VND");
	/**
     * Creates new form GUI_HoaDon
     */
    public GUI_HoaDon() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException ex) {
		}
		daoHoaDon = new Dao_HoaDon();
		initComponents();
		updateTable();
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
    }

    private void updateTable() {
    	modelHoaDon.setRowCount(0);
		ArrayList<HoaDon> listHD = daoHoaDon.getALLHDNVKH();
		for (HoaDon hd : listHD) {
			modelHoaDon.addRow(new Object[] { hd.getMaHoaDon(), hd.getNgayLap(), hd.getNhanVien().getHoTen(),hd.getNhanVien().getChucVu(), hd.getKhachHang().getHoTen(), hd.getKhachHang().getSdt(),df.format(tinhTongTien(hd))});
		}
	}
    public double tinhTongTien(HoaDon hd) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		double tong = 0;
		ArrayList<ChiTietHoaDon> lsCTHD = dao_ChiTietHoaDon.getAllCTHDTheoMa(hd.getMaHoaDon());
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

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        chkMaHD = new javax.swing.JCheckBox();
        chkTenNhanVien = new javax.swing.JCheckBox();
        chkTenKH = new javax.swing.JCheckBox();
        btnTim = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jdNgayLap = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnXemChiTiet = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(1426, 722));

        jLabel26.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Quản Lý Hóa Đơn");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tìm kiếm"));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText(" Mã Hóa Đơn:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Tên Nhân Viên:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Ngày Lập Hóa Đơn:");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Tên Khách Hàng:");

        btnTim.setBackground(new java.awt.Color(51, 153, 255));
        btnTim.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setIcon(new javax.swing.ImageIcon("img\\timkiem24.png")); // NOI18N
        btnTim.setText("Tìm Kiếm");

        btnLamMoi.setBackground(new java.awt.Color(51, 153, 255));
        btnLamMoi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon("img\\refresh24.png")); // NOI18N
        btnLamMoi.setText("Làm mới");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkTenNhanVien))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkMaHD)))
                .addGap(95, 95, 95)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkTenKH)
                .addGap(57, 57, 57)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 41, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chkMaHD)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNhanVien)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(chkTenNhanVien))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(btnTim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jdNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenKhachHang)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(chkTenKH)))))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách hóa đơn"));

        table.setModel(modelHoaDon=new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Ngày Lập", "Tên Nhân Viên", "Chức Vụ", "Tên Khách Hàng", "SDT Khách Hàng","Tổng Tiền"
            }
        ));
        table.setRowHeight(30);
        table.setSelectionBackground(new java.awt.Color(51, 153, 255));
        jScrollPane3.setViewportView(table);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );

        btnXemChiTiet.setBackground(new java.awt.Color(51, 153, 255));
        btnXemChiTiet.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnXemChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnXemChiTiet.setIcon(new javax.swing.ImageIcon("img\\document24.png")); // NOI18N
        btnXemChiTiet.setText("Xem Chi Tiết");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(51, 153, 255));
        btnXoa.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon("img\\delete.png")); // NOI18N
        btnXoa.setText("Xóa Hóa Đơn");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(483, 483, 483)
                .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1426, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox chkMaHD;
    private javax.swing.JCheckBox chkTenKH;
    private javax.swing.JCheckBox chkTenNhanVien;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdNgayLap;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(btnLamMoi)) {
			txtMaHoaDon.setText("");
			txtTenKhachHang.setText("");
			txtTenNhanVien.setText("");
			jdNgayLap.setDate(null);
			updateTable();
		}
		else if(o.equals(btnXoa)) {
			if (table.getSelectedRow() != -1) {
				if (JOptionPane.showConfirmDialog(this, "Có chắc chắn xóa không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dao_ChiTietHoaDon.xoaCTHD(modelHoaDon.getValueAt(table.getSelectedRow(), 0).toString());
					daoHoaDon.xoaHoaDon(modelHoaDon.getValueAt(table.getSelectedRow(), 0).toString());
					modelHoaDon.removeRow(table.getSelectedRow());
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn cần xóa");
			}
		}else if(o.equals(btnTim)) {
			String ma = "%"+txtMaHoaDon.getText().trim()+"%";
			String nl = "%%";
			if(jdNgayLap.getDate()!=null) {
				java.util.Date ngay = new java.util.Date(jdNgayLap.getDate().getTime());
				int m = ngay.getMonth()+1;
				int y = ngay.getYear()+1900;
				int d = ngay.getDate();
				String mm = m+"";
				String dd = d+"";
				if(d<10)
					dd = "0"+d;
				if(m<10)
					mm = "0"+m;
				nl = y+"-"+mm+"-"+dd;
				System.out.println(nl);
			}
			String tenNV = "%"+txtTenNhanVien.getText().trim()+"%";
			String tenKH =  "%"+txtTenKhachHang.getText().trim()+"%";
			if(chkMaHD.isSelected())
				ma = txtMaHoaDon.getText().trim();
			if(chkTenNhanVien.isSelected())
				tenNV = txtTenNhanVien.getText().trim();
			if(chkTenKH.isSelected())
				tenKH = txtTenKhachHang.getText().trim();
			if(txtMaHoaDon.getText().trim().equals(""))
				ma = "%%";
			if(txtTenNhanVien.getText().trim().equals(""))
				tenNV = "%%";
			if(txtTenKhachHang.getText().trim().equals(""))
				tenKH = "%%";
			if(jdNgayLap.getDate()==null)
				nl = "%%";
			modelHoaDon.setRowCount(0);
			ArrayList<HoaDon> listHD = daoHoaDon.getHDTheoDK(ma, nl, tenNV, tenKH);
			for (HoaDon hd : listHD) {
				modelHoaDon.addRow(new Object[] { hd.getMaHoaDon(), hd.getNgayLap(), hd.getNhanVien().getHoTen(),hd.getNhanVien().getChucVu(), hd.getKhachHang().getHoTen(), hd.getKhachHang().getSdt(),df.format(tinhTongTien(hd))});
			}
		}else if(o.equals(btnXemChiTiet)) {
			if(table.getSelectedRow()!=-1) {
				GUI_XemCTHD gui_XemCTHD = new GUI_XemCTHD();
				gui_XemCTHD.setVisible(true);
				gui_XemCTHD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gui_XemCTHD.setLocationRelativeTo(null);
				int r = table.getSelectedRow();
				gui_XemCTHD.getLbMaHoaDon().setText(modelHoaDon.getValueAt(r, 0).toString());
				gui_XemCTHD.getLbNgayLap().setText(modelHoaDon.getValueAt(r, 1).toString());
				gui_XemCTHD.getLbNhanVien().setText(modelHoaDon.getValueAt(r, 2).toString());
				gui_XemCTHD.getLbKhachHang().setText(modelHoaDon.getValueAt(r, 4).toString());
				gui_XemCTHD.getLbSDT().setText(modelHoaDon.getValueAt(r, 5).toString());
				int i=0;
				int tongTien = 0;
				for (ChiTietHoaDon ct : dao_ChiTietHoaDon.getAllCTHDTheoMa(modelHoaDon.getValueAt(r, 0).toString())) {
					i++;
					SanPham sp = dao_SanPham.getSPTheoMa(ct.getSanPham().getMaSanPham());
					int sl = ct.getSoLuong();
					double giaBan = sp.tinhGiaBan();
					if(dao_SanPham.getSPTheoNgayGiamGia(sp.getMaSanPham(),modelHoaDon.getValueAt(r, 1).toString())!=null) {
						double giamGia =dao_SanPham.getSPTheoNgayGiamGia(sp.getMaSanPham(),modelHoaDon.getValueAt(r, 1).toString()).getGiamGia();
						giaBan = sp.tinhGiaBan()*(1-giamGia/100);
					}
					double tt = sl*giaBan;
					tongTien +=tt;
					String [] row = {i+"",sp.getTenSanPham(),sl+"",giaBan+"",tt+""};
					gui_XemCTHD.getModel().addRow(row);
				}
				DecimalFormat df = new DecimalFormat("#,##0.00 VND");
				tongTien +=tongTien* 0.05;
				gui_XemCTHD.getLbTongTien().setText(df.format(tongTien));
			}else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn cần xem");
			}
		}
	}
}
