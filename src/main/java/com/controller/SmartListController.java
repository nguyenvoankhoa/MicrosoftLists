package com.controller;

import com.model.Row;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.view.ViewType;
import com.payload.request.*;
import com.service.ControllerService;
import com.view.RowDTO;
import com.view.SmartListDTO;
import com.view.mapper.ColumnToDTOMapper;
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

    @PostMapping("columns")
    public ResponseEntity<Object> createColumn(@RequestBody AddColumnRequest addReq) {
        IColumn<?> col = controllerService.addColumn(addReq);
        var column = ColumnToDTOMapper.map(col);
        return new ResponseEntity<>(column, HttpStatus.OK);
    }


    @GetMapping("filters")
    public ResponseEntity<List<Object>> getColumnFilters(@RequestBody ColumnRequest cr) {
        List<Object> filters = controllerService.getFilters(cr);
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }


    @GetMapping("filter-by")
    public ResponseEntity<List<RowDTO>> filterColumn(@RequestBody FilterRequest fr) {
        List<RowDTO> rows = controllerService.filterByColumn(fr);
        return new ResponseEntity<>(rows, HttpStatus.OK);
    }

    @GetMapping("group-by")
    public ResponseEntity<Map<Object, List<Row>>> groupByColumn(@RequestBody ColumnRequest cr) {
        Map<Object, List<Row>> groupedRows = controllerService.groupByColumn(cr);
        return new ResponseEntity<>(groupedRows, HttpStatus.OK);
    }

    @GetMapping("count")
    public ResponseEntity<Long> countColumn(@RequestBody ColumnRequest cr) {
        long colNum = controllerService.countByColumn(cr);
        return new ResponseEntity<>(colNum, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SmartListDTO> getListSorted(@RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
                                                      @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                                                      @RequestParam(name = "listName") String listName,
                                                      @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        SmartListDTO sortedList = controllerService.sort(listName, order, sortBy);
        sortedList = controllerService.getPage(sortedList, pageNum, pageSize);
        return new ResponseEntity<>(sortedList, HttpStatus.OK);
    }


    @PutMapping("move-left")
    public ResponseEntity<SmartListDTO> moveLeftColumn(@RequestBody ColumnRequest cr) {
        SmartListDTO dto = controllerService.moveLeft(cr);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("move-right")
    public ResponseEntity<SmartListDTO> moveRightColumn(@RequestBody ColumnRequest cr) {
        SmartListDTO dto = controllerService.moveRight(cr);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("row")
    public ResponseEntity<SmartListDTO> addRowData(@RequestBody RowDataRequest request) {
        SmartListDTO dto = controllerService.addRowData(request);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("single-data")
    public ResponseEntity<SmartListDTO> addSingleData(@RequestBody AddSingleDataRequest request) {
        SmartListDTO dto = controllerService.addSingleData(request);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("column-type")
    public ResponseEntity<ColumnType[]> getColumnTypes() {
        ColumnType[] columnTypes = ColumnType.values();
        return new ResponseEntity<>(columnTypes, HttpStatus.OK);
    }

    @DeleteMapping("columns")
    public ResponseEntity<SmartListDTO> removeColumn(ColumnRequest cr) {
        SmartListDTO dto = controllerService.removeColumn(cr);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("view-type")
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
