package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Setter
@Getter
public class Image implements Data<Image>, Comparator<Image> {
    private byte[] image;
    private int size;

    @Override
    public Image getData() {
        return this;
    }

    @Override
    public void setData(Image data) {
        setImage(data.getImage());
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
