package org.aston.ems.student_service.service;

import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.StudentCreateDTO;
import org.aston.ems.student_service.dto.StudentDTO;
import org.aston.ems.student_service.dto.StudentProgressDataDTO;
import org.aston.ems.student_service.dto.StudentUpdateDTO;
import org.aston.ems.student_service.exception.ResourceNotFoundException;
import org.aston.ems.student_service.mapper.StudentMapper;
import org.aston.ems.student_service.mapper.TaskMapper;
import org.aston.ems.student_service.repository.StudentRepository;
import org.aston.ems.student_service.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final TaskRepository taskRepository;

    private final StudentMapper studentMapper;

    private final TaskMapper taskMapper;

    public List<StudentProgressDataDTO> getAllWithProgressData() {
        var students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::mapToProgressData)
                .toList();
    }

    public List<StudentDTO> getAll() {
        var students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::map)
                .toList();
    }

    public StudentDTO create(StudentCreateDTO userData) {
        var student = studentMapper.map(userData);
        studentRepository.save(student);
        return studentMapper.map(student);
    }

    public StudentDTO findById(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %s not found", id)));
        return studentMapper.map(student);
    }

    public StudentDTO update(StudentUpdateDTO userData, Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %s not found", id)));
        studentMapper.update(userData, student);
        studentRepository.save(student);
        return studentMapper.map(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
