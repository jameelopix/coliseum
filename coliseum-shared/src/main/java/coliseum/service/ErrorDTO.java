package coliseum.service;

public class ErrorDTO {

	private String code;

	private String message;

	private ErrorType errorType;

	public ErrorDTO(String code, String message) {
		this(code, message, ErrorType.ERROR);
	}

	public ErrorDTO(String code, String message, ErrorType errorType) {
		this.code = code;
		this.message = message;
		this.setErrorType(errorType);
	}

	@Override
	public String toString() {
		return "ErrorDTO [code=" + code + ", message=" + message + "]";
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
}
