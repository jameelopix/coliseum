package org.coliseum.service;

import java.util.List;

import coliseum.service.BaseServiceRequest;

public class StudentServiceRequest extends BaseServiceRequest {

	private static final long serialVersionUID = 1L;

	private Student student;

	private List<Student> students;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
