package com.view;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartListDTO {
    String name;
    List<RowDTO> rows;
    List<Object> columns;


}
