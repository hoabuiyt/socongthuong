package vn.qti.socongthuong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import vn.qti.socongthuong.model.DanhMucNNKD;

public interface DanhMucNNKDRepository extends PagingAndSortingRepository<DanhMucNNKD, Long> {

	DanhMucNNKD findByidDanhMuc(Long idDanhMuc);
	List<DanhMucNNKD> getBytenDanhMucContaining(String tenDanhMuc);
	

	Page<DanhMucNNKD> getBytenDanhMucContaining(String tenDanhMuc, Pageable pageable);
	


}
