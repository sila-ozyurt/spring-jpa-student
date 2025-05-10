package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.IStudentController;
import com.hediyesilaozyurt.entities.Student;
import com.hediyesilaozyurt.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/rest/api/student")
public class StudentControllerImpl implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping(path="/list")
    @Override
    public List<Student> list() {
        return studentService.list();
    }

    @DeleteMapping(path="/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(name ="id") Integer id) {
        if(!studentService.existById(id)){
            return ResponseEntity.notFound().build();
        }
        studentService.delete(id);

        Map<String, Object> response=new HashMap<>();
        response.put("message","successfully deleted");
        response.put("deletedId",id);

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/save")
    @Override
    public Student saveStudent(@RequestBody Student student) {
        System.out.println(student);
        return studentService.saveStudent(student);
    }

    @GetMapping("/list/{id}")
    @Override
    public Optional<Student> findById(@PathVariable(name="id") Integer id) {
        return studentService.findById(id);
    }
}
