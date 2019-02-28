package coliseum.service;

import java.io.Serializable;

public class BaseServiceResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer totalRecords;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
}