package com.StudentManagementApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.StudentManagementApp.entity.Student;
import com.StudentManagementApp.service.StudentService;

@Controller
public class StudentContoller {

	@Autowired
	StudentService studentService;

	@GetMapping({"/", "/students"})
	public String listStudents(Model model) {

		// model have list of student and this display in students.html file
		model.addAttribute("students", studentService.getAllStudents());
		return "index"; // returning view of html file
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model) {

		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute Student student) {

		studentService.saveStudent(student);
		return "redirect:/index";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {

		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student"; 
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, 
			@ModelAttribute Student student, Model model){
		
//		get student from db by id
		Student existingStudent = studentService.getStudentById(id);
		
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setAge(student.getAge());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setEmail(student.getEmail());
		
		studentService.updateStudent(existingStudent);
		return  "redirect:/index";
	}
		
	
	//delete request
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/index";
	}
	
	 
}
