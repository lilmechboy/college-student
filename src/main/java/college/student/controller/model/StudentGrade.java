package college.student.controller.model;

import college.student.entity.Grade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentGrade {
	
	private Long gradeId;
	private String letterGrade;
	private Long pointGrade;
	private String quarterTaken;
	private Long courseId;
	
	public StudentGrade(Grade grade) {
		
		gradeId = grade.getGradeId();
		letterGrade = grade.getLetterGrade();
		pointGrade = grade.getPointGrade();
		quarterTaken = grade.getQuarterTaken();
		courseId = grade.getCourseId();
	}
	
}
