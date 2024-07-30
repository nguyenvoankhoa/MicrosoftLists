package com.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDataRequest {
    String colName;
    Object data;
}
