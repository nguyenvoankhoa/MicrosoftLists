package com.model;

import com.model.datatype.IData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Row {
    private List<IData> iDataList;
    private List<Comment> comments;

    public Row() {
        iDataList = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public Row addData(int cId, Object data) {
        getIDataList().get(cId).setData(data);
        return this;
    }

    public Object getData(int cId){
        return getIDataList().get(cId).getData();
    }

}
