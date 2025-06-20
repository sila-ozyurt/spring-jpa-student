package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.IStudentController;
import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.Student;
import com.hediyesilaozyurt.services.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.*;

@RestController
@RequestMapping(path = "/rest/api/student")
public class StudentControllerImpl implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping(path="/search")
    @Override
    public List<DtoStudent> searchByFirstName(@RequestParam(value="name") String name) {
        return studentService.searchByFirstName(name);
    }

    @Override
    @GetMapping(path="/list-students-taking-this-course/{courseId}")
    public List<DtoStudent> studentsTakingASpecificCourse(@PathVariable(name="courseId") Long courseId) {
        return studentService.studentsTakingASpecificCourse(courseId);
    }

    @GetMapping(path="/list")
    @Override
    public List<DtoStudent> list() {
        return studentService.list();
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoStudent update(@PathVariable(name="id") Long id, @RequestBody @Valid DtoStudent student) {
        return studentService.update(id,student);
    }

    @DeleteMapping(path="/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(name ="id") Long id) {
        if(!studentService.existById(id)){
            return ResponseEntity.notFound().build();
        }
        studentService.delete(id);

        Map<String, Object> response=new HashMap<>();
        response.put("message","successfully deleted");
        response.put("deletedId",id);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path="/list/orderByDate")
    @Override
    public List<DtoStudent> sortByBirthDate() {
        return studentService.sortByBirthDate();
    }

    @GetMapping(path="/count")
    @Override
    public Integer getNumberOfTotalStudents() {
        return studentService.getNumberOfTotalStudents();
    }

    @Override
    @GetMapping(path="/get-student/{card_id}")
    public DtoStudent getStudentByCardId(@PathVariable(name="card_id") Long cardId) {
        return studentService.getStudentByCardId(cardId);
    }

    @PostMapping(path = "/save")
    @Override
    public DtoStudent saveStudent(@RequestBody @Valid DtoStudent student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/list/{id}")
    @Override
    public Optional<DtoStudent> findById(@PathVariable(name="id") Long id) {
        return studentService.findById(id);
    }
}
