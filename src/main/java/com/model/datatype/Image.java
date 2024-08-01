package com.model.datatype;

import com.model.column.ColumnType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;

@Data
@SuperBuilder
public class Image extends BaseData implements IData<Image>, Comparator<Image> {
    private byte[] img;
    private int size;
    private final ColumnType type = ColumnType.HYPERLINK;

    public Image(String colName) {
        super(colName);
        this.img = new byte[8];
    }

    public Image(String colName, byte[] img) {
        super(colName);
        this.img = img;
    }

    @Override
    public Image getData() {
        return this;
    }

    @Override
    public void setData(Image data) {
        setImg(data.getImg());
        setColName(data.getColName());
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
