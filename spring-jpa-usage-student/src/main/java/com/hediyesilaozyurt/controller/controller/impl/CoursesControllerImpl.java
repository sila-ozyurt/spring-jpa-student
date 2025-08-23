package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.ICoursesController;
import com.hediyesilaozyurt.dto.dto.DtoCourses;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.ICoursesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/api/courses")
public class CoursesControllerImpl extends RestBaseController implements ICoursesController {

    @Autowired
    private ICoursesService coursesService;

    @Override
    @PostMapping(path="/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoCourses> save(@RequestBody @Valid DtoCourses dtoCourse) {
        DtoCourses savedCourse=coursesService.save(dtoCourse);

        return createResponse(savedCourse);
    }

    //public
    @Override
    @GetMapping(path="/list")
    public RootEntity<Page<DtoCourses>> findAll(@ModelAttribute RestPageableRequest pageableRequest) {
        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoCourses> dtoCourses=coursesService.findAll(pageable);

        return createResponse(dtoCourses);
    }

/*
    // Herkes ders detaylarını görebilir
    @GetMapping("/{id}")
    public RootEntity<Optional<DtoCourses>> findById(@PathVariable(name="id") Long id) {
        return ok(coursesService.findById(id));
    }

    // Sadece Admin ders güncelleyebilir
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoCourses> update(@PathVariable(name="id") Long id,
                                         @RequestBody @Valid DtoCourses dtoCourse) {
        return ok(coursesService.update(id, dtoCourse));
    }

    // Sadece Admin ders silebilir
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<String> delete(@PathVariable(name="id") Long id) {
        coursesService.delete(id);
        return ok("Course successfully deleted with ID: " + id);
    }

    // Aktif dersleri listeleme (herkes erişebilir)
    @GetMapping("/active")
    public RootEntity<List<DtoCourses>> getActiveCourses() {
        return ok(coursesService.findActiveCourses());
    }

    // Öğrenci kayıt olabileceği dersleri görme
    @GetMapping("/available-for-enrollment")
    @PreAuthorize("hasRole('STUDENT')")
    public RootEntity<List<DtoCourses>> getAvailableCoursesForEnrollment(Authentication authentication) {
        String username = authentication.getName();
        return ok(coursesService.getAvailableCoursesForStudent(username));
    }

    // Öğrencinin kayıtlı olduğu dersler
    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public RootEntity<List<DtoCourses>> getMyCourses(Authentication authentication) {
        String username = authentication.getName();
        return ok(coursesService.getStudentCourses(username));
    }

    // Öğrenci ders kaydı yapma
    @PostMapping("/enroll/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public RootEntity<String> enrollToCourse(@PathVariable(name="courseId") Long courseId,
                                             Authentication authentication) {
        String username = authentication.getName();
        coursesService.enrollStudentToCourse(username, courseId);
        return ok("Successfully enrolled to course with ID: " + courseId);
    }

    // Öğrenci ders kaydını iptal etme
    @DeleteMapping("/unenroll/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public RootEntity<String> unenrollFromCourse(@PathVariable(name="courseId") Long courseId,
                                                 Authentication authentication) {
        String username = authentication.getName();
        coursesService.unenrollStudentFromCourse(username, courseId);
        return ok("Successfully unenrolled from course with ID: " + courseId);
    }

    // Bölüme göre dersleri listeleme
    @GetMapping("/by-department/{departmentId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<List<DtoCourses>> getCoursesByDepartment(@PathVariable(name="departmentId") Long departmentId) {
        return ok(coursesService.getCoursesByDepartment(departmentId));
    }

    // Derse kayıtlı öğrenci sayısı (sadece admin)
    @GetMapping("/{id}/enrollment-count")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<Integer> getEnrollmentCount(@PathVariable(name="id") Long id) {
        return ok(coursesService.getEnrollmentCountByCourse(id));
    }

    // Ders durumunu güncelleme (sadece admin)
    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoCourses> updateCourseStatus(@PathVariable(name="id") Long id,
                                                     @RequestParam String status) {
        return ok(coursesService.updateCourseStatus(id, status));
    }

    // Ders kapasitesini güncelleme (sadece admin)
    @PutMapping("/capacity/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoCourses> updateCourseCapacity(@PathVariable(name="id") Long id,
                                                       @RequestParam Integer capacity) {
        return ok(coursesService.updateCourseCapacity(id, capacity));
    }

    // Popüler dersleri listeleme (en çok kayıt alan)
    @GetMapping("/popular")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<List<DtoCourses>> getPopularCourses() {
        return ok(coursesService.getPopularCourses());
    }*/
}
