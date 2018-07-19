package vn.qti.socongthuong.repository;

import org.springframework.data.repository.CrudRepository;
import vn.qti.socongthuong.model.DanhMucNNKD;

public interface DanhMucNNKDRepository extends CrudRepository<DanhMucNNKD,Long>{
	public DanhMucNNKD findByidDanhMuc(Long idDanhMuc);
}
