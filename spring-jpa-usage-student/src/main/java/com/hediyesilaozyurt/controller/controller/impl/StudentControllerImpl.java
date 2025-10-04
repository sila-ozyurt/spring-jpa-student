package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.IStudentController;
import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import com.hediyesilaozyurt.dto.utils.RestPageableResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.IStudentService;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public RootEntity<RestPageableResponse<DtoStudentResponse>> searchByFirstName(@RequestParam(value="name") String name,
                                                                                  @ModelAttribute RestPageableRequest pageableRequest) {
        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoStudentResponse> dtoStudents=studentService.searchByFirstName(name,pageable);
        RestPageableResponse response=toPageableResponse(dtoStudents,dtoStudents.getContent());

        return createResponse(response);
    }

    @Override
    public RootEntity<DtoStudentResponse> findByUsername(String username) {
        return studentService.findByUsername(username)
                .map(this::ok)
                .orElseGet(()->error(null));
    }

    @Override
    @GetMapping(path="/find-student-by-course-id/{courseId}")
    @PreAuthorize(value = "hasAnyRole('STUDENT','ADMIN')")
    public RootEntity<RestPageableResponse<DtoStudentResponse>> findStudentByCourseId(@PathVariable(name="courseId") Long courseId,
                                                                      @ModelAttribute RestPageableRequest pageableRequest) {

        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoStudentResponse> dtoStudents=studentService.findStudentByCourseId(courseId,pageable);
        RestPageableResponse response=toPageableResponse(dtoStudents,dtoStudents.getContent());

        return createResponse(response);
    }

    @GetMapping(path="/list")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<RestPageableResponse<DtoStudentResponse>> findAll(@ModelAttribute RestPageableRequest pageableRequest) {

        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoStudentResponse> dtoStudents=studentService.findAll(pageable);
        RestPageableResponse response=toPageableResponse(dtoStudents,dtoStudents.getContent());

        return createResponse(response);
    }

    @PutMapping("/update/{id}")
    @Override
    @PreAuthorize("hasAnyRole('ADMIN')")
    public RootEntity<DtoStudentResponse> update(@PathVariable(name="id") Long id,
                                                 @RequestBody @Valid DtoStudentRequest student) {
        DtoStudentResponse dtoStudent=studentService.update(id,student);

        return createResponse(dtoStudent);
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
    public RootEntity<RestPageableResponse<DtoStudentResponse>> sortByBirthDate(@ModelAttribute RestPageableRequest pageableRequest) {

        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoStudentResponse> dtoStudents=studentService.sortByBirthDate(pageable);
        RestPageableResponse response=toPageableResponse(dtoStudents,dtoStudents.getContent());

        return createResponse(response);
    }

    @GetMapping(path="/count")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RootEntity<Integer> getNumberOfTotalStudents() {
        Integer totalNumber=studentService.getNumberOfTotalStudents();

        return createResponse(totalNumber);
    }

    @Override
    @GetMapping(path="/get-student/{card_id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<DtoStudentResponse> getStudentByCardId(@PathVariable(name="card_id") Long cardId) {
        DtoStudentResponse dtoStudent=studentService.getStudentByCardId(cardId).get();

        return createResponse(dtoStudent);
    }

    @PostMapping(path = "/save")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RootEntity<DtoStudentResponse> saveStudent(@RequestBody @Valid DtoStudentRequest student) {
        DtoStudentResponse dtoStudent= studentService.saveStudent(student);

        return createResponse(dtoStudent);
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    @Override
    public RootEntity<DtoStudentResponse> findById(@PathVariable(name="id") Long id) {
        DtoStudentResponse dtoStudent=studentService.findById(id).get();

        return createResponse(dtoStudent);
    }


    @Override
    @GetMapping("/list/byDepartment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public RootEntity<RestPageableResponse<DtoStudentResponse>> getStudentsByDepartment(@PathVariable(name="id") Long id,
                                                                        @ModelAttribute RestPageableRequest pageableRequest) {
        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoStudentResponse> dtoStudents=studentService.getStudentsByDepartment(id,pageable);
        RestPageableResponse response=toPageableResponse(dtoStudents,dtoStudents.getContent());

        return createResponse(response);
    }
}
