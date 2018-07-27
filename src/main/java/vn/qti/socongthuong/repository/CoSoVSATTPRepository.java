package vn.qti.socongthuong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import vn.qti.socongthuong.model.CoSoVSATTP;
import vn.qti.socongthuong.model.DanhMucNNKD;

public interface CoSoVSATTPRepository extends CrudRepository<CoSoVSATTP,Long>, JpaSpecificationExecutor<CoSoVSATTP>{
	CoSoVSATTP findByidCoSo(Long idCoSo);
	List<CoSoVSATTP> getBytenCoSoContaining(String tenCoSo);
	
	Iterable<CoSoVSATTP> findByTenCoSoOrTenChuCoSoContainingIgnoreCase(String tenCoSo, String tenChuCoSo);
	
	///List<CoSoVSATTP> getByTenCoSoOrTenChuCoSoContaining(String tenCoSo);
}
