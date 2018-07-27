package vn.qti.socongthuong.controller;

import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.qti.socongthuong.model.CoSoVSATTP;
import vn.qti.socongthuong.repository.CoSoVSATTPRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/cosovsattp22")
public class CoSoVSATTPController {

	/*@Autowired
	private CoSoVSATTPRepository CoSoVSATTPRepository;

	@GetMapping("/getall")
	public KQTVCoSoVSATTP getAllNNKD() {
		List<CoSoVSATTP> nnkd = (List<CoSoVSATTP>) CoSoVSATTPRepository.findAll();
		System.out.println("findAll: " + nnkd.size());
		return new KQTVCoSoVSATTP(nnkd.size(), nnkd);
	}
	
	@GetMapping("/gencoso")
	public String genDanhMuc() {
		for (int i = 0; i < 2000; i++) {
			CoSoVSATTP cs = new CoSoVSATTP();
			cs.setTenCoSo("Tên cơ sở "+ i);
			cs.setTenChuCoSo("Tên chủ cơ sở " + i);
			cs.setDiaChiCoSo("Địa chỉ cở sở "+ i);
			cs.setMaXa("Mã xã " + i);
			cs.setMaHuyen("Mã huyện " + i);
			cs.setMaTinh("Mã tỉnh " + i);
			
			cs.setIdDanhMuc((long)i);
			cs.setSoGiayCN("Số giấy chứng nhận " + i);
			cs.setNgayCapCN(Long.parseLong("1531414800000"));
			cs.setGhiChu("Ghi chú " + i);
			CoSoVSATTPRepository.save(cs);
		}		
		return "OK";
	}

	@GetMapping("/getCoSoVSATTP")
	public KQTVCoSoVSATTP getCoSoVSATTP(@RequestParam(value = "keyword") String keyWord, @RequestParam(value = "page") String page, @RequestParam(value = "pageSize") String pageSize) {

		List<CoSoVSATTP> sourceList = (List<CoSoVSATTP>) CoSoVSATTPRepository.findByTenCoSoOrTenChuCoSoContainingIgnoreCase(keyWord.toString(),keyWord.toString());

		sourceList.sort(Comparator.comparingLong(CoSoVSATTP::getIdCoSo).reversed());
		int total = sourceList.size();

		// kiểm tra _page ==> mặc định trả về 1
		int _page = (page.isEmpty() || Integer.parseInt(page) < 1) ? 1 : (Integer.parseInt(page));
		// kiểm tra PageSize ==> mặc định trả về 10
		int _pageSize = (pageSize.isEmpty() || pageSize.toString() == "") ? 20 : (Integer.parseInt(pageSize));

		// giá trí đặt biệt < 0 ==> trả về tất cả
		if (_pageSize <= 0) {
			return new KQTVCoSoVSATTP(total, sourceList);
		}

		int fromIndex = (_page - 1) * _pageSize;
		if (sourceList == null || total < fromIndex) {
			return new KQTVCoSoVSATTP(total, sourceList);
		}
		sourceList = sourceList.subList(fromIndex, Math.min(fromIndex + _pageSize, total));
		return new KQTVCoSoVSATTP(total, sourceList);
	}
	
	// TEST PAGING 
	@GetMapping("/getCoSoVSATTP2")
	public Page<CoSoVSATTP> getCoSoVSATTP2(@RequestParam(value = "keyword") String tenDanhMuc, @RequestParam(value = "page") String page, @RequestParam(value = "pageSize") String pageSize) {
		
		int _page = (page.isEmpty() || Integer.parseInt(page) < 1) ? 1 : (Integer.parseInt(page));
		int _pageSize = (pageSize.isEmpty() || pageSize.toString() == "") ? 10 : (Integer.parseInt(pageSize));
		
		Pageable pageable = new PageRequest(_page,_pageSize,Sort.Direction.DESC,"idDanhMuc");
		
		Page<CoSoVSATTP> sourceList = (Page<CoSoVSATTP>) CoSoVSATTPRepository.getBytenDanhMucContaining(tenDanhMuc,pageable);		
		
		return sourceList;
		
	}


	// Get a Single CoSoVSATTP
	@GetMapping("/cosovsattp/{id}")
	public CoSoVSATTP getCoSoVSATTPById(@PathVariable(value = "id") Long idCoSo) {
		CoSoVSATTP cs = CoSoVSATTPRepository.findByidCoSo(idCoSo);
		if (cs == null) {
			System.out.println("khong tim thay");
			return null;
		} else {
			System.out.println("get don vi " + cs.getIdCoSo());
			return cs;
		}
	}

	 ========================================= 

	// Create a new CoSoVSATTP
	@PostMapping("/add")
	public CoSoVSATTP createCoSoVSATTP(@Valid @RequestBody CoSoVSATTP cs) {
		return CoSoVSATTPRepository.save(cs);
	}

	// XÓA
	@RequestMapping(method = RequestMethod.POST, value = "delete/{id}")
	public String deleteNNKD(@PathVariable("id") Long idCoSo) {
		System.out.println("Delete CoSoVSATTP with ID = " + idCoSo + "...");
		try {
			CoSoVSATTP cs = CoSoVSATTPRepository.findByidCoSo(idCoSo);
			CoSoVSATTPRepository.delete(cs);
		} catch (Exception e) {
			return "Fail to delete!";
		}

		return "CoSoVSATTP has been deleted!";
	}

	// Update a NNKD
	@RequestMapping(method = RequestMethod.POST, value = "update/{id}")
	public CoSoVSATTP updateNNKD(@PathVariable(value = "id") Long idCoSo, @Valid @RequestBody CoSoVSATTP csNew) {

		CoSoVSATTP csData = CoSoVSATTPRepository.findByidCoSo(idCoSo);
		if (csData != null) {
			csData.setTenCoSo(csNew.getTenCoSo());
			csData.setTenChuCoSo(csNew.getTenChuCoSo());
			csData.setDiaChiCoSo(csNew.getDiaChiCoSo());
			csData.setMaXa(csNew.getMaXa());
			csData.setMaHuyen(csNew.getMaHuyen());
			csData.setMaTinh(csNew.getMaTinh());
			
			csData.setIdDanhMuc((long)csNew.getIdDanhMuc());
			csData.setSoGiayCN(csNew.getSoGiayCN());
			csData.setNgayCapCN(csNew.getNgayCapCN());
			csData.setGhiChu(csNew.getGhiChu());
			
			CoSoVSATTP updatedCS = CoSoVSATTPRepository.save(csData);
			return updatedCS;
		} else {
			return null;
		}
	}
*/
}

class KQTVCoSoVSATTP{

	Integer total;
	List<CoSoVSATTP> items;
	
	public KQTVCoSoVSATTP() {
	}

	public KQTVCoSoVSATTP(int size, List<CoSoVSATTP> nnkd) {
		this.total = size;
		this.items = nnkd;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<CoSoVSATTP> getItems() {
		return items;
	}

	public void setItems(List<CoSoVSATTP> items) {
		this.items = items;
	}
}
