package vn.qti.socongthuong.model;

import java.util.List;

public class KQTV<T> {

	Integer total;
	List<T> items;

	public KQTV() {
	}

	public KQTV(int size, List<T> nnkd) {
		this.total = size;
		this.items = nnkd;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}


}
