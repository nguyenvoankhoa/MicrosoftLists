package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO extends  BaseDataDTO {
    private String name;
    private byte[] image;
    private ColumnType type;
}

