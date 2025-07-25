package com.hediyesilaozyurt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError<T> {

    private Integer status;

    private ErrorDetailForApi<T> exception;

}
