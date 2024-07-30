package com.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateFormRequest extends BaseRequest {
    String formName;
    List<String> columns;
}
