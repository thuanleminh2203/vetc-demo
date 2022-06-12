package com.mbbank.vetc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private long pageIndex = 1;
    private long pageSize = 10;
    private long total;
}
