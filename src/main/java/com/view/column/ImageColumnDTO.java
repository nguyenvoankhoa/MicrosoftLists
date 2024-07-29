package com.view.column;

import com.model.column.ColumnType;
import com.model.column.ImageColumn;
import com.model.datatype.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageColumnDTO {
    private Image image;
    private ColumnType type;

    public ImageColumnDTO(ImageColumn column) {
        this.image = column.getDefaultData();
        this.type = column.getColumnType();
    }
}

