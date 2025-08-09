package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.IStudentController;
import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/rest/api/student")
public class StudentControllerImpl extends RestBaseController implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping(path="/search")
    @PreAuthorize(value="hasAnyRole('ADMIN')")
    @Override
    public RootEntity<List<DtoStudentResponse>> searchByFirstName(@RequestParam(value="name") String name) {
        List<DtoStudentResponse> dtoStudents=studentService.searchByFirstName(name);

        if(dtoStudents!=null){
            return ok(dtoStudents);
        }
        else{
            return error(dtoStudents);
        }
    }

    @Override
    @GetMapping(path="/list-students-taking-this-course/{courseId}")
    @PreAuthorize(value = "hasAnyRole('STUDENT','ADMIN')")
    public RootEntity<List<DtoStudentResponse>> studentsTakingASpecificCourse(@PathVariable(name="courseId") Long courseId) {
        List<DtoStudentResponse> dtoStudents=studentService.studentsTakingASpecificCourse(courseId);

        if(dtoStudents!=null){
            return ok(dtoStudents);
        }
        {
            return error(dtoStudents);
        }
    }

    @GetMapping(path="/list")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<List<DtoStudentResponse>> list() {
        List<DtoStudentResponse> dtoStudents=studentService.list();

        if(dtoStudents!=null){
            return ok(dtoStudents);
        }
        else{
            return error(dtoStudents);
        }

    }

    @PutMapping("/update/{id}")
    @Override
    @PreAuthorize("hasAnyRole('ADMIN')")
    public RootEntity<DtoStudentResponse> update(@PathVariable(name="id") Long id,
                                                 @RequestBody @Valid DtoStudentRequest student) {
        DtoStudentResponse dtoStudent=studentService.update(id,student);

        if(dtoStudent!=null){
            return ok(dtoStudent);
        }
        else{
            return error(dtoStudent);
        }

    }

    @DeleteMapping(path="/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    @Override
    public RootEntity<List<DtoStudentResponse>> sortByBirthDate() {
        List<DtoStudentResponse> dtoStudents=studentService.sortByBirthDate();

        if(dtoStudents!=null){
            return ok(dtoStudents);
        }
        else{
            return error(dtoStudents);
        }
    }

    @GetMapping(path="/count")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RootEntity<Integer> getNumberOfTotalStudents() {
        Integer totalNumber=studentService.getNumberOfTotalStudents();

        if(totalNumber!=null){
            return ok(totalNumber);
        }
        else{
            return error(totalNumber);
        }

    }

    @Override
    @GetMapping(path="/get-student/{card_id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<DtoStudentResponse> getStudentByCardId(@PathVariable(name="card_id") Long cardId) {
        DtoStudentResponse dtoStudent=studentService.getStudentByCardId(cardId).get();

        if(dtoStudent!=null){
            return ok(dtoStudent);
        }else{
            return error(dtoStudent);
        }
    }

    @PostMapping(path = "/save")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RootEntity<DtoStudentResponse> saveStudent(@RequestBody @Valid DtoStudentRequest student) {
        DtoStudentResponse dtoStudent= studentService.saveStudent(student);

        if(dtoStudent!=null){
            return ok(dtoStudent);
        }
        else{
            return error(dtoStudent);
        }

    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    @Override
    public RootEntity<DtoStudentResponse> findById(@PathVariable(name="id") Long id) {
        DtoStudentResponse dtoStudent=studentService.findById(id).get();

        if(dtoStudent!=null){
            return ok(dtoStudent);
        }else{
            return error(dtoStudent);
        }

    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('STUDENT')")
    @Override
    public RootEntity<DtoStudentResponse> getStudentProfile(Authentication authentication){
        String username= authentication.getName();
        DtoStudentResponse student=studentService.findByUsername(username).get();

        if(student!=null){
            return ok(student);
        }
        else{
            return error(student);
        }

    }

    @Override
    @GetMapping("/list/byDepartment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public RootEntity<List<DtoStudentResponse>> getStudentsByDepartment(@PathVariable(name="id") Long id) {
        List<DtoStudentResponse> dtoStudents=studentService.getStudentsByDepartment(id);

        if(dtoStudents!=null){
            return ok(dtoStudents);
        }else{
            return error(dtoStudents);
        }
    }
}
