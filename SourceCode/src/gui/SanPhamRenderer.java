package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import connectDB.ConnectDB;
import dao.Dao_LoaiSanPham;
import dao.Dao_SanPham;
import dao.Dao_TacGia;
import entity.SanPham;
 
public class SanPhamRenderer extends JPanel implements ListCellRenderer<SanPham> {
 
    private JLabel lbIcon = new JLabel();
    private JLabel lbTen = new JLabel();
    private JLabel lbTacGia = new JLabel();
    private JLabel lbGia = new JLabel();
    private JLabel lbSL = new JLabel();
    private Dao_TacGia dao_TacGia ;
    private Dao_LoaiSanPham dao_LoaiSanPham = new Dao_LoaiSanPham();
    private Dao_SanPham dao_SanPham = new Dao_SanPham();
 
    public SanPhamRenderer() {
    	
    	dao_TacGia = new Dao_TacGia();
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	setLayout(new BorderLayout(5, 5));
        JPanel panelText = new JPanel(new GridLayout(2,2));
        panelText.add(lbTen);
        panelText.add(lbTacGia);
        panelText.add(lbGia);
        panelText.add(lbSL);
        JPanel panelAnh = new JPanel();
        panelAnh.setPreferredSize(new Dimension(80, 120));
        panelAnh.add(lbIcon);
        panelAnh.setBackground(Color.white);
        add(panelAnh, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends SanPham> list,
            SanPham sanPham, int index, boolean isSelected, boolean cellHasFocus) {
    	DecimalFormat df = new DecimalFormat("#,##0");
    	if(dao_LoaiSanPham.getTenLoaiTheoMaSP(sanPham.getMaSanPham()).equals("Sách")) {
    		lbIcon.setIcon(new ImageIcon(sanPham.getHinhAnh()));
    		if(dao_SanPham.getSPTheoMaDangGiamGia(sanPham.getMaSanPham())!=null) {
    			double giamGia =dao_SanPham.getSPTheoMaDangGiamGia(sanPham.getMaSanPham()).getGiamGia();
    			lbTen.setText("Tên: "+sanPham.getTenSanPham()+"(Giảm giá "+giamGia+"%)");	
    			lbGia.setText("Giá: "+df.format(sanPham.tinhGiaBan()*(1-giamGia/100))+" VNĐ (Giá gốc: "+df.format(sanPham.tinhGiaBan())+" VNĐ)");
    		}
    		else {
    			lbTen.setText("Tên: "+sanPham.getTenSanPham());
    			lbGia.setText("Giá bán: "+df.format(sanPham.tinhGiaBan())+" VNĐ");
    		}
    		
    		lbSL.setText("Số lượng: "+sanPham.getSoLuong()+"");
    		lbTacGia.setText("Tác giả: "+dao_TacGia.getTenTGTheoMa(sanPham.getTacGia().getMaTG()));
    	}else {
    		lbIcon.setIcon(new ImageIcon(sanPham.getHinhAnh()));
    		if(dao_SanPham.getSPTheoMaDangGiamGia(sanPham.getMaSanPham())!=null) {
    			double giamGia =dao_SanPham.getSPTheoMaDangGiamGia(sanPham.getMaSanPham()).getGiamGia();
    			lbTen.setText("Tên: "+sanPham.getTenSanPham()+"(Giảm giá "+giamGia+"%)");	
    			lbGia.setText("Giá: "+df.format(sanPham.tinhGiaBan()*(1-giamGia/100))+" VNĐ (Giá gốc: "+df.format(sanPham.tinhGiaBan())+" VNĐ)");
    		}	
    		else {
    			lbTen.setText("Tên: "+sanPham.getTenSanPham());
    			lbGia.setText("Giá bán: "+df.format(sanPham.tinhGiaBan())+" VNĐ");
    		}
    		lbSL.setText("Số lượng: "+sanPham.getSoLuong()+"");
    		lbTacGia.setText("Thương hiệu: "+sanPham.getThuongHieu());
    	}
        lbGia.setForeground(Color.red);
        Font font = new Font("Arial", Font.BOLD, 12);
        lbTen.setFont(font);
        lbGia.setFont(font);
     
        // set Opaque to change background color of JLabel
        lbTen.setOpaque(true);
        lbTacGia.setOpaque(true);
        lbIcon.setOpaque(true);
        lbGia.setOpaque(true);
        lbSL.setOpaque(true);
     
        // when select item
        if (isSelected) {
            lbTen.setBackground(list.getSelectionBackground());
            lbTacGia.setBackground(list.getSelectionBackground());
            lbIcon.setBackground(list.getSelectionBackground());
            lbGia.setBackground(list.getSelectionBackground());
            lbSL.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else { // when don't select
            lbTen.setBackground(Color.white);
            lbTacGia.setBackground(Color.white);
            lbIcon.setBackground(Color.white);
            lbGia.setBackground(Color.white);
            lbSL.setBackground(Color.white);
            
            setBackground(Color.white);
        }
        return this;
    }
}
