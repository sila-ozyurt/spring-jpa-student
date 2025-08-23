package com.hediyesilaozyurt.entities.soleResponseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  RootEntity <T> {

    private boolean result;

    private T data;

    public static <T> RootEntity<T> ok(T data){
        RootEntity<T> rootEntity=new RootEntity<>();
        rootEntity.setData(data);
        rootEntity.setResult(true);
        return rootEntity;
    }

    public static <T> RootEntity<T> error(T data){
        RootEntity<T> rootEntity=new RootEntity<>();
        rootEntity.setData(data);
        rootEntity.setResult(false);
        return rootEntity;

    }

}
