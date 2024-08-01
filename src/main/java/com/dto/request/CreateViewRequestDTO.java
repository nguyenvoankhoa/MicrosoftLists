package com.dto.request;

import com.model.view.ViewType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateViewRequestDTO extends BaseRequest {
    ViewType viewType;
    Object[] data;
}
