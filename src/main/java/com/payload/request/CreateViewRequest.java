package com.payload.request;

import com.model.view.ViewType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateViewRequest extends BaseRequest {
    ViewType viewType;
    Object[] data;
}
