package vn.qti.socongthuong.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "CoSoVSATTP")
public class CoSoVSATTP implements Serializable {

	private static final long serialVersionUID = 4910225916550731446L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idCoSo", nullable = false)
	private Long idCoSo;

	@Column(name = "tenCoso")
	private String tenCoso;

	@Column(name = "tenChuCoso")
	private String tenChuCoso;

	@Column(name = "diaChiCoso")
	private String diaChiCoso;

	@Column(name = "maXa")
	private String maXa;

	@Column(name = "maHuyen")
	private String maHuyen;

	@Column(name = "maTinh")
	private String maTinh;

	@Column(name = "idDanhMuc")
	private Long idDanhMuc;

	@Column(name = "soGiayCN")
	private String soGiayCN;

	@Column(name = "ngayCapCN")
	private Long ngayCapCN;

	@Column(name = "ghiChu")
	private String ghiChu;

	public CoSoVSATTP() {
	}

	public CoSoVSATTP(String _tenCoSo, String _tenChuCoSo, String _diaChiCoSo, String _maXa, String _maHuyen,
			String _maTinh, Long _idDanhMuc, String _soGiayCN, Long _ngayCapCN, String _ghiChu) {
		this.tenCoso = _tenCoSo;
		this.tenChuCoso = _tenChuCoSo;
		this.diaChiCoso = _diaChiCoSo;
		this.maXa = _maXa;
		this.maHuyen = _maHuyen;
		this.maTinh = _maTinh;
		this.idDanhMuc = _idDanhMuc;
		this.soGiayCN = _soGiayCN;
		this.ngayCapCN = _ngayCapCN;
		this.ghiChu = _ghiChu;
	}

	public Long getIdCoSo() {
		return idCoSo;
	}

	public void setIdCoSo(Long idCoSo) {
		this.idCoSo = idCoSo;
	}

	public String getTenCoso() {
		return tenCoso;
	}

	public void setTenCoso(String tenCoso) {
		this.tenCoso = tenCoso;
	}

	public String getTenChuCoso() {
		return tenChuCoso;
	}

	public void setTenChuCoso(String tenChuCoso) {
		this.tenChuCoso = tenChuCoso;
	}

	public String getDiaChiCoso() {
		return diaChiCoso;
	}

	public void setDiaChiCoso(String diaChiCoso) {
		this.diaChiCoso = diaChiCoso;
	}

	public String getMaXa() {
		return maXa;
	}

	public void setMaXa(String maXa) {
		this.maXa = maXa;
	}

	public String getMaHuyen() {
		return maHuyen;
	}

	public void setMaHuyen(String maHuyen) {
		this.maHuyen = maHuyen;
	}

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public Long getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(Long idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getSoGiayCN() {
		return soGiayCN;
	}

	public void setSoGiayCN(String soGiayCN) {
		this.soGiayCN = soGiayCN;
	}

	public Long getNgayCapCN() {
		return ngayCapCN;
	}

	public void setNgayCapCN(Long ngayCapCN) {
		this.ngayCapCN = ngayCapCN;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

}
