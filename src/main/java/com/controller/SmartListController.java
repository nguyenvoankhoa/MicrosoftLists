package com.controller;

import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.service.JsonService;
import com.service.MicrosoftListService;
import com.service.SmartListService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public ResponseEntity addRowData(@RequestBody List<>){

    }
}
