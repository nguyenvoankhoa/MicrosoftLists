package com.service;

import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.model.Template;
import com.model.column.IColumn;
import com.util.Common;
import com.util.ConfigLoader;
import com.dto.MicrosoftListDTO;
import com.dto.RowDTO;
import com.dto.SmartListDTO;
import com.dto.mapper.ModelMapper;
import com.dto.request.*;
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
    private MicrosoftListService microsoftListService;
    private SmartListService smartListService;
    private ModelMapper mapper;
    private MicrosoftList microsoftList;
    private String listPath;
    private JsonService jsonService;

    @Autowired
    public ControllerService(MicrosoftListService microsoftListService, SmartListService smartListService, JsonService jsonService, ModelMapper mapper) {
        this.mapper = mapper;
        this.microsoftListService = microsoftListService;
        this.smartListService = smartListService;
        this.jsonService = jsonService;
        try {
            listPath = ConfigLoader.loadProperties("list.file.name");
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
        jsonService.saveToJson(ml, listPath);
        return mapper.mapMicrosoftList(ml);
    }


    public SmartListDTO createList(String name) {
        SmartList sl = microsoftListService.createList(microsoftList, name);
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }


    public Object addColumn(AddColumnRequest addReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, addReq.getListName());
        IColumn<?> column = smartListService.createNewColumn(sl, addReq.getColType(), addReq.getColName());
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.getColumnToDTOMapper().map(column);
    }


    public List<Object> getFilters(ColumnRequest colReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, colReq.getListName());
        return Common.getListFilter(sl, colReq.getColName());
    }

    public List<RowDTO> filterByColumn(FilterRequest filterReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, filterReq.getListName());
        List<Row> rows = Common.filter(sl, filterReq.getColName(), filterReq.getFilter());
        return rows.stream().map(mapper::mapRow).toList();
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
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO moveRight(ColumnRequest colReq) {
        SmartList sl = microsoftListService.getListByName(microsoftList, colReq.getListName());
        sl = smartListService.moveRight(sl, colReq.getColName());
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO addSingleData(AddSingleDataRequest request) {
        SmartList sl = microsoftListService.getListByName(microsoftList, request.getListName());
        sl = smartListService.addDataSimple(sl, request.getColName(), request.getRowId(), request.getData());
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO sortWithPaging(SmartList sl, String order, String colName) {
        sl = "asc".equalsIgnoreCase(order) ? Common.sortAsc(sl, colName) : Common.sortDesc(sl, colName);
        return mapper.mapSmartList(sl);
    }

    public SmartList getPage(String listName, int pageNumber, int pageSize) {
        SmartList sl = microsoftListService.getListByName(microsoftList, listName);
        int fromIndex = pageNumber * pageSize;
        List<Row> rows = sl.getRows().stream()
                .skip(fromIndex)
                .limit(pageSize)
                .toList();
        sl.setRows(rows);
        return sl;
    }

    public SmartListDTO getSortedAndPagedSmartList(String listName, String sortBy, String order, int pageNum, int pageSize) {
        SmartList list = getPage(listName, pageNum, pageSize);
        if (sortBy != null && !sortBy.isEmpty() && order != null && !order.isEmpty()) {
            return sortWithPaging(list, order, sortBy);
        }
        return mapper.mapSmartList(list);
    }

    public SmartListDTO createView(CreateViewRequest request) {
        SmartList sl = microsoftListService.getListByName(microsoftList, request.getListName());
        smartListService.createView(sl, request.getViewType(), request.getData());
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }

    public SmartListDTO addRowData(RowDataRequest request) {
        SmartList sl = microsoftListService.getListByName(microsoftList, request.getListName());
        Map<String, Object> mData = new HashMap<>();
        for (AddDataRequest req : request.getRowData()) {
            mData.put(req.getColName(), req.getData());
        }
        sl = smartListService.addRowData(sl, mData);
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }


    public SmartListDTO removeColumn(ColumnRequest cr) {
        SmartList sl = microsoftListService.getListByName(microsoftList, cr.getListName());
        sl = smartListService.removeColumn(sl, cr.getColName());
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }


    public SmartListDTO createListFromTemplate(TemplateToListRequest request) {
        Template t = microsoftListService.getTemplateByName(microsoftList, request.getTemplateName());
        SmartList sl = microsoftListService.createListFromTemplate(microsoftList, t, request.getListName());
        jsonService.saveToJson(microsoftList, listPath);
        return mapper.mapSmartList(sl);
    }
}
