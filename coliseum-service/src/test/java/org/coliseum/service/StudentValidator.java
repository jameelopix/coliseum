package org.coliseum.service;

import coliseum.service.BaseService;
import coliseum.service.ErrorDTO;

public interface StudentValidator extends BaseService {

	public ErrorDTO duplicateStudent(StudentServiceRequest studentServiceRequest);

	public ErrorDTO checkStudentAuth(StudentServiceRequest studentServiceRequest);
}
