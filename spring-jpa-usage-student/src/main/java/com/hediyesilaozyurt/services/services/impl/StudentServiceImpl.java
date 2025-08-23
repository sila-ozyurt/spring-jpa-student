package com.hediyesilaozyurt.services.services.impl;

import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import com.hediyesilaozyurt.entities.entities.Student;
import com.hediyesilaozyurt.mapper.StudentMapper;
import com.hediyesilaozyurt.repository.respository.StudentRepository;
import com.hediyesilaozyurt.services.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<DtoStudentResponse> findAll(Pageable pageable) {
       Page<Student> students= studentRepository.findAll(pageable);
       return studentMapper.toDtoPage(students);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
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
    public Page<DtoStudentResponse> findStudentByCourseId(Long courseId,Pageable pageable) {
        Page<Student> students=studentRepository.findStudentByCourseId(courseId,pageable);
        Page<DtoStudentResponse> dtoStudents=studentMapper.toDtoPage(students);
        return dtoStudents;
    }

    @Override
    @Transactional
    public DtoStudentResponse update(Long id, DtoStudentRequest dtoStudentIU) {
        Student dbStudent= studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
        Student entityToUpdate=studentMapper.updateEntityFromDto(dtoStudentIU,dbStudent);
        return studentMapper.toDto(studentRepository.save(entityToUpdate));
    }

    @Override
    public Page<DtoStudentResponse> sortByBirthDate(Pageable pageable) {
        Page<Student> students= studentRepository.sortByBirthDate(pageable);
        return studentMapper.toDtoPage(students);
    }

    @Override
    public Page<DtoStudentResponse> searchByFirstName(String name,Pageable pageable) {
        Page<Student> students=studentRepository.searchByFirstName(name,pageable);
        return studentMapper.toDtoPage(students);
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
    public Page<DtoStudentResponse> getStudentsByDepartment(Long id,Pageable pageable) {
        Page<Student> students=studentRepository.getStudentsByDepartment(id,pageable);
        return studentMapper.toDtoPage(students);
    }
}
