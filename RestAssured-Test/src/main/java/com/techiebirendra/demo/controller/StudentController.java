package com.techiebirendra.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techiebirendra.demo.beans.Greetings;
import com.techiebirendra.demo.beans.Student;
import com.techiebirendra.demo.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	public StudentController(StudentService studentService){
		this.studentService=studentService;
	}
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody Student student) {
		return studentService.save(student);

	}

	@GetMapping(path = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody

	public List<Student> getAll() {
		return studentService.getAllStudentDetails();
	}

	@GetMapping(path = "findById", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody

	public Student findById(@RequestParam int id) {
		return studentService.findById(id);
	}

	@GetMapping(value = "/greeting")
	public @ResponseBody Greetings greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Integer id) {
		System.out.println(studentService);
		Greetings greets = studentService.getGreetings(name, id);
		return greets;
	}
}