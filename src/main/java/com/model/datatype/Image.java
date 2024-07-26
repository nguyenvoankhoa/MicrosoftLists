package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Setter
@Getter
public class Image implements IData<Image>, Comparator<Image> {
    private byte[] img;
    private int size;
    private ColumnType type = ColumnType.HYPERLINK;

    @Override
    public Image getData() {
        return this;
    }

    @Override
    public void setData(Image data) {
        setImg(data.getImg());
    }

    @Override
    public Object getImportantData() {
        return this.size;
    }

    @Override
    public int compare(Image o1, Image o2) {
        return Comparator.comparing(Image::getSize)
                .compare(o2, o1);
    }
}