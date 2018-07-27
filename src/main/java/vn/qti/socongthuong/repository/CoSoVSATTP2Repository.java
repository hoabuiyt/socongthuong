package vn.qti.socongthuong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import vn.qti.socongthuong.model.CoSoVSATTP;
import vn.qti.socongthuong.model.CoSoVSATTP2;

public interface CoSoVSATTP2Repository extends CrudRepository<CoSoVSATTP2,Long>, JpaSpecificationExecutor<CoSoVSATTP2> { //,JpaRepository<CoSoVSATTP2, Long>
    
	CoSoVSATTP2 findByidCoSo(Long idCoSo);
	
	//Page<CoSoVSATTP2> findByDanhMucNNKDIdDanhMuc(Long danhMucNNKDIdDanhMuc, Pageable pageable);
    List<CoSoVSATTP2> findByDanhMucNNKDIdDanhMuc(Long danhMucNNKDIdDanhMuc);
    
    
    Iterable<CoSoVSATTP2> findByTenCoSoOrTenChuCoSoContainingIgnoreCase(String tenCoSo, String tenChuCoSo);
    
    Iterable<CoSoVSATTP2> findByTenCoSoOrTenChuCoSoContainingIgnoreCaseAndDanhMucNNKDIdDanhMuc(String tenCoSo, String tenChuCoSo,Long danhMucNNKDIdDanhMuc);
}
