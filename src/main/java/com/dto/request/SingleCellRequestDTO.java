package com.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleCellRequestDTO extends BaseRequest{
    String colName;
    int rowId;
    String data;
}
