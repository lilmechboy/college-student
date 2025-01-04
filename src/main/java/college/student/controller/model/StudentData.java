package college.student.controller.model;

import java.util.HashSet;
import java.util.Set;

import college.student.entity.Course;
import college.student.entity.Grade;
import college.student.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentData {
	
	private Long studentId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;	
	private String address;
	private String declaredMajor;
	private Set<Course> courses = new HashSet<>();
	private Set<Grade> grades = new HashSet<>();
	
	public StudentData(Student student) {
		
		studentId = student.getStudentId();
		firstName = student.getFirstName();
		lastName = student.getLastName();
		phoneNumber = student.getPhoneNumber();
		email = student.getEmail();	
		address = student.getAddress();
		declaredMajor = student.getDeclaredMajor();

		for(Course course : student.getCourses()) {
			courses.add(course);
		}
		
		for(Grade grade : student.getGrades()) {
			grades.add(grade);
		}
			
	}
}
