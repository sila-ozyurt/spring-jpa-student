package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.dto.utils.PageUtil;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.dto.utils.RestPageableResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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
        return PageUtil.toPageAble(pageableRequest);
    }

    protected <T> RestPageableResponse<T> toPageableResponse(Page<?> page, List<T> content){
        return PageUtil.toPageableResponse(page,content);
    }
}

