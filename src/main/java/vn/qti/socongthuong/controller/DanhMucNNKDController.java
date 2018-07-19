package vn.qti.socongthuong.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.qti.socongthuong.model.DanhMucNNKD;
import vn.qti.socongthuong.repository.DanhMucNNKDRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/danhmucnnkd")
public class DanhMucNNKDController {

	@Autowired
	private DanhMucNNKDRepository danhMucNNKDRepository;

	// Get All DanhMucNNKD
	@GetMapping("/getall")
	public List<DanhMucNNKD> getAllNotes() {
		// return noteRepository.findAll();
		List<DanhMucNNKD> lt = (List<DanhMucNNKD>) danhMucNNKDRepository.findAll();
		System.out.println("get findAll");
		return lt;
	}

	// Create a new DanhMucNNKD
	@PostMapping("/add")
	public DanhMucNNKD createNote(@Valid @RequestBody DanhMucNNKD nnkd) {
		return danhMucNNKDRepository.save(nnkd);
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

	// XÓA
	@RequestMapping(method = RequestMethod.POST, value = "delete/{id}")
	public String deleteBook(@PathVariable("id") Long idDanhMuc) {
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
