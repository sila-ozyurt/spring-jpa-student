package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;

public class RestBaseController {

    public <T> RootEntity<T> ok(T data){
        return RootEntity.ok(data);
    }

    public <T> RootEntity<T> error(T data){
        return RootEntity.error(data);
    }
}
