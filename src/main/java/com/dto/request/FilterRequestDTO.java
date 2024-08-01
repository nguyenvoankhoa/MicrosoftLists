package com.dto.request;

import lombok.Data;

@Data
public class FilterRequestDTO extends ColumnRequestDTO {
    Object filter;
}
