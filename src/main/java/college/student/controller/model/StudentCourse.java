package college.student.controller.model;

import college.student.entity.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCourse {
	
	private Long courseId;
	private String courseName;
	private String coursePrefix;
	private String courseContent;
	
	public StudentCourse(Course course) {
		courseId = course.getCourseId();
		courseName = course.getCourseName();
		coursePrefix = course.getCoursePrefix();
		courseContent = course.getCourseContent();
	}
	
	public StudentCourse(Long courseId, String courseName, 
			String coursePrefix, String courseContent) {
		
		this.courseId = courseId;
		this.courseName = courseName;
		this.coursePrefix = coursePrefix;
		this.courseContent = courseContent;
	}
	
	public Course toCourse() {
		Course course = new Course();
		
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		course.setCoursePrefix(coursePrefix);
		course.setCourseContent(courseContent);
		
		return course;
	}

}
