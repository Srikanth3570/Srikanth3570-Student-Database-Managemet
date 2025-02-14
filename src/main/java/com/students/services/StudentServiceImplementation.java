package com.students.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.students.entities.Student;
import com.students.repositories.StudentRepository;

@Service
public class StudentServiceImplementation 
		implements StudentServices{

	@Autowired
	StudentRepository repo;
	
	public String addStudent(Student st) {
	    if (repo.existsByEmail(st.getEmail())) { 
	        return "duplicate"; // Return 'duplicate' if the email already exists
	    }

	    try {
	        repo.save(st); // Save the student and return 'success'
	        return "success";
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception stack trace
	        return "error"; // Handle other errors
	    }
	}



	
	@Override
	public String updateStudent(Student st) {
	    // Assuming "repo.save(st)" updates the student entity.
	    repo.save(st);
	    return "student updated successfully!";
	}

	
	@Override
	public Student searchStudent(int univId) {
	    System.out.println("Searching for student with ID: " + univId);
	    Optional<Student> studentOptional = repo.findById(univId);

	    if (studentOptional.isPresent()) {
	        System.out.println("Student found: " + studentOptional.get());
	        return studentOptional.get();
	    } else {
	        System.out.println("Student with ID " + univId + " not found.");
	        return null; // Handle null properly in the controller
	    }
	}


	    // DELETE STUDENT
	@Override
	public boolean deleteStudent(int univId) {
	    if (repo.existsById(univId)) {
	        repo.deleteById(univId);
	        return true;
	    }
	    return false;
	}


	    // FETCH ALL STUDENTS
	 public List<Student> fetchAllStudents() {
	        return repo.findAll(); // Returns empty list if no students are found
	    }
}
