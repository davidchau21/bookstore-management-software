package entity;

public class TacGia {
	private String maTG;
	private String hoTen;
	private int namSinh;
	public TacGia(String maTG, String hoTen, int namSinh) {
		super();
		this.maTG = maTG;
		this.hoTen = hoTen;
		this.namSinh = namSinh;
	}
	public TacGia() {
		super();
	}
	
	public TacGia(String maTG) {
		super();
		this.maTG = maTG;
	}
	public String getMaTG() {
		return maTG;
	}
	public void setMaTG(String maTG) {
		this.maTG = maTG;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}
	@Override
	public String toString() {
		return "TacGia [maTG=" + maTG + ", hoTen=" + hoTen + ", namSinh=" + namSinh + "]";
	}
	
}
