package com.hediyesilaozyurt.services.impl;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentIU;
import com.hediyesilaozyurt.entities.Student;
import com.hediyesilaozyurt.mapper.StudentMapper;
import com.hediyesilaozyurt.repository.StudentRepository;
import com.hediyesilaozyurt.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IStudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> list() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU) {
        Student student=studentMapper.toEntity(dtoStudentIU);
        Student dbStudent=studentRepository.save(student);
        return studentMapper.toDto(dbStudent);
    }

    @Override
    public boolean existById(Integer id) {
        return studentRepository.existsById(id);
    }

    @Override
    public Student update(Integer id, Student student) {
        Optional<Student> dbStudent=findById(id);
        if(dbStudent.isPresent()){
            Student existingStudent=dbStudent.get();

            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setBirthOfDate(student.getBirthOfDate());

            return studentRepository.save(existingStudent);
        }
        return null;
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }
}
