package com.codegym.lib.services;

import com.codegym.lib.models.Student;
import com.codegym.lib.repositories.IStudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService implements IStudentService {

    private IStudentRepository studentRepository;

    StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }
}
