package entity;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String sdt;
	private DiaChi diaChi;
	public NhaCungCap(String maNCC, String tenNCC, String sdt, DiaChi diaChi) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdt = sdt;
		this.diaChi = diaChi;
	}
	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}
	public NhaCungCap() {
		super();
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public DiaChi getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNCC == null) ? 0 : maNCC.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		if (maNCC == null) {
			if (other.maNCC != null)
				return false;
		} else if (!maNCC.equals(other.maNCC))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", sdt=" + sdt + ", diaChi=" + diaChi + "]";
	}
	
}