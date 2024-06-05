package com.cydeo.repository;

import com.cydeo.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
}
