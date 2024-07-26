package com.model.datatype;

import com.model.column.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HyperLink implements IData<HyperLink>, Comparator<HyperLink> {
    private String link;
    private ColumnType type = ColumnType.HYPERLINK;

    public HyperLink(String link) {
        this.link = link;
    }

    @Override
    public HyperLink getData() {
        return this;
    }

    @Override
    public void setData(HyperLink data) {
        setLink(data.getLink());
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
