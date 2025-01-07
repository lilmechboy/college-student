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
		
		//I want to incorporate this feature in the future
		//courseId = grade.getCourseId();
	}
	
	public StudentGrade(Long gradeId, String letterGrade, Long pointGrade, 
			String quarterTaken, Long courseId) {
		
		this.gradeId = gradeId;
		this.letterGrade = letterGrade;
		this.pointGrade = pointGrade;
		this.quarterTaken = quarterTaken;
		// this.courseId = courseId;
	}

	public Grade toGrade() {
		Grade grade = new Grade();
		
		grade.setGradeId(gradeId);
		grade.setLetterGrade(letterGrade);
		grade.setPointGrade(pointGrade);
		grade.setQuarterTaken(quarterTaken);
		//grade.setCourseId(courseId);
		return grade;
	}
	
}
