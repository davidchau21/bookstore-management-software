package entity;

public class DiaChi {
	private String maDC;
	private String tinhTP;
	private String quanHuyen;
	private String phuongXa;
	/**
	 * @return the maDC
	 */
	public String getMaDC() {
		return maDC;
	}
	/**
	 * @param maDC the maDC to set
	 */
	public void setMaDC(String maDC) {
		this.maDC = maDC;
	}
	/**
	 * @return the tinhTP
	 */
	public String getTinhTP() {
		return tinhTP;
	}
	/**
	 * @param tinhTP the tinhTP to set
	 */
	public void setTinhTP(String tinhTP) {
		this.tinhTP = tinhTP;
	}
	/**
	 * @return the quanHuyen
	 */
	public String getQuanHuyen() {
		return quanHuyen;
	}
	/**
	 * @param quanHuyen the quanHuyen to set
	 */
	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}
	/**
	 * @return the phuongXa
	 */
	public String getPhuongXa() {
		return phuongXa;
	}
	/**
	 * @param phuongXa the phuongXa to set
	 */
	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}
	/**
	 * @param maDC
	 * @param tinhTP
	 * @param quanHuyen
	 * @param phuongXa
	 */
	
	
	/**
	 * @param tinhTP
	 * @param quanHuyen
	 * @param phuongXa
	 */
	public DiaChi(String tinhTP, String quanHuyen, String phuongXa) {
		super();
		this.tinhTP = tinhTP;
		this.quanHuyen = quanHuyen;
		this.phuongXa = phuongXa;
	}
	/**
	 * @param maDC
	 */
	public DiaChi(String maDC) {
		super();
		this.maDC = maDC;
	}
	public DiaChi(String maDC, String tinhTP, String quanHuyen, String phuongXa) {
		super();
		this.maDC = maDC;
		this.tinhTP = tinhTP;
		this.quanHuyen = quanHuyen;
		this.phuongXa = phuongXa;
	}
	/**
	 * 
	 */
	public DiaChi() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DiaChi [maDC=" + maDC + ", tinhTP=" + tinhTP + ", quanHuyen=" + quanHuyen + ", phuongXa=" + phuongXa
				+ "]";
	}
}

