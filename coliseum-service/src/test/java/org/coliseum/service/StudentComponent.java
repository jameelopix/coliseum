package org.coliseum.service;

import coliseum.service.BaseService;

public interface StudentComponent extends BaseService {

	public StudentServiceResponse saveStudent(StudentServiceRequest studentServiceRequest);

	public StudentServiceResponse updateStudent(StudentServiceRequest studentServiceRequest);

	public StudentServiceResponse deleteStudent(StudentServiceRequest studentServiceRequest);

	public StudentServiceResponse getStudent(StudentServiceRequest studentServiceRequest);
}
