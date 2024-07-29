package com.service.controller;

import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.model.column.IColumn;
import com.payload.request.AddColumnRequest;
import com.payload.request.AddSingleDataRequest;
import com.payload.request.ColumnRequest;
import com.payload.request.FilterRequest;
import com.service.Common;
import com.service.MicrosoftListService;
import com.service.SmartListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ControllerService {
    MicrosoftListService mls;
    SmartListService sls;

    public ControllerService(MicrosoftListService mls, SmartListService sls) {
        this.mls = mls;
        this.sls = sls;
    }

    public IColumn<?> addColumn(MicrosoftList ml, AddColumnRequest addReq) {
        SmartList sl = mls.getListByName(ml, addReq.getListName());
        sls = new SmartListService();
        return sls.createNewColumn(sl, addReq.getColType(), addReq.getColName());
    }


    public List<Object> getFilters(MicrosoftList ml, ColumnRequest colReq) {
        SmartList sl = mls.getListByName(ml, colReq.getListName());
        return Common.getListFilter(sl, colReq.getColName());
    }

    public List<Row> filterByColumn(MicrosoftList ml, FilterRequest filterReq) {
        SmartList sl = mls.getListByName(ml, filterReq.getListName());
        return Common.filter(sl, filterReq.getColName(), filterReq.getFilter());
    }

    public Map<Object, List<Row>> groupByColumn(MicrosoftList ml, ColumnRequest colReq) {
        SmartList sl = mls.getListByName(ml, colReq.getListName());
        return Common.groupBy(sl, colReq.getColName());
    }

    public long countByColumn(MicrosoftList ml, ColumnRequest colReq) {
        SmartList sl = mls.getListByName(ml, colReq.getListName());
        return Common.count(sl, colReq.getColName());
    }

    public SmartList moveLeft(MicrosoftList ml, ColumnRequest colReq) {
        SmartList sl = mls.getListByName(ml, colReq.getListName());
        sls = new SmartListService();
        return sls.moveLeft(sl, colReq.getColName());
    }

    public SmartList moveRight(MicrosoftList ml, ColumnRequest colReq) {
        SmartList sl = mls.getListByName(ml, colReq.getListName());
        sls = new SmartListService();
        return sls.moveRight(sl, colReq.getColName());
    }

    public SmartList addSingleData(MicrosoftList ml, AddSingleDataRequest request) {
        SmartList sl = mls.getListByName(ml, request.getListName());
        sls = new SmartListService();
        sls.addData(sl, request.getColName(), request.getRowId(), request.getData());
        return sl;
    }

    public SmartList sort(MicrosoftList ml, String listName, String order, String colName) {
        SmartList sl = mls.getListByName(ml, listName);
        if ("asc".equalsIgnoreCase(order)) {
            return Common.sortAsc(sl, colName);
        }
        return Common.sortDesc(sl, colName);
    }
}
