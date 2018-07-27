package vn.qti.socongthuong.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "DanhMucNNKD")
public class DanhMucNNKD implements Serializable{
	private static final long serialVersionUID = 4910225916550731446L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idDanhMuc", nullable = false)
	private Long idDanhMuc;
	
	@Column(name = "tenDanhMuc")
	private String tenDanhMuc;
	
	public DanhMucNNKD(String _tenDanhMuc) {
		this.tenDanhMuc = _tenDanhMuc;
	}

	public DanhMucNNKD() {
	}

	public Long getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(Long idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}
	
}
