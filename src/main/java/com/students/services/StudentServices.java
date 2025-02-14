package com.students.services;

import java.util.List;

import com.students.entities.Student;

public interface StudentServices {
	String addStudent(Student st);
	List<Student> fetchAllStudents();
	Student searchStudent(int univId);
	String updateStudent(Student st);
	boolean deleteStudent(int univId);
}
