package com.dto.request;

import lombok.Data;

@Data
public class FilterRequest extends ColumnRequest{
    Object filter;
}