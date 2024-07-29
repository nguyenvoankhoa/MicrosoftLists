package com.payload.request;

import com.model.column.ColumnType;
import lombok.Data;

@Data
public class AddColumnRequest extends BaseRequest{
    String colName;
    ColumnType colType;
}
