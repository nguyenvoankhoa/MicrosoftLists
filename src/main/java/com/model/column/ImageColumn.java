package com.model.column;

import com.model.datatype.Image;
import com.util.Common;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

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

    @Override
    public ColumnType getColumnType() {
        return getType();
    }

    @Override
    public void checkConstraint(Object data) {
        Predicate<Image> requirePredicate = d -> !isRequire() || d != null;
        Common.checkValid(requirePredicate.test((Image) data));
    }

    @Override
    public Image createSimpleData(Object data) {
        return new Image((byte[]) data);
    }
}
