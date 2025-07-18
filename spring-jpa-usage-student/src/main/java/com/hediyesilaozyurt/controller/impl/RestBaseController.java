package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.entities.RootEntity;

public class RestBaseController {

    public <T> RootEntity<T> ok(T data){
        return RootEntity.ok(data);
    }
}
