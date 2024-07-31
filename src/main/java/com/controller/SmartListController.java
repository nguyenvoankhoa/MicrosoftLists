package com.controller;

import com.model.column.ColumnType;
import com.model.view.ViewType;
import com.service.ControllerService;
import com.dto.RowDTO;
import com.dto.SmartListDTO;
import com.dto.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/smart-lists")
public class SmartListController {
    private final ControllerService controllerService;

    @Autowired
    public SmartListController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping
    public ResponseEntity<SmartListDTO> getSmartList(@RequestParam(name = "sortBy", required = false) String sortBy,
                                                     @RequestParam(name = "order", required = false) String order,
                                                     @RequestParam(name = "listName") String listName,
                                                     @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        SmartListDTO dto = controllerService.getSortedAndPagedSmartList(listName, sortBy, order, pageNum, pageSize);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("rows")
    public ResponseEntity<SmartListDTO> addRowData(@RequestBody RowDataRequest request) {
        SmartListDTO dto = controllerService.addRowData(request);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("rows/{id}")
    public ResponseEntity<RowDTO> getRowData(@PathVariable("id") int rowId,
                                             @RequestParam("listName") String listName) {
        RowDTO dto = controllerService.getRow(rowId, listName);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("rows/{id}")
    public ResponseEntity deleteRowData(@PathVariable("id") int rowId,
                                        @RequestParam("listName") String listName) {
        controllerService.deleteRow(rowId, listName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("data")
    public ResponseEntity<SmartListDTO> addSingleData(@RequestBody AddSingleDataRequest request) {
        SmartListDTO dto = controllerService.addSingleData(request);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("column-types")
    public ResponseEntity<ColumnType[]> getColumnTypes() {
        ColumnType[] columnTypes = ColumnType.values();
        return new ResponseEntity<>(columnTypes, HttpStatus.OK);
    }

    @GetMapping("columns")
    public ResponseEntity<Object> getColumn(@RequestParam(name = "columnName") String colName,
                                            @RequestParam(name = "listName") String listName) {
        var column = controllerService.getColumn(colName, listName);
        return new ResponseEntity<>(column, HttpStatus.OK);
    }

    @PostMapping("columns")
    public ResponseEntity<Object> createColumn(@RequestBody AddColumnRequest addReq) {
        var column = controllerService.addColumn(addReq);
        return new ResponseEntity<>(column, HttpStatus.CREATED);
    }


    @GetMapping("filters")
    public ResponseEntity<List<Object>> getColumnFilters(@RequestParam(name = "columnName") String colName,
                                                         @RequestParam(name = "listName") String listName) {
        List<Object> filters = controllerService.getFilters(colName, listName);
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }


    @PostMapping("filter")
    public ResponseEntity<List<RowDTO>> filterColumn(@RequestBody FilterRequest fr) {
        List<RowDTO> rows = controllerService.filterByColumn(fr);
        return new ResponseEntity<>(rows, HttpStatus.OK);
    }

    @PostMapping("group")
    public ResponseEntity<Map<Object, List<RowDTO>>> groupByColumn(@RequestBody ColumnRequest cr) {
        Map<Object, List<RowDTO>> groupedRows = controllerService.groupByColumn(cr);
        return new ResponseEntity<>(groupedRows, HttpStatus.OK);
    }

    @PostMapping("count")
    public ResponseEntity<Long> countColumn(@RequestBody ColumnRequest cr) {
        long colNum = controllerService.countByColumn(cr);
        return new ResponseEntity<>(colNum, HttpStatus.OK);
    }


    @PutMapping("columns/move-left")
    public ResponseEntity<SmartListDTO> moveLeftColumn(@RequestBody ColumnRequest cr) {
        SmartListDTO dto = controllerService.moveLeft(cr);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("columns/move-right")
    public ResponseEntity<SmartListDTO> moveRightColumn(@RequestBody ColumnRequest cr) {
        SmartListDTO dto = controllerService.moveRight(cr);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @DeleteMapping("columns")
    public ResponseEntity removeColumn(ColumnRequest cr) {
        controllerService.removeColumn(cr);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("view-types")
    public ResponseEntity<ViewType[]> getViewTypes() {
        ViewType[] viewTypes = ViewType.values();
        return new ResponseEntity<>(viewTypes, HttpStatus.OK);
    }

    @PostMapping("views")
    public ResponseEntity<SmartListDTO> createView(@RequestBody CreateViewRequest request) {
        SmartListDTO dto = controllerService.createView(request);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
