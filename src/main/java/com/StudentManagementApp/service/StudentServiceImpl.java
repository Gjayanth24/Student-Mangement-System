package com.StudentManagementApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentManagementApp.entity.Student;
import com.StudentManagementApp.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository repository;

	@Override
	public List<Student> getAllStudents() {

		return repository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {

		return repository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return repository.findById(id).get();

	}

	@Override
	public Student updateStudent(Student student) {
		return repository.save(student);
		
	}

	@Override
	public void deleteStudentById(Long id) {
		repository.deleteById(id);
		
	}

}
