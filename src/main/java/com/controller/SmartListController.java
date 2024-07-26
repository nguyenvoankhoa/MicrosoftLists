package com.controller;

import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
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
        sls = new SmartListService(sl);
        List<Object> filters = Common.getListFilter(sl, colName);
        return ResponseEntity.ok(filters);
    }


    @PostMapping
    public ResponseEntity addRowData(@RequestBody List<RowDataRequest> requests) {
         // add data to list by row
    }


}
