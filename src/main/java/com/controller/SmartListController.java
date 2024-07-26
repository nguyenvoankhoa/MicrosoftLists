package com.controller;

import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.view.View;
import com.model.view.ViewType;
import com.payload.request.RowDataRequest;
import com.service.Common;
import com.service.JsonService;
import com.service.MicrosoftListService;
import com.service.SmartListService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/smart-lists")
public class SmartListController {
    @Value("list.file.name")
    String mlsPath;

    MicrosoftListService mls;

    SmartListService sls;

    @PostMapping
    public ResponseEntity addColumn(ColumnType colType, String colName, String listName) {
        SmartList sl = mls.getListByName(listName);
        sls = new SmartListService(sl);
        IColumn col = sls.createNewColumn(colType, colName);
        return ResponseEntity.ok(col);
    }


    @GetMapping
    public ResponseEntity getColumnFilter(@RequestBody String colName, String listName) {
        SmartList sl = mls.getListByName(listName);
        List<Object> filters = Common.getListFilter(sl, colName);
        return ResponseEntity.ok(filters);
    }


    @GetMapping
    public ResponseEntity filterColumn(@RequestBody String colName, Object filter, String listName) {
        SmartList sl = mls.getListByName(listName);
        List<Row> filteredRows = Common.filter(sl, "Title", filter);
        return ResponseEntity.ok(filteredRows);
    }

    @GetMapping
    public ResponseEntity groupByColumn(@RequestBody String colName, String listName) {
        SmartList sl = mls.getListByName(listName);
        Map<Object, List<Row>> groupedRows = Common.groupBy(sl, colName);
        return ResponseEntity.ok(groupedRows);
    }

    @GetMapping
    public ResponseEntity countColumn(@RequestBody String colName, String listName) {
        SmartList sl = mls.getListByName(listName);
        long colNum = Common.count(sl, colName);
        return ResponseEntity.ok(colNum);
    }

    @PutMapping
    public ResponseEntity moveLeftColumn(@RequestBody String colName, String listName) {
        SmartList sl = mls.getListByName(listName);
        sls = new SmartListService(sl);
        sls.moveLeft(colName);
        return ResponseEntity.ok(sl);
    }

    @PutMapping
    public ResponseEntity moveRightColumn(@RequestBody String colName, String listName) {
        SmartList sl = mls.getListByName(listName);
        sls = new SmartListService(sl);
        sls.moveRight(colName);
        return ResponseEntity.ok(sl);
    }

    @PostMapping
    public ResponseEntity addRowData(@RequestBody List<RowDataRequest> requests) {
        // add data to list by row
        return null;
    }

}
