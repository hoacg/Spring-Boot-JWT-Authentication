package com.codegym.lib.repositories;

import com.codegym.lib.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepository extends CrudRepository<Student, Long> {
}
