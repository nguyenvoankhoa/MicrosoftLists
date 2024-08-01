package com.dto.data;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HyperLinkDTO  extends  BaseDataDTO{
    private String link;
    private ColumnType type;
}
