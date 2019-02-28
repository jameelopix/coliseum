package org.coliseum.service;

public class StudentComponentImpl implements StudentComponent {

	@Override
	public StudentServiceResponse saveStudent(StudentServiceRequest studentServiceRequest) {
		// TODO Auto-generated method stub
		System.out.println("StudentComponentImpl.saveStudent()");
		return new StudentServiceResponse();
	}

	@Override
	public StudentServiceResponse updateStudent(StudentServiceRequest studentServiceRequest) {
		// TODO Auto-generated method stub
		System.out.println("StudentComponentImpl.updateStudent()");
		return new StudentServiceResponse();
	}

	@Override
	public StudentServiceResponse deleteStudent(StudentServiceRequest studentServiceRequest) {
		// TODO Auto-generated method stub
		System.out.println("StudentComponentImpl.deleteStudent()");
		return new StudentServiceResponse();
	}

	@Override
	public StudentServiceResponse getStudent(StudentServiceRequest studentServiceRequest) {
		// TODO Auto-generated method stub
		System.out.println("StudentComponentImpl.getStudent()");
		return new StudentServiceResponse();
	}
}
