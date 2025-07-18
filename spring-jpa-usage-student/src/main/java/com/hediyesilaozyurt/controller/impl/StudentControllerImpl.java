package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.IStudentController;
import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.RootEntity;
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
public class StudentControllerImpl extends RestBaseController implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping(path="/search")
    @Override
    public RootEntity<List<DtoStudent>> searchByFirstName(@RequestParam(value="name") String name) {
        return ok(studentService.searchByFirstName(name));
    }

    @Override
    @GetMapping(path="/list-students-taking-this-course/{courseId}")
    public RootEntity<List<DtoStudent>> studentsTakingASpecificCourse(@PathVariable(name="courseId") Long courseId) {
        return ok(studentService.studentsTakingASpecificCourse(courseId));
    }

    @GetMapping(path="/list")
    @Override
    public RootEntity<List<DtoStudent>> list() {
        return ok(studentService.list());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoStudent> update(@PathVariable(name="id") Long id, @RequestBody @Valid DtoStudent student) {
        return ok(studentService.update(id,student));
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
    public RootEntity<List<DtoStudent>> sortByBirthDate() {
        return ok(studentService.sortByBirthDate());
    }

    @GetMapping(path="/count")
    @Override
    public RootEntity<Integer> getNumberOfTotalStudents() {
        return ok(studentService.getNumberOfTotalStudents());
    }

    @Override
    @GetMapping(path="/get-student/{card_id}")
    public RootEntity<DtoStudent> getStudentByCardId(@PathVariable(name="card_id") Long cardId) {
        return ok(studentService.getStudentByCardId(cardId));
    }

    @PostMapping(path = "/save")
    @Override
    public RootEntity<DtoStudent> saveStudent(@RequestBody @Valid DtoStudent student) {
        return ok(studentService.saveStudent(student));
    }

    @GetMapping("/list/{id}")
    @Override
    public RootEntity<Optional<DtoStudent>> findById(@PathVariable(name="id") Long id) {
        return ok(studentService.findById(id));
    }
}
