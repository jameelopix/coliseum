package coliseum.web;

import java.io.Serializable;
import java.util.List;

import coliseum.service.ErrorDTO;

public class BaseWidgetModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer recordstoFetch;

	private Integer pageIndex;

	private Integer totalRecords;

	private List<Long> idsToDelete;

	private List<String> errorMessages;

	private List<ErrorDTO> errorDTOs;

	public Integer getRecordstoFetch() {
		return recordstoFetch;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public List<Long> getIdsToDelete() {
		return idsToDelete;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public List<ErrorDTO> getErrorDTOs() {
		return errorDTOs;
	}

	public void setRecordstoFetch(Integer recordstoFetch) {
		this.recordstoFetch = recordstoFetch;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public void setIdsToDelete(List<Long> idsToDelete) {
		this.idsToDelete = idsToDelete;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public void setErrorDTOs(List<ErrorDTO> errorDTOs) {
		this.errorDTOs = errorDTOs;
	}
}