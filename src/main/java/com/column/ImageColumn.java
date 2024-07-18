package com.column;

import com.column.datatype.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageColumn extends Column implements IColumn<Image> {
    private Image image;

    public ImageColumn(String name) {
        super(name);
        setType(ColumnType.IMAGE);
    }

    @Override
    public Image getDefaultData() {
        return this.image;
    }
}
