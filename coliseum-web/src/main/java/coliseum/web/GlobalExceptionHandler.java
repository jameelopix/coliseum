package coliseum.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import coliseum.service.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public BaseClientModel handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		BaseClientModel widgetModel = new BaseClientModel();
		widgetModel.setErrorMessages(Arrays.asList(error.getMessage()));
		List<String> errorMessages = new ArrayList<>();
		for (ObjectError objectError : error.getBindingResult().getAllErrors()) {
			errorMessages.add(objectError.getCode() + ":" + objectError.getDefaultMessage());
		}
		widgetModel.setErrorMessages(errorMessages);
		return widgetModel;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public BaseClientModel handleBusinessException(BusinessException exception) {
		BaseClientModel widgetModel = new BaseClientModel();
		widgetModel.setErrorDTOs(exception.getErrorDTOs());
		return widgetModel;
	}
}
