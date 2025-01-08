package college.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import college.student.controller.model.StudentCourse;
import college.student.controller.model.StudentData;
import college.student.controller.model.StudentGrade;
import lombok.extern.slf4j.Slf4j;
import college.student.service.StudentService;

@RestController
@RequestMapping("/college_student")
@Slf4j

public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	// Create  student
	@PostMapping("/student")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentData insertStudent(@RequestBody StudentData studentData) {
		log.info("Creating Student {}", studentData);
		return studentService.saveStudent(studentData);
	}
	
	// Read  all student
	@GetMapping("/student")
	public List<StudentData> retrieveAllStudents() {
		log.info("Retrieving all students");
		return studentService.retrieveAllStudents();
	}
	
	// Read  student
	@GetMapping("/student/{studentId}")
	public StudentData retrieveStudent(@PathVariable Long studentId) {
		log.info("Retrieveing student with ID= {}",studentId);
		return studentService.retrieveStudentById(studentId);
	}
	
	// Update  student
	@PutMapping("/student/{studentId}")
	public StudentData updateStudent(@PathVariable Long studentId,
			@RequestBody StudentData studentData) {
		studentData.setStudentId(studentId);
		log.info("Updating student with id {}",studentId );
		
		return studentService.saveStudent(studentData);
	}
	
	// Delete  student
	@DeleteMapping("/student/{studentId}")
	public Map<String, String> deleteStudent(@PathVariable Long studentId) {
		log.info("Deleting student with ID = {}.", studentId);
		studentService.deleteStudent(studentId);
		
		return Map.of("message", "Student with ID= " + studentId +
				" was deleted succesfully");
	}
	
	// Delete  all student
	@DeleteMapping("/student")
	public void deleteAllStudents() {
		log.info("Attempting to delete all students");
		throw new UnsupportedOperationException(
				"Deleting all students is not supported at this time");
	}
	
	// Create  course
	@PostMapping("/course")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentCourse insertCourse(@RequestBody StudentCourse studentCourse) {
		log.info("Creating location {}", studentCourse);
		return studentService.saveCourse(studentCourse);
	}
	
	// Read  all courses
	@GetMapping("/course")
	public List<StudentCourse> retrieveAllCourses() {
		log.info("Retrieving all courses");
		return studentService.retrieveAllCourses();
	}
	
	// Read  course
	@GetMapping("/course/{courseId}")
	public StudentCourse retrieveCourse(@PathVariable Long courseId) {
		log.info("Retrieving course with ID= {}", courseId);
		return studentService.retrieveCourseById(courseId);
	}
	
	// Update  course
	@PutMapping("/course/{courseId}")
	public StudentCourse updateCourse(@PathVariable Long courseId, 
			@RequestBody StudentCourse studentCourse) {
		studentCourse.setCourseId(courseId);
		log.info("Updating course with ID= ", courseId);
		return studentService.saveCourse(studentCourse);
	}
	
	// Delete  course
	@DeleteMapping("/course/{courseId}")
	public void deleteCourse(@PathVariable Long courseId) {
		log.info("Attempting to delete Course with ID= ", courseId);
		throw new UnsupportedOperationException(
				"Deleting courses is not allowed at this time");
	}
	
	// Delete  all course
	@DeleteMapping("/course")
	public void deleteAllCourses() {
		log.info("Attempting to delete all courses");
		throw new UnsupportedOperationException(
				"Deleting all courses is not supported at this time");
	}
	
	// Create  new grade
	@PostMapping("/student/{studentId}/grade")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentGrade insertGrade(@PathVariable Long studentId, 
			@RequestBody StudentGrade studentGrade) {
		log.info("Inputing Grade for student with ID= {}", studentId);
		return studentService.saveGrade(studentId, studentGrade);
	}
	
	// Read  all grades
	@GetMapping("/student/{studentId}/grade")
	public List<StudentGrade> retrieveAllGrade(@PathVariable Long studentId) {
		log.info("Retrieving all Grades from student with ID= {}", studentId);
		return studentService.retrieveAllGrades(studentId);
	}
	
	// Update  grade
	@PutMapping("/student/{studentId}/grade/{gradeId}")
	public StudentGrade updateGrade(@PathVariable Long studentId, 
			@PathVariable Long gradeId, @RequestBody StudentGrade studentGrade) {
		log.info("Updating grade with ID= {} for student with ID= {}", 
				gradeId, studentId);
		studentGrade.setGradeId(gradeId);
		return studentService.saveGrade(studentId, studentGrade);
	}
	
	// Delete  grade
	// I made the mapping this way so that the grade was still correlated with
	// the student as to make sure you were deleting the right grade
	@DeleteMapping("/student/{studentId}/grade/{gradeId}")
	public Map<String, String> deleteGradeById(@PathVariable Long studentId, 
			@PathVariable Long gradeId) {
		log.info("Deleting grade with ID= {} from student with ID= {}", 
				gradeId, studentId);
		studentService.deleteGradeById(studentId, gradeId);
		return Map.of("msg", "Grade with ID= " + gradeId + " from Student with ID= "
				+ studentId + " has been deleted"); 
	}
	
}	
