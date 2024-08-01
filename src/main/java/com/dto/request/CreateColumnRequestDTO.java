package com.dto.request;

import com.model.column.ColumnType;
import lombok.Data;


@Data
public class CreateColumnRequestDTO extends BaseRequest {
    String colName;
    ColumnType colType;
    String data = null;
}
