package com.hediyesilaozyurt.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    VALIDATION_FAILED,
    DATABASE_CONSTRAINT,
    ENTITY_NOT_FOUND,
    INVALID_ARGUMENT_TYPE,
    INTERNAL_SERVER_ERROR;
}
