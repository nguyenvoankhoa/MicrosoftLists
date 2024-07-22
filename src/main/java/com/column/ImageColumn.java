package com.column;

import com.column.datatype.HyperLink;
import com.column.datatype.Image;
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
    public boolean checkConstraint(Image data) {
        Predicate<Image> requirePredicate = d -> !isRequire() || d != null;
        return requirePredicate.test(data);
    }
}
