package com.hediyesilaozyurt.dto.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@UtilityClass
public class PageUtil {

    public Pageable toPageAble(RestPageableRequest pageableRequest){
        Sort sort=pageableRequest.isAsc()?
                Sort.by(Sort.Direction.ASC, pageableRequest.getColumnName()):
                Sort.by(Sort.Direction.DESC, pageableRequest.getColumnName());

        Pageable pageable= PageRequest.of(pageableRequest.getPageNumber(),
                pageableRequest.getPageSize(),
                sort);

        return pageable;
    }

    public <T> RestPageableResponse<T> toPageableResponse(Page<?> page, List<T> content){
        RestPageableResponse<T> pageableResponse=new RestPageableResponse<>();
        pageableResponse.setContent(content);
        pageableResponse.setPageNumber(page.getPageable().getPageNumber());
        pageableResponse.setPageSize(page.getPageable().getPageSize());
        pageableResponse.setTotalElements(page.getTotalElements());

        return pageableResponse;
    }
}
