package college.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import college.student.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {

}
