package com.model.column;

import com.model.datatype.Image;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImageColumn extends Column implements IColumn<Image> {
    private Image image;

    public ImageColumn(String name, ColumnType columnType) {
        super(name);
        setType(columnType);
    }

    @Override
    public Image getDefaultData() {
        return this.image;
    }

    @Override
    public void setDefaultData(String str) {
        setImage(Image.builder().img(str.getBytes()).build());
    }

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public boolean checkConstraint(Object data) {
        Common.checkExist(data);
        return Common.checkType(data.getClass(), Image.class);
    }

    @Override
    public Object handleCreateData(String data, String colName) {
        return new Image(colName, data.getBytes());
    }

}
