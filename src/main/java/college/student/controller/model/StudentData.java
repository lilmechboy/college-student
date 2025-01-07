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
	private Set<StudentCourse> courses = new HashSet<>();
	private Set<StudentGrade> grades = new HashSet<>();
	
	public StudentData(Student student) {
		
		studentId = student.getStudentId();
		firstName = student.getFirstName();
		lastName = student.getLastName();
		phoneNumber = student.getPhoneNumber();
		email = student.getEmail();	
		address = student.getAddress();
		declaredMajor = student.getDeclaredMajor();

		for(Course course : student.getCourses()) {
			this.courses.add(new StudentCourse(course));
		}
		
		for(Grade grade : student.getGrades()) {
			this.grades.add(new StudentGrade(grade));
		}
			
	}
	
	StudentData(Long studentId, String firstName, String lastName,
			String phoneNumber,	String email, String address, String declaredMajor) {
		
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.declaredMajor = declaredMajor;		
	}

	public Student toStudent() {
		Student student = new Student();
		
		student.setStudentId(studentId);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setPhoneNumber(phoneNumber);
		student.setEmail(email);
		student.setAddress(address);
		student.setDeclaredMajor(declaredMajor);
		
		for(StudentCourse studentCourse : courses) {
			student.getCourses().add(studentCourse.toCourse());
		}
		
		for(StudentGrade studentGrade : grades) {
			student.getGrades().add(studentGrade.toGrade());
		}
		
		return student;
	}
}
