package com.dto.column;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseColumDTO {
    String name;
    boolean isRequire;
    ColumnType type;
}
