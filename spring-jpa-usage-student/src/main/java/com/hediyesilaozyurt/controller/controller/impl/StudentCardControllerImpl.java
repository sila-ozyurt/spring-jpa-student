package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.IStudentCardController;
import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.IStudentCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RootEntity<List<DtoStudentCard>> list() {
        List<DtoStudentCard> dtoStudentCards=studentCardService.list();

        if(dtoStudentCards!=null){
            return ok(dtoStudentCards);
        }
        else{
            return error(dtoStudentCards);
        }
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<Optional<DtoStudentCard>> findById(@PathVariable(name = "id") Long id) {
        Optional<DtoStudentCard> optionalDtoStudentCard=studentCardService.findById(id);

        if(optionalDtoStudentCard.isPresent()){
            return ok(optionalDtoStudentCard);
        }
        else{
            return error(optionalDtoStudentCard);
        }
    }

    @Override
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoStudentCard> save(@RequestBody @Valid DtoStudentCard dtoStudentCard) {
        DtoStudentCard studentCard=studentCardService.save(dtoStudentCard);

        if(studentCard!=null){
            return ok(studentCard);
        }
        else{
            return error(studentCard);
        }
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