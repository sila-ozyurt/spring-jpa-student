package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class RestBaseController {

    protected <T> RootEntity<T> ok(T data){
        return RootEntity.ok(data);
    }

    protected <T> RootEntity<T> error(T data){
        return RootEntity.error(data);
    }

    protected <T> RootEntity<T> createResponse(T data){
        return data!=null? RootEntity.ok(data):RootEntity.error(data);
    }

    protected Pageable toPageAble(RestPageableRequest pageableRequest){
        Sort sort=pageableRequest.isAsc()?
                Sort.by(Sort.Direction.ASC, pageableRequest.getColumnName()):
                Sort.by(Sort.Direction.DESC, pageableRequest.getColumnName());

        Pageable pageable= PageRequest.of(pageableRequest.getPageNumber(),
                pageableRequest.getPageSize(),
                sort);

        return pageable;
    }
}

