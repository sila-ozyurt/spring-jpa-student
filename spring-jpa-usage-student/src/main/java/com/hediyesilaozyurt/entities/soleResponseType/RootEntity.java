package com.hediyesilaozyurt.entities.soleResponseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  RootEntity <T> {

    private Integer status;

    private T payload;

    public static <T> RootEntity<T> ok(T payload){
        RootEntity<T> rootEntity=new RootEntity<>();
        rootEntity.setPayload(payload);
        rootEntity.setStatus(HttpStatus.OK.value());
        return rootEntity;
    }

    public static <T> RootEntity<T> error(T data){
        RootEntity<T> rootEntity=new RootEntity<>();
        rootEntity.setPayload(data);
        rootEntity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return rootEntity;

    }

}
