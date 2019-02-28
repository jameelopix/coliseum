package org.coliseum.service;

import coliseum.service.ErrorDTO;

public class StudentValidatorImpl implements StudentValidator {

	@Override
	public ErrorDTO duplicateStudent(StudentServiceRequest studentServiceRequest) {
		// TODO Auto-generated method stub

		System.out.println("StudentValidatorImpl.duplicateStudent()");
		ErrorDTO errorDTO = new ErrorDTO("ERR101", "StudentValidatorImpl.duplicateStudent()");
		return errorDTO;
	}

	@Override
	public ErrorDTO checkStudentAuth(StudentServiceRequest studentServiceRequest) {
		// TODO Auto-generated method stub
		System.out.println("StudentValidatorImpl.checkStudentAuth()");
		ErrorDTO errorDTO = new ErrorDTO("ERR102", "StudentValidatorImpl.checkStudentAuth()");
		return errorDTO;
	}
}
