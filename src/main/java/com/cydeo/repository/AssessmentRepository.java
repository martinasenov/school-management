package com.cydeo.repository;

import com.cydeo.entity.Assessment;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {



    Assessment findByStudentIdAndLessonId(Long studentId, Long lessonId);
    Assessment findByStudentAndLesson(Student student, Lesson lesson);
}
