package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import com.hediyesilaozyurt.entities.authEntity.UserEntity;
import com.hediyesilaozyurt.entities.entities.Courses;
import com.hediyesilaozyurt.entities.entities.MainDepartment;
import com.hediyesilaozyurt.entities.entities.Student;
import com.hediyesilaozyurt.entities.entities.StudentCard;
import com.hediyesilaozyurt.repository.authRepository.UserRepository;
import com.hediyesilaozyurt.repository.respository.CoursesRepository;
import com.hediyesilaozyurt.repository.respository.MainDepartmentRepository;
import com.hediyesilaozyurt.repository.respository.StudentCardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentMapper {

    private final ModelMapper modelMapper;
    private final CoursesRepository coursesRepository;
    private final MainDepartmentRepository mainDepartmentRepository;
    private final StudentCardRepository studentCardRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void setupMapper(){

        // Entity to DTO mapping - ID included
        modelMapper.typeMap(Student.class, DtoStudentResponse.class);

        // Request DTO to Entity mapping -
        modelMapper.createTypeMap(DtoStudentRequest.class, Student.class)
                .addMappings(mapper -> {
                    mapper.skip(Student::setId);
                    //these fields will be manually added
                    mapper.skip(Student::setStudentCard);
                    mapper.skip(Student::setMainDepartment);
                    mapper.skip(Student::setCourses);
                    mapper.skip(Student::setUser);
                });
    }

    public Student toEntity(DtoStudentRequest dto){
        if (dto == null) return null;

        // map scalar fields
        Student student = modelMapper.map(dto, Student.class);

        // set manual fields
        setRelationshipsFromIds(dto, student);

        return student;

/*
     // if you use 2 sided relation between student and student card u have to use the lines below
        if (dto == null) return null;

        Student student = modelMapper.map(dto, Student.class);

        if (student.getStudentCard() != null) {
            student.getStudentCard().setStudent(student);
        }

        return student;*/
    }

    private void setRelationshipsFromIds(DtoStudentRequest requestDto, Student student) {
        // Student Card
        if (requestDto.getStudentCardId() != null) {
            StudentCard card = studentCardRepository.findById(requestDto.getStudentCardId())
                    .orElseThrow(() -> new IllegalArgumentException("StudentCard not found: " + requestDto.getStudentCardId()));
            student.setStudentCard(card);
        }

        // Main Department
        if (requestDto.getMainDepartmentId() != null) {
            MainDepartment department = mainDepartmentRepository.findById(requestDto.getMainDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("MainDepartment not found: " + requestDto.getMainDepartmentId()));
            student.setMainDepartment(department);
        }

        // Courses
        if (requestDto.getCourseIds() != null && !requestDto.getCourseIds().isEmpty()) {
            List<Courses> courses = coursesRepository.findAllById(requestDto.getCourseIds());
            if (courses.size() != requestDto.getCourseIds().size()) {
                throw new IllegalArgumentException("Some courses not found in database");
            }
            student.setCourses(courses);
            log.debug("Set {} courses to student", courses.size());
        }

        // User
        if (requestDto.getUserId() != null) {
            UserEntity user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + requestDto.getUserId()));
            student.setUser(user);
        }
    }

    public DtoStudentResponse toDto(Student entity){
        return entity!=null ? modelMapper.map(entity, DtoStudentResponse.class):null;
    }


    public List<DtoStudentResponse> toDtoList(List<Student> students){
        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Student updateEntityFromDto(DtoStudentRequest requestDto, Student entity){
        if (requestDto == null || entity == null) {
            log.warn("Request DTO or Entity is null in updateEntityFromRequest");
            return entity;
        }

        log.debug("Updating student entity with ID: {}", entity.getId());

        modelMapper.map(requestDto, entity);

        updateRelationshipsFromIds(requestDto, entity);

        return entity;
    }


    private void updateRelationshipsFromIds(DtoStudentRequest requestDto, Student student) {
        // Student Card - update if provided
        if (requestDto.getStudentCardId() != null) {
            StudentCard card = studentCardRepository.findById(requestDto.getStudentCardId())
                    .orElseThrow(() -> new IllegalArgumentException("StudentCard not found: " + requestDto.getStudentCardId()));
            student.setStudentCard(card);
        }

        // Main Department - update if provided
        if (requestDto.getMainDepartmentId() != null) {
            MainDepartment department = mainDepartmentRepository.findById(requestDto.getMainDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("MainDepartment not found: " + requestDto.getMainDepartmentId()));
            student.setMainDepartment(department);
        }

        // Courses - CRITICAL: Clear and reload approach
        if (requestDto.getCourseIds() != null) {
            student.getCourses().clear(); // Mevcut courses'ları temizle

            if (!requestDto.getCourseIds().isEmpty()) {
                List<Courses> newCourses = coursesRepository.findAllById(requestDto.getCourseIds());
                if (newCourses.size() != requestDto.getCourseIds().size()) {
                    throw new IllegalArgumentException("Some courses not found in database");
                }
                student.getCourses().addAll(newCourses);
                log.debug("Updated courses: {} items", newCourses.size());
            }
        }

        // User - update if provided
        if (requestDto.getUserId() != null) {
            UserEntity user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + requestDto.getUserId()));
            student.setUser(user);
        }
    }

}