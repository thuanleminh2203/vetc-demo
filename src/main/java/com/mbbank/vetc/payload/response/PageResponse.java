package com.mbbank.vetc.payload.response;

import com.mbbank.vetc.dto.PageDTO;
import lombok.Data;

@Data
public class PageResponse<T> {
    private PageDTO page;
    private T result;
}
