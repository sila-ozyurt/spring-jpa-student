package com.hediyesilaozyurt.dto.utils;

import lombok.Data;

import java.util.List;

@Data
public class RestPageableResponse<T> {

    private List<T> content;

    private int pageNumber;

    private int pageSize;

    private long totalElements;
}
