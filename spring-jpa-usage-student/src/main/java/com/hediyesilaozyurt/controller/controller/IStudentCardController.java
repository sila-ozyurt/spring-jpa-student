package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.dto.utils.RestPageableResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface IStudentCardController {

    public RootEntity<DtoStudentCard> save(DtoStudentCard dtoStudentCard);

    public RootEntity<Optional<DtoStudentCard>> findById(Long id);

    public RootEntity<RestPageableResponse<DtoStudentCard>> findAll(RestPageableRequest pageableRequest);

    public ResponseEntity<?> delete(Long id);
}
