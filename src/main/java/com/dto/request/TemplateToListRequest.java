package com.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateToListRequest extends BaseRequest{
    String templateName;
}
