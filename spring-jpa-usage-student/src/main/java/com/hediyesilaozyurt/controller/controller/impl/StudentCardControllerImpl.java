package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.IStudentCardController;
import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.dto.utils.RestPageableResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.IStudentCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/api/student-card")
public class StudentCardControllerImpl extends RestBaseController implements IStudentCardController {

    @Autowired
    private IStudentCardService studentCardService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public RootEntity<RestPageableResponse<DtoStudentCard>> findAll(@ModelAttribute RestPageableRequest pageableRequest) {
        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoStudentCard> dtoStudentCards=studentCardService.findAll(pageable);
        RestPageableResponse response=toPageableResponse(dtoStudentCards,dtoStudentCards.getContent());

        return createResponse(response);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<Optional<DtoStudentCard>> findById(@PathVariable(name = "id") Long id) {
        Optional<DtoStudentCard> optionalDtoStudentCard=studentCardService.findById(id);

        return createResponse(optionalDtoStudentCard);
    }

    @Override
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoStudentCard> save(@RequestBody @Valid DtoStudentCard dtoStudentCard) {
        DtoStudentCard studentCard=studentCardService.save(dtoStudentCard);

        return createResponse(studentCard);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (!studentCardService.existById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentCardService.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully deleted");
        response.put("deletedId", id);

        return ResponseEntity.ok(response);
    }
}