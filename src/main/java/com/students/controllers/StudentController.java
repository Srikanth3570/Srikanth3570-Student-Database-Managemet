package com.students.controllers;
import java.util.List;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.students.entities.Student;
import com.students.services.StudentServices;

import jakarta.validation.Valid;

@Controller
public class StudentController {
	@Autowired
	StudentServices service;
	
	@PostMapping("/reg")
	@ResponseBody
	public String addStudent(@Valid @ModelAttribute Student st, Model model) {
	    String result = service.addStudent(st);
	    
	    if (result.equals("duplicate")) {
	        return "duplicate"; // Return 'duplicate' if the email already exists
	    }

	    return "success"; // Return 'success' if registration is successful
	}


	@PostMapping("/upd")
	public String updateStudent(Student st) {
	    String result = service.updateStudent(st);
	    if (result.equals("student updated successfully!")) {
	        // Optionally, you can redirect or display a success message.
	        return "redirect:/fetchAll";  // After update, redirect to "View All Students"
	    }
	    return "index"; // Return to the home page or error page in case of failure.
	}

	@DeleteMapping("/del")
	public ResponseEntity<String> deleteStudent(@RequestParam int univId) {
	    if (service.deleteStudent(univId)) {
	        return ResponseEntity.ok("Student deleted successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
	    }
	}

	@PostMapping("/search")
	public ResponseEntity<?> searchStudent(@RequestParam int univId) {
	    Student student = service.searchStudent(univId);
	    if (student != null) {
	        return ResponseEntity.ok(student);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
	    }
	}



	    // FETCH ALL STUDENTS
	@GetMapping("/fetchAll")
	public String fetchAllStudents(Model model) {
	    List<Student> students = service.fetchAllStudents();
	    model.addAttribute("stList", students); // The attribute name should match the one used in the template
	    return "studentList"; // This should match the file name exactly
	}

}
