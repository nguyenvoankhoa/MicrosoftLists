package com.dto.request;

import com.model.column.ColumnType;
import lombok.Data;


@Data
public class CreateColumnRequestDTO extends BaseRequest {
    String colName;
    ColumnType colType;
    boolean allowDefault;
    String data = null;
}
