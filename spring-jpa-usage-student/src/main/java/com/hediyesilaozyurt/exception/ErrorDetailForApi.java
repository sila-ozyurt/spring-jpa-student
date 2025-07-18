package com.hediyesilaozyurt.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetailForApi<T> {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime errorTime;

    private String hostName;

    private ErrorCode errorCode;

    private String path;

    private T errors;
}
