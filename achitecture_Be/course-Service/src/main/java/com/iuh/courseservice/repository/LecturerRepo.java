package com.iuh.courseservice.repository;

import com.iuh.courseservice.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepo extends JpaRepository<Lecturer,Integer> {
}
