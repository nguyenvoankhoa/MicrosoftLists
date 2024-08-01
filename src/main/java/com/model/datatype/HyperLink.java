package com.model.datatype;

import com.model.column.ColumnType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;

@Setter
@Getter
@SuperBuilder
public class HyperLink extends BaseData implements IData<HyperLink>, Comparator<HyperLink> {
    private String link;
    private String displayText;
    private final ColumnType type = ColumnType.HYPERLINK;

    public HyperLink(String colName, String... args) {
        super(colName);
        this.link = args[0];
        this.displayText = args[1];
    }

    @Override
    public HyperLink getData() {
        return this;
    }

    @Override
    public void setData(HyperLink data) {
        setLink(data.getLink());
        setColName(data.getColName());
    }

    @Override
    public Object getImportantData() {
        return this.link;
    }


    @Override
    public int compare(HyperLink o1, HyperLink o2) {
        return o2.getLink().compareTo(o1.getLink());
    }
}
