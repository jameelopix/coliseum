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
	public BaseWidgetModel handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		BaseWidgetModel widgetModel = new BaseWidgetModel();
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
	public BaseWidgetModel handleBusinessException(BusinessException exception) {
		BaseWidgetModel widgetModel = new BaseWidgetModel();
		widgetModel.setErrorDTOs(exception.getErrorDTOs());
		return widgetModel;
	}
}
