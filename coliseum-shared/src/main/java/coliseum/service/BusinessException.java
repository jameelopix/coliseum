package coliseum.service;

import java.util.List;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	private List<ErrorDTO> errorDTOs;

	public BusinessException(List<ErrorDTO> errorDTOs) {
		this.errorDTOs = errorDTOs;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public List<ErrorDTO> getErrorDTOs() {
		return errorDTOs;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrorDTOs(List<ErrorDTO> errorDTOs) {
		this.errorDTOs = errorDTOs;
	}
}
