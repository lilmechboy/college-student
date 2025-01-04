package college.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import college.student.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

}
