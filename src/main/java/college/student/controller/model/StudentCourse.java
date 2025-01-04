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

}
