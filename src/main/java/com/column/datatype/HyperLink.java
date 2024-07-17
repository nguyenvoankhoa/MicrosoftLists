package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Setter
@Getter
public class HyperLink implements Data<HyperLink>, Comparator<HyperLink> {
    private String link;

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
