package com.hediyesilaozyurt.services.services.impl;

import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import com.hediyesilaozyurt.entities.entities.Student;
import com.hediyesilaozyurt.mapper.StudentMapper;
import com.hediyesilaozyurt.repository.respository.StudentRepository;
import com.hediyesilaozyurt.services.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<DtoStudentResponse> list() {
       List<Student> students= studentRepository.findAll();
       return studentMapper.toDtoList(students);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public DtoStudentResponse saveStudent(DtoStudentRequest dtoStudentIU) {
        Student student=studentMapper.toEntity(dtoStudentIU);
        Student dbStudent=studentRepository.save(student);
        return studentMapper.toDto(dbStudent);
    }

    @Override
    public boolean existById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public List<DtoStudentResponse> studentsTakingASpecificCourse(Long courseId) {
        List<Student> students=studentRepository.findStudentByCourseId(courseId);
        List<DtoStudentResponse> dtoStudents=studentMapper.toDtoList(students);
        return dtoStudents;
    }

    @Override
    public DtoStudentResponse update(Long id, DtoStudentRequest dtoStudentIU) {
        Student dbStudent= studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
        Student entityToUpdate=studentMapper.updateEntityFromDto(dtoStudentIU,dbStudent);
        return studentMapper.toDto(studentRepository.save(entityToUpdate));
    }

    @Override
    public List<DtoStudentResponse> sortByBirthDate() {
        List<Student> students= studentRepository.sortByBirthDate();
        return studentMapper.toDtoList(students);
    }

    @Override
    public List<DtoStudentResponse> searchByFirstName(String name) {
        List<Student> students=studentRepository.searchByFirstName(name);
        return studentMapper.toDtoList(students);
    }

    @Override
    public Integer getNumberOfTotalStudents() {
        return studentRepository.getNumberOfTotalStudents();
    }

    @Override
    public Optional<DtoStudentResponse> findById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto);
    }

    @Override
    public Optional<DtoStudentResponse> getStudentByCardId(Long cardId) {
        Optional<Student> optionalStudent= studentRepository.getStudentByCardId(cardId);
        return optionalStudent.map(student -> studentMapper.toDto(student));
    }

    @Override
    public Optional<DtoStudentResponse> findByUsername(String username) {
        Optional<Student> optionalStudent=studentRepository.findByUsername(username);
        return optionalStudent.map(student->studentMapper.toDto(student));
    }

    @Override
    public List<DtoStudentResponse> getStudentsByDepartment(Long id) {
        List<Student> students=studentRepository.getStudentsByDepartment(id);
        return studentMapper.toDtoList(students);
    }
}
