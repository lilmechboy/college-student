package college.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import college.student.entity.Grade;

public interface GradeDao extends JpaRepository<Grade, Long> {

}
