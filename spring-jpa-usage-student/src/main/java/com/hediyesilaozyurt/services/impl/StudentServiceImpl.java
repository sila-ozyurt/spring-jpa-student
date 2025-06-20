package com.hediyesilaozyurt.services.impl;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.Student;
import com.hediyesilaozyurt.mapper.StudentMapper;
import com.hediyesilaozyurt.repository.StudentRepository;
import com.hediyesilaozyurt.services.IStudentService;
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
    public List<DtoStudent> list() {
       List<Student> students= studentRepository.findAll();
       return studentMapper.toDtoList(students);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public DtoStudent saveStudent(DtoStudent dtoStudentIU) {
        Student student=studentMapper.toEntity(dtoStudentIU);
        Student dbStudent=studentRepository.save(student);
        return studentMapper.toDto(dbStudent);
    }

    @Override
    public boolean existById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public List<DtoStudent> studentsTakingASpecificCourse(Long courseId) {
        List<Student> students=studentRepository.findStudentByCourseId(courseId);
        List<DtoStudent> dtoStudents=studentMapper.toDtoList(students);
        return dtoStudents;
    }

    @Override
    public DtoStudent update(Long id,DtoStudent dtoStudentIU) {
        Optional<Student> optionalStudent=findEntityById(id);
        if(optionalStudent.isPresent()){
            Student dbStudent=optionalStudent.get();
            // Apply the fields from the dto to the existing entity
            studentMapper.updateEntityFromDto(dtoStudentIU,dbStudent);
            // Save the updated entity to the database
            Student updateStudent=studentRepository.save(dbStudent);
            // Convert the updated entity back to a DTO and return it
            return studentMapper.toDto(updateStudent);
        }
        return null;
    }

    @Override
    public Optional<Student> findEntityById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<DtoStudent> sortByBirthDate() {
        List<Student> students= studentRepository.sortByBirthDate();
        return studentMapper.toDtoList(students);
    }

    @Override
    public List<DtoStudent> searchByFirstName(String name) {
        List<Student> students=studentRepository.searchByFirstName(name);
        return studentMapper.toDtoList(students);
    }

    @Override
    public Integer getNumberOfTotalStudents() {
        return studentRepository.getNumberOfTotalStudents();
    }

    @Override
    public Optional<DtoStudent> findById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto);
        //student -> studentMapper.toDto(student)
    }

    @Override
    public DtoStudent getStudentByCardId(Long cardId) {
        Optional<Student> optionalStudent= studentRepository.getStudentByCardId(cardId);
        return studentMapper.toDto(optionalStudent.get());
    }

}
