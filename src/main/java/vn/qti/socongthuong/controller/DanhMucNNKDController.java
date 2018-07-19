package vn.qti.socongthuong.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.qti.socongthuong.repository.DanhMucNNKDRepository;
import vn.qti.socongthuong.model.DanhMucNNKD;

@RestController
@RequestMapping("/api/danhmucnnkd")
public class DanhMucNNKDController {
	
	@Autowired
	DanhMucNNKDRepository danhMucNNKDRepository;
	
	// Get All DanhMucNNKD
	@GetMapping("/getall")
	public List<DanhMucNNKD> getAllNotes() {
	    //return (List<DanhMucNNKD>) danhMucNNKDRepository.findAll();
	    List<DanhMucNNKD> lt = (List<DanhMucNNKD>) danhMucNNKDRepository.findAll();
		System.out.println("get findAll");
		return lt;
	}

    // Create a new DanhMucNNKD
	@PostMapping("/add")
	public DanhMucNNKD createDanhMucNNKD(@Valid @RequestBody DanhMucNNKD nnkd) {
	    return danhMucNNKDRepository.save(nnkd);
	}

    // Get a Single DanhMucNNKD

    // Update a DanhMucNNKD

    // Delete a DanhMucNNKD
}
