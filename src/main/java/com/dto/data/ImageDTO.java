package com.dto.data;
import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO  extends  BaseDataDTO{
    private byte[] img;
    private int size;
    private ColumnType type;
}
