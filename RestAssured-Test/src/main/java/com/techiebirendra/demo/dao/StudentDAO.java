package com.techiebirendra.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.techiebirendra.demo.beans.Student;

@Repository("studentDAO")
public class StudentDAO {
	public static List<Student> studentsData = new ArrayList<>();
	// Default Available Data
	static {
		Student s1 = new Student("birendra", 101, "kol", Double.valueOf(1000));
		Student s2 = new Student("shyam", 102, "mum", Double.valueOf(1100));
		studentsData.add(s1);
		studentsData.add(s2);
	}

	public int save(Student student) {
		if (student != null) {
			studentsData.add(student);
			return 1;
		}
		return 0;

	}

	public List<Student> getAllStudentDetails() {
		// TODO Auto-generated method stub
		return studentsData;
	}

	public Student findById(int id) {
		if (studentsData.size() > 0) {
			return studentsData.stream().filter(s -> s.getId() == id).findFirst().get();
		}
		return null;
	}
}