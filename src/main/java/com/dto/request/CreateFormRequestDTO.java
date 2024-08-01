package com.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateFormRequestDTO extends BaseRequest {
    String formName;
    List<String> columns;
}
