package com.example.sharma.demo01.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.sharma.demo01.dao.StudentRepository;
import com.example.sharma.demo01.model.Student;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/studentService")
@Api(value="onlinestore", description="Operations pertaining to student service")

public class StudentResource {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@ApiOperation(value = "View a list of available students", response = Student.class)
	@GetMapping("/students")
	public List<Student> getAllStudent(){
		
		return studentRepository.findAll();
	}
	@ApiOperation(value = "View a id of available student", response = Student.class)
	@GetMapping("/{id}")
	public Student retriveStudentById(@PathVariable Long id) {
		Optional<Student>  student= studentRepository.findById(id);
		return  student.get();
	}
	@ApiOperation(value = "Delete a id of available student", response = Student.class)
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable Long id ) {
		
		studentRepository.deleteById(id);
		
	}
	@ApiOperation(value = "Create a  student", response = Student.class)
	@PostMapping("/noOfstudents")
	
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		
		Student savedStudent= studentRepository.save(student);
		URI urilocation = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(savedStudent.getId()).toUri();	
		
		return ResponseEntity.created(urilocation).build();
		
		
		
		
	}
	@ApiOperation(value = "Update a id of available student", response = Student.class)
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id){
		
		
		Optional<Student> studentobj= studentRepository.findById(student.getId());
		
		if (!studentobj.isPresent())
			return ResponseEntity.notFound().build();

		
		student.setId(id);
		studentRepository.save(student);
		
		return ResponseEntity.noContent().build();
	}
	

}
