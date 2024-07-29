package com.view.data;
import com.model.column.ColumnType;
import com.model.datatype.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private byte[] img;
    private int size;
    private ColumnType type;

    public ImageDTO(Image image) {
        this.img = image.getImg();
        this.size = image.getSize();
        this.type = image.getType();
    }
}
