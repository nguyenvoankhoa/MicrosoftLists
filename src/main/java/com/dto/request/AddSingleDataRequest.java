package com.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSingleDataRequest extends BaseRequest{
    String colName;
    int rowId;
    Object data;
}
