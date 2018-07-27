package vn.qti.socongthuong.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;

import vn.qti.socongthuong.ResourceNotFoundException;
import vn.qti.socongthuong.model.CoSoVSATTP;
import vn.qti.socongthuong.model.CoSoVSATTP2;
import vn.qti.socongthuong.model.DanhMucNNKD;
import vn.qti.socongthuong.repository.CoSoVSATTP2Repository;
import vn.qti.socongthuong.repository.DanhMucNNKDRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/cosovsattp")
public class CoSoVSATTP2Controller {

	@Autowired
	private CoSoVSATTP2Repository coSoVSATTP2Repository;

	@Autowired
	private DanhMucNNKDRepository danhMucNNKDRepository;

	/*
	 * /// get
	 * 
	 * @GetMapping("/danhmucnnkd/{idDanhMuc}/cosovsattps") public
	 * Page<CoSoVSATTP2> getAllCoSoVSATTPByIdDanhMuc(@PathVariable(value =
	 * "idDanhMuc") Long idDanhMuc, Pageable pageable) { return
	 * coSoVSATTP2Repository.findByDanhMucNNKDIdDanhMuc(idDanhMuc, pageable); }
	 */

	@GetMapping("/gencoso")
	public String genDanhMuc(@RequestParam(value = "n") Integer n) {
		Date dateBegin, dateEnd;
		long ngaycap = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Faker faker = new Faker();
		n = ((n != null) ? n : 1000);
		for (int i = 1; i < n; i++) {
			String hero = faker.superhero().name();
			String name = faker.name().fullName();
			String streetAddress = faker.address().streetAddress();
			String ghichu = faker.lorem().sentence();
			try {
				dateBegin = (Date) formatter.parse("01-07-2018");
				dateEnd = (Date) formatter.parse("01-08-2018");
				ngaycap = faker.date().between(dateBegin, dateEnd).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}

			CoSoVSATTPPost cs = new CoSoVSATTPPost();
			cs.setTenCoSo("Tên CS " + hero);
			cs.setTenChuCoSo("Tên chủ CS " + name);
			cs.setDiaChiCoSo("Địa chỉ CS " + streetAddress);
			cs.setMaXa("Mã xã " + i);
			cs.setMaHuyen("Mã huyện " + i);
			cs.setMaTinh("Mã tỉnh " + i);

			// cs.setIdDanhMuc((long)i);
			cs.setSoGiayCN("Số giấy CN " + i);
			cs.setNgayCapCN(ngaycap);
			cs.setGhiChu("Ghi chú " + ghichu);

			long x = 100 + (int) (Math.random() * 1700);
			cs.setIdDanhMuc(x);

			createCoSoVSATTP(cs);
		}
		return "OK";
	}

	/// Post
	/*
	 * @PostMapping("/add") public CoSoVSATTP2
	 * createCoSoVSATTP(@RequestParam(value = "idDanhMuc") Long
	 * danhMucNNKDIdDanhMuc,
	 * 
	 * @Valid @RequestBody CoSoVSATTP2 coSoVSATTP2) { return
	 * danhMucNNKDRepository.findById(danhMucNNKDIdDanhMuc).map(danhMucNNKD -> {
	 * coSoVSATTP2.setDanhMucNNKD(danhMucNNKD); return
	 * coSoVSATTP2Repository.save(coSoVSATTP2); }).orElseThrow(() -> new
	 * ResourceNotFoundException("idDanhMuc " + danhMucNNKDIdDanhMuc +
	 * " not found")); }
	 */
	@PostMapping("/add")
	public CoSoVSATTP2 createCoSoVSATTP(@Valid @RequestBody CoSoVSATTPPost coSoVSATTPPost) {

		CoSoVSATTP2 csSave = new CoSoVSATTP2();
		return danhMucNNKDRepository.findById(coSoVSATTPPost.getIdDanhMuc()).map(danhMucNNKD -> {

			csSave.setTenCoSo(coSoVSATTPPost.getTenCoSo());
			csSave.setTenChuCoSo(coSoVSATTPPost.getTenChuCoSo());
			csSave.setDiaChiCoSo(coSoVSATTPPost.getDiaChiCoSo());
			csSave.setMaXa(coSoVSATTPPost.getMaXa());
			csSave.setMaHuyen(coSoVSATTPPost.getMaHuyen());
			csSave.setMaTinh(coSoVSATTPPost.getMaTinh());
			csSave.setSoGiayCN(coSoVSATTPPost.getSoGiayCN());
			csSave.setNgayCapCN(coSoVSATTPPost.getNgayCapCN());
			csSave.setGhiChu(coSoVSATTPPost.getGhiChu());

			csSave.setDanhMucNNKD(danhMucNNKD);
			return coSoVSATTP2Repository.save(csSave);
		}).orElseThrow(
				() -> new ResourceNotFoundException("idDanhMuc " + coSoVSATTPPost.getIdDanhMuc() + " not found"));
	}

	// get 2 theo KeyWord + get ID Danh Muc
	@GetMapping("/getCoSoVSATTP")
	public KQTVCoSoVSATTP2 getCoSoVSATTP2(@RequestParam(value = "keyword") String keyWord,
			@RequestParam(value = "idDanhMuc") Long danhMucNNKDIdDanhMuc, @RequestParam(value = "page") String page,
			@RequestParam(value = "pageSize") String pageSize) {

		List<CoSoVSATTP2> sourceList = null;

		/*List<CoSoVSATTP2> sourceList = (List<CoSoVSATTP2>) ((danhMucNNKDIdDanhMuc != null)
				? coSoVSATTP2Repository.findByTenCoSoOrTenChuCoSoContainingIgnoreCaseAndDanhMucNNKDIdDanhMuc( keyWord.toString(), keyWord.toString(), danhMucNNKDIdDanhMuc)
				: coSoVSATTP2Repository.findByTenCoSoOrTenChuCoSoContainingIgnoreCase(keyWord.toString(), keyWord.toString()));*/

		if (danhMucNNKDIdDanhMuc != null) {
			sourceList = (List<CoSoVSATTP2>) coSoVSATTP2Repository
					.findByTenCoSoOrTenChuCoSoContainingIgnoreCaseAndDanhMucNNKDIdDanhMuc(keyWord.toString(),
							keyWord.toString(), danhMucNNKDIdDanhMuc);
		} else {
			sourceList = (List<CoSoVSATTP2>) coSoVSATTP2Repository
					.findByTenCoSoOrTenChuCoSoContainingIgnoreCase(keyWord.toString(), keyWord.toString());
		}

		sourceList.sort(Comparator.comparingLong(CoSoVSATTP2::getIdCoSo).reversed());
		int total = sourceList.size();

		// kiểm tra _page ==> mặc định trả về 1
		int _page = (page.isEmpty() || Integer.parseInt(page) < 1) ? 1 : (Integer.parseInt(page));
		// kiểm tra PageSize ==> mặc định trả về 10
		int _pageSize = (pageSize.isEmpty() || pageSize.toString() == "") ? 20 : (Integer.parseInt(pageSize));

		// giá trí đặt biệt < 0 ==> trả về tất cả
		if (_pageSize <= 0) {
			return new KQTVCoSoVSATTP2(total, sourceList);
		}

		int fromIndex = (_page - 1) * _pageSize;
		if (sourceList == null || total < fromIndex) {
			return new KQTVCoSoVSATTP2(total, sourceList);
		}
		sourceList = sourceList.subList(fromIndex, Math.min(fromIndex + _pageSize, total));
		return new KQTVCoSoVSATTP2(total, sourceList);
	}

	// Get a Single CoSoVSATTP
	@GetMapping("/getCoSoVSATTPById")
	public CoSoVSATTP2 getCoSoVSATTPById(@RequestParam(value = "id") Long idCoSo) {
		CoSoVSATTP2 cs = coSoVSATTP2Repository.findByidCoSo(idCoSo);
		if (cs == null) {
			System.out.println("khong tim thay");
			return null;
		} else {
			System.out.println("get don vi " + cs.getIdCoSo());
			return cs;
		}
	}

	// Update a NNKD
	@RequestMapping(method = RequestMethod.POST, value = "update/{id}")
	public CoSoVSATTP2 updateNNKD(@PathVariable(value = "id") Long idCoSo, @Valid @RequestBody CoSoVSATTP2 csNew) {

		CoSoVSATTP2 csData = coSoVSATTP2Repository.findByidCoSo(idCoSo);
		if (csData != null) {
			csData.setTenCoSo(csNew.getTenCoSo());
			csData.setTenChuCoSo(csNew.getTenChuCoSo());
			csData.setDiaChiCoSo(csNew.getDiaChiCoSo());
			csData.setMaXa(csNew.getMaXa());
			csData.setMaHuyen(csNew.getMaHuyen());
			csData.setMaTinh(csNew.getMaTinh());

			// csData.setIdDanhMuc((long) csNew.getIdDanhMuc());
			csData.setSoGiayCN(csNew.getSoGiayCN());
			csData.setNgayCapCN(csNew.getNgayCapCN());
			csData.setGhiChu(csNew.getGhiChu());

			CoSoVSATTP2 updatedCS = coSoVSATTP2Repository.save(csData);
			return updatedCS;
		} else {
			return null;
		}
	}

	// XÓA
	@RequestMapping(method = RequestMethod.POST, value = "delete/{id}")
	public Boolean deleteNNKD(@PathVariable("id") Long idCoSo) {
		System.out.println("Delete CoSoVSATTP with ID = " + idCoSo + "...");
		try {
			CoSoVSATTP2 cs = coSoVSATTP2Repository.findByidCoSo(idCoSo);
			coSoVSATTP2Repository.delete(cs);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/* =========================== */
	/// get theo ID Danh Muc
	/*
	 * @GetMapping("/getCoSoVSATTPByIdDanhMuc") public List<CoSoVSATTP2>
	 * getAllCoSoVSATTPByIdDanhMuc2(
	 * 
	 * @RequestParam(value = "idDanhMuc") Long danhMucNNKDIdDanhMuc) {
	 * List<CoSoVSATTP2> cs =
	 * coSoVSATTP2Repository.findByDanhMucNNKDIdDanhMuc(danhMucNNKDIdDanhMuc);
	 * return cs; }
	 */
}

class KQTVCoSoVSATTP2 {

	Integer total;
	List<CoSoVSATTP2> items;

	public KQTVCoSoVSATTP2() {
	}

	public KQTVCoSoVSATTP2(int size, List<CoSoVSATTP2> nnkd) {
		this.total = size;
		this.items = nnkd;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<CoSoVSATTP2> getItems() {
		return items;
	}

	public void setItems(List<CoSoVSATTP2> items) {
		this.items = items;
	}
}

class CoSoVSATTPPost extends CoSoVSATTP2 {

	private static final long serialVersionUID = 1L;

	private Long idDanhMuc;

	public Long getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(Long idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

}
