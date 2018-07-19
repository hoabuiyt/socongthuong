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

	@Column(name = "tenCoSo")
	private String tenCoSo;

	@Column(name = "tenChuCoSo")
	private String tenChuCoSo;

	@Column(name = "diaChiCoSo")
	private String diaChiCoSo;

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

	public CoSoVSATTP(String tenCoSo, String tenChuCoSo, String diaChiCoSo, String maXa, String maHuyen, String maTinh,
			Long idDanhMuc, String soGiayCN, Long ngayCapCN, String ghiChu) {
		this.tenCoSo = tenCoSo;
		this.tenChuCoSo = tenChuCoSo;
		this.diaChiCoSo = diaChiCoSo;
		this.maXa = maXa;
		this.maHuyen = maHuyen;
		this.maTinh = maTinh;
		this.idDanhMuc = idDanhMuc;
		this.soGiayCN = soGiayCN;
		this.ngayCapCN = ngayCapCN;
		this.ghiChu = ghiChu;
	}

	public CoSoVSATTP() {
	}

	public Long getIdCoSo() {
		return idCoSo;
	}

	public void setIdCoSo(Long idCoSo) {
		this.idCoSo = idCoSo;
	}

	public String getTenCoSo() {
		return tenCoSo;
	}

	public void setTenCoSo(String tenCoSo) {
		this.tenCoSo = tenCoSo;
	}

	public String getTenChuCoSo() {
		return tenChuCoSo;
	}

	public void setTenChuCoSo(String tenChuCoSo) {
		this.tenChuCoSo = tenChuCoSo;
	}

	public String getDiaChiCoSo() {
		return diaChiCoSo;
	}

	public void setDiaChiCoSo(String diaChiCoSo) {
		this.diaChiCoSo = diaChiCoSo;
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
