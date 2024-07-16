package com.column.datatype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HyperLink implements Data<HyperLink> {
    private String link;

    @Override
    public HyperLink getData() {
        return this;
    }

    @Override
    public void setData(HyperLink data) {
        setLink(data.getLink());
    }
}
