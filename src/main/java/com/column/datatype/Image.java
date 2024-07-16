package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Image implements Data<Image>{
    private byte[] image;

    @Override
    public Image getData() {
        return this;
    }

    @Override
    public void setData(Image data) {
        setImage(data.getImage());
    }
}
