package com.service;

import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.model.column.IColumn;
import com.payload.request.*;
import com.view.MicrosoftListDTO;
import com.view.RowDTO;
import com.view.SmartListDTO;
import com.view.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ControllerService {
    private static final Logger LOGGER = Logger.getLogger(ControllerService.class.getName());
    private final MicrosoftListService microsoftListService;
    private final SmartListService smartListService;
    private ModelMapper mapper;
    private MicrosoftList microsoftList;

    @Autowired
    public ControllerService(MicrosoftListService microsoftListService, SmartListService smartListService, JsonService jsonService) {
        this.mapper = new ModelMapper();
        this.microsoftListService = microsoftListService;
        this.smartListService = smartListService;

        try {
            String listPath = ConfigService.loadProperties("list.file.name");
            this.microsoftList = jsonService.loadListsFromJson(listPath);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load MicrosoftList from JSON file ", e);
        }
    }

    public MicrosoftListDTO getMicrosoftList() {
        return mapper.mapMicrosoftList(microsoftList);
    }

    public MicrosoftListDTO addFavouriteList(String name) {
        MicrosoftList ml = microsoftListService.addFavourite(microsoftList, name);
        return mapper.mapMicrosoftList(ml);
    }


    public SmartListDTO createList(String name) {
        SmartList sl = microsoftListService.createList(microsoftList, name);
        return mapper.mapSmartList(sl);
    }


    public IColumn addColumn(AddColumnRequest addReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, addReq.getListName());
        return smartListService.createNewColumn(sl, addReq.getColType(), addReq.getColName());
    }


    public List<Object> getFilters(ColumnRequest colReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, colReq.getListName());
        return Common.getListFilter(sl, colReq.getColName());
    }

    public List<RowDTO> filterByColumn(FilterRequest filterReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, filterReq.getListName());
        List<Row> rows = Common.filter(sl, filterReq.getColName(), filterReq.getFilter());
        return rows.stream().map(r -> mapper.mapRow(r)).toList();
    }

    public Map<Object, List<Row>> groupByColumn(ColumnRequest colReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, colReq.getListName());
        return Common.groupBy(sl, colReq.getColName());
    }

    public long countByColumn(ColumnRequest colReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, colReq.getListName());
        return Common.count(sl, colReq.getColName());
    }

    public SmartListDTO moveLeft(ColumnRequest colReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, colReq.getListName());
        sl = smartListService.moveLeft(sl, colReq.getColName());
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO moveRight(ColumnRequest colReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, colReq.getListName());
        sl = smartListService.moveRight(sl, colReq.getColName());
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO addSingleData(AddSingleDataRequest request) {
        SmartList sl = microsoftListService.getListByName(microsoftList, request.getListName());
        sl = smartListService.addDataSimple(sl, request.getColName(), request.getRowId(), request.getData());
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO sort(String listName, String order, String colName) {
        SmartList sl = microsoftListService.getListByName(microsoftList, listName);
        sl = "asc".equalsIgnoreCase(order) ? Common.sortAsc(sl, colName) : Common.sortDesc(sl, colName);
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO getPage(SmartListDTO sl, int pageNumber, int pageSize) {
        int fromIndex = pageNumber * pageSize;
        List<RowDTO> rows = sl.getRows().stream()
                .skip(fromIndex)
                .limit(pageSize)
                .toList();
        sl.setRows(rows);
        return sl;
    }

    public SmartListDTO createView(CreateViewRequest request) {
        SmartList sl = microsoftListService.getListByName(microsoftList, request.getListName());
        smartListService.createView(sl, request.getViewType(), request.getData());
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO addRowData(RowDataRequest request) {
        SmartList sl = microsoftListService.getListByName(microsoftList, request.getListName());
        Map<String, Object> mData = new HashMap<>();
        for (AddDataRequest req : request.getRowData()) {
            mData.put(req.getColName(), req.getData());
        }
        sl = smartListService.addRowData(sl, mData);
        return mapper.mapSmartList(sl);
    }


    public SmartListDTO removeColumn(ColumnRequest cr) {
        SmartList sl = microsoftListService.getListByName(microsoftList, cr.getListName());
        sl = smartListService.removeColumn(sl, cr.getColName());
        return mapper.mapSmartList(sl);
    }


}
