package college.student.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import college.student.controller.model.StudentCourse;
import college.student.controller.model.StudentData;
import college.student.controller.model.StudentGrade;
import college.student.dao.CourseDao;
import college.student.dao.GradeDao;
import college.student.dao.StudentDao;
import college.student.entity.Course;
import college.student.entity.Grade;
import college.student.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private GradeDao gradeDao;
	
	@Transactional(readOnly = false)
	public StudentData saveStudent(StudentData studentData) {
		Student student = studentData.toStudent();
		Student dbStudent = studentDao.save(student);
		
		return new StudentData(dbStudent);
	}
	
	@Transactional(readOnly = true)
	public List<StudentData> retrieveAllStudents() {
		// @formatter:off
		return studentDao.findAll()
				.stream()
				.map(stud -> new StudentData(stud))
				.toList();
		// @formatter:on
	}
	
	@Transactional(readOnly = false)
	public void deleteStudent(Long studentId) {
		Student student = findStudentById(studentId);
		studentDao.delete(student);
	}

	private Student findStudentById(Long studentId) {
		return studentDao.findById(studentId).orElseThrow(() -> 
				new NoSuchElementException("Student with ID= " 
				+ studentId + " was not found" ));
	}
	
	@Transactional(readOnly = true)
	public StudentData retrieveStudentById(Long studentId) {
		Student student = findStudentById(studentId);
		return new StudentData(student);
	}
	
	@Transactional(readOnly = false)
	public StudentCourse saveCourse(StudentCourse studentCourse) {
		Course course = studentCourse.toCourse();
		Course dbCourse = courseDao.save(course);
		return new StudentCourse(dbCourse);
	}
	
	@Transactional
	public StudentCourse retrieveCourseById(Long courseId) {
		Course course = findCourseById(courseId);
		return new StudentCourse(course);
	}

	private Course findCourseById(Long courseId) {
		return courseDao.findById(courseId).orElseThrow(
				() -> new NoSuchElementException("Course with ID= " 
						+ courseId + " was not found"));
	}
	
	@Transactional(readOnly = true)
	public List<StudentCourse> retrieveAllCourses() {
		// @formatter:off
		return courseDao.findAll()
				.stream()
				.map(crs -> new StudentCourse(crs))
				.toList();
		// @formatter:on
	}
	
	@Transactional(readOnly = false)
	public StudentGrade saveGrade(Long studentId, StudentGrade studentGrade) {
		Student student = findStudentById(studentId);
		//Long gradeId = studentGrade.getGradeId();
		Grade grade = findOrCreateGrade(studentId, studentGrade.getGradeId());
		
		copyGradeFields(grade, studentGrade);
		grade.setStudent(student);
		
		Set<Grade> grades = student.getGrades();
		grades.add(grade);
		
		student.setGrades(grades);		
		
		Grade dbGrade = gradeDao.save(grade);
		return new StudentGrade(dbGrade);
	}

	private void copyGradeFields(Grade grade, StudentGrade studentGrade) {
		grade.setLetterGrade(studentGrade.getLetterGrade());
		grade.setPointGrade(studentGrade.getPointGrade());
		grade.setQuarterTaken(studentGrade.getQuarterTaken());
	}

	private Grade findOrCreateGrade(Long studentId, Long gradeId) {
		Grade grade;
		if(Objects.isNull(gradeId)) {
			grade = new Grade();
		}else{
			grade = gradeDao.findById(gradeId)
					.orElseThrow(() -> new NoSuchElementException(
							"Grade with ID= " + gradeId + " does not exist"));
		}
		return grade;
	}
	
	@Transactional(readOnly = true)
	public List<StudentGrade> retrieveAllGrades(Long studentId) {
		StudentData studentData = retrieveStudentById(studentId);
		Set<StudentGrade> grades = studentData.getGrades();
		List<StudentGrade> response = new LinkedList<>();
		
		for(StudentGrade studentGrade : grades) {
			response.add(studentGrade);
		}
		return response;
	}
	
	@Transactional(readOnly = false)
	public void deleteGradeById(Long studentId, Long gradeId) {
		Grade grade = gradeDao.findById(gradeId)
				.orElseThrow(() -> new NoSuchElementException(
						"Grade with ID= " + gradeId + " does not exist"));
		
		List<StudentGrade> grades = retrieveAllGrades(studentId);
		grade.setStudent(findStudentById(studentId));
		
		boolean found = false;
		
		for(StudentGrade studentGrade : grades) {
			if(gradeId == studentGrade.getGradeId()) {
				found = true;
				break;
			}
		}
		
		if(found == false) {
			throw new IllegalArgumentException("Grade with ID= " + gradeId 
					+" from Student with ID= " + studentId + "does not exist");
		}
		else{
			gradeDao.delete(grade);
			System.out.println("after delete");
		}
		
		
	}
	
	// I'm gonna cut it out for now, but leave it in case I need to reverse and expand
//	private Grade findGradeById(Long studentId, Long gradeId) {
//		Grade grade = gradeDao.findById(gradeId)
//				.orElseThrow(() -> new NoSuchElementException(
//						"Grade with ID= " + gradeId + " does not exist"));
//		
//		boolean found = false;
//		
//		return null;
//	}

}
