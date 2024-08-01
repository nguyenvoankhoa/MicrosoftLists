package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartListDTO {
    String name;
    List<Object> columns;
    List<RowDTO> rows;
}
