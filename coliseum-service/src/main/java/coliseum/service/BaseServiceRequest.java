package coliseum.service;

import java.io.Serializable;

public class BaseServiceRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer recordstoFetch;

	private Integer pageIndex;

	public Integer getRecordstoFetch() {
		return recordstoFetch;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setRecordstoFetch(Integer recordstoFetch) {
		this.recordstoFetch = recordstoFetch;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
}
