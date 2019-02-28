package org.coliseum.service;

import coliseum.service.BaseService;
import coliseum.service.FlowDescriptor;

public interface StudentService extends BaseService {

	@FlowDescriptor(flow = "saveStudent", request = "studentServiceRequest", response = "studentServiceResponse")
	public StudentServiceResponse saveStudent(StudentServiceRequest studentServiceRequest);

	@FlowDescriptor(flow = "updateStudent", request = "studentServiceRequest", response = "studentServiceResponse")
	public StudentServiceResponse updateStudent(StudentServiceRequest studentServiceRequest);

	@FlowDescriptor(flow = "deleteStudent", request = "studentServiceRequest", response = "studentServiceResponse")
	public StudentServiceResponse deleteStudent(StudentServiceRequest studentServiceRequest);

	@FlowDescriptor(flow = "getStudent", request = "studentServiceRequest", response = "studentServiceResponse")
	public StudentServiceResponse getStudent(StudentServiceRequest studentServiceRequest);
}
