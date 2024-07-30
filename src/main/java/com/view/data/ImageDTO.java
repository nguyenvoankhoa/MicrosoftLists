package com.view.data;
import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private byte[] img;
    private int size;
    private ColumnType type;
}
