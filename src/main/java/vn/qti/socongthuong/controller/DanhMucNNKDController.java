package vn.qti.socongthuong.controller;

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

import vn.qti.socongthuong.model.DanhMucNNKD;
import vn.qti.socongthuong.repository.DanhMucNNKDRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/danhmucnnkd")
public class DanhMucNNKDController {

	@Autowired
	private DanhMucNNKDRepository danhMucNNKDRepository;

	@GetMapping("/getall")
	public KQTVDanhMucNNKD getAllNNKD() {
		List<DanhMucNNKD> nnkd = (List<DanhMucNNKD>) danhMucNNKDRepository.findAll();
		System.out.println("findAll: " + nnkd.size());
		return new KQTVDanhMucNNKD(nnkd.size(), nnkd);
	}

	@GetMapping("/getDanhMucNNKD")
	public KQTVDanhMucNNKD getDanhMucNNKD(@RequestParam(value = "keyword") String tenDanhMuc, @RequestParam(value = "page") String page,
			@RequestParam(value = "pageSize") String pageSize) {

		List<DanhMucNNKD> sourceList = (List<DanhMucNNKD>) danhMucNNKDRepository.getBytenDanhMucContaining(tenDanhMuc.toString());
		int total = sourceList.size();

		// kiểm tra _page ==> mặc định trả về 1
		int _page = (page.isEmpty() || Integer.parseInt(page) < 1) ? 1 : (Integer.parseInt(page));
		// kiểm tra PageSize ==> mặc định trả về 10
		int _pageSize = (pageSize.isEmpty() || pageSize.toString() == "") ? 10 : (Integer.parseInt(pageSize));

		// giá trí đặt biệt < 0 ==> trả về tất cả
		if (_pageSize <= 0) {
			return new KQTVDanhMucNNKD(total, sourceList);
		}

		int fromIndex = (_page - 1) * _pageSize;
		if (sourceList == null || total < fromIndex) {
			return new KQTVDanhMucNNKD(total, sourceList);
		}
		sourceList = sourceList.subList(fromIndex, Math.min(fromIndex + _pageSize, total));
		return new KQTVDanhMucNNKD(total, sourceList);
	}
	
	// TEST PAGING 
	@GetMapping("/getDanhMucNNKD2")
	public Page<DanhMucNNKD> getDanhMucNNKD2(@RequestParam(value = "keyword") String tenDanhMuc, @RequestParam(value = "page") String page, @RequestParam(value = "pageSize") String pageSize) {
		
		int _page = (page.isEmpty() || Integer.parseInt(page) < 1) ? 1 : (Integer.parseInt(page));
		int _pageSize = (pageSize.isEmpty() || pageSize.toString() == "") ? 10 : (Integer.parseInt(pageSize));
		
		Pageable pageable = new PageRequest(_page,_pageSize,Sort.Direction.DESC,"idDanhMuc");
		
		Page<DanhMucNNKD> sourceList = (Page<DanhMucNNKD>) danhMucNNKDRepository.getBytenDanhMucContaining('%'+tenDanhMuc+'%',pageable);		
		
		return sourceList;
		
	}


	// Get a Single DanhMucNNKD
	@GetMapping("/nnkd/{id}")
	public DanhMucNNKD getNNKDById(@PathVariable(value = "id") Long idDanhMuc) {
		DanhMucNNKD nnkd = danhMucNNKDRepository.findByidDanhMuc(idDanhMuc);
		if (nnkd == null) {
			System.out.println("khong tim thay");
			return null;
		} else {
			System.out.println("get don vi " + nnkd.getIdDanhMuc());
			return nnkd;
		}
	}

	/* ========================================= */

	// Create a new DanhMucNNKD
	@PostMapping("/add")
	public DanhMucNNKD createNNKD(@Valid @RequestBody DanhMucNNKD nnkd) {
		return danhMucNNKDRepository.save(nnkd);
	}

	// XÓA
	@RequestMapping(method = RequestMethod.POST, value = "delete/{id}")
	public String deleteNNKD(@PathVariable("id") Long idDanhMuc) {
		System.out.println("Delete DanhMucNNKD with ID = " + idDanhMuc + "...");
		try {
			DanhMucNNKD nnkd = danhMucNNKDRepository.findByidDanhMuc(idDanhMuc);
			danhMucNNKDRepository.delete(nnkd);
		} catch (Exception e) {
			return "Fail to delete!";
		}

		return "NNKD has been deleted!";
	}

	// Update a NNKD
	@RequestMapping(method = RequestMethod.POST, value = "update/{id}")
	public DanhMucNNKD updateNNKD(@PathVariable(value = "id") Long idDanhMuc, @Valid @RequestBody DanhMucNNKD nnkdNew) {

		DanhMucNNKD nnkdData = danhMucNNKDRepository.findByidDanhMuc(idDanhMuc);
		if (nnkdData != null) {
			nnkdData.setTenDanhMuc(nnkdNew.getTenDanhMuc());
			DanhMucNNKD updatedNNKD = danhMucNNKDRepository.save(nnkdData);
			return updatedNNKD;
		} else {
			return null;
		}
	}

}

class KQTVDanhMucNNKD{

	Integer total;
	List<DanhMucNNKD> items;
	
	public KQTVDanhMucNNKD() {
	}

	public KQTVDanhMucNNKD(int size, List<DanhMucNNKD> nnkd) {
		this.total = size;
		this.items = nnkd;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<DanhMucNNKD> getItems() {
		return items;
	}

	public void setItems(List<DanhMucNNKD> items) {
		this.items = items;
	}
}
