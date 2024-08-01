package com.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RowDataRequest extends BaseRequest {
    List<AddDataRequestDTO> rowData;
}
