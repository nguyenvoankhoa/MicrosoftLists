package com.controller;

import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.view.ViewType;
import com.payload.request.*;
import com.service.*;
import com.service.controller.ControllerService;
import com.view.RowDTO;
import com.view.SmartListDTO;
import com.view.mapper.ColumnToDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/smart-lists")
public class SmartListController {
    private ControllerService cs;
    private MicrosoftList ml;
    private static final Logger LOGGER = Logger.getLogger(SmartListController.class.getName());

    public SmartListController(ControllerService cs) {
        JsonService js = new JsonService();
        try {
            String listPath = ConfigService.loadProperties("list.file.name");
            ml = js.loadListsFromJson(listPath);
            MicrosoftListService mls = new MicrosoftListService();
            SmartListService sls = new SmartListService();
            cs = new ControllerService(mls, sls);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load MicrosoftList to JSON file ", e);
        }
        this.cs = cs;
    }

    @PostMapping("column")
    public ResponseEntity<?> addColumn(@RequestBody AddColumnRequest addReq) {
        IColumn<?> col = cs.addColumn(ml, addReq);
        var column = ColumnToDTOMapper.map(col);
        return new ResponseEntity<>(column, HttpStatus.OK);
    }


    @GetMapping("filters")
    public ResponseEntity<List<Object>> getColumnFilters(@RequestBody ColumnRequest cr) {
        List<Object> filters = cs.getFilters(ml, cr);
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }


    @GetMapping("filter-by")
    public ResponseEntity<List<RowDTO>> filterColumn(@RequestBody FilterRequest fr) {
        List<Row> rows = cs.filterByColumn(ml, fr);
        var listRow = rows.stream().map(RowDTO::new).toList();
        return new ResponseEntity<>(listRow, HttpStatus.OK);
    }

    @GetMapping("group-by")
    public ResponseEntity groupByColumn(@RequestBody ColumnRequest cr) {
        Map<Object, List<Row>> groupedRows = cs.groupByColumn(ml, cr);
        return new ResponseEntity<>(groupedRows, HttpStatus.OK);
    }

    @GetMapping("count")
    public ResponseEntity<Long> countColumn(@RequestBody ColumnRequest cr) {
        long colNum = cs.countByColumn(ml, cr);
        return new ResponseEntity<>(colNum, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getListSorted(@RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
                                        @RequestParam(name = "order", defaultValue = "asc") String order,
                                        @RequestParam(name = "listName") String listName,
                                        @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        var list = cs.sort(ml, listName, order, sortBy);
        list.setRows(Common.getPage(list, pageNum, pageSize));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PutMapping("move-left")
    public ResponseEntity<SmartListDTO> moveLeftColumn(@RequestBody ColumnRequest cr) {
        SmartList sl = cs.moveLeft(ml, cr);
        var listDto = new SmartListDTO(sl);
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }

    @PutMapping("move-right")
    public ResponseEntity<SmartListDTO> moveRightColumn(@RequestBody ColumnRequest cr) {
        SmartList sl = cs.moveRight(ml, cr);
        var listDto = new SmartListDTO(sl);
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }

    @PostMapping("row-data")
    public ResponseEntity addRowData(@RequestBody List<RowDataRequest> requests) {
        // add data to list by row
        return null;
    }

    @PostMapping("single-data")
    public ResponseEntity addSingleData(@RequestBody AddSingleDataRequest request) {
        SmartList sl = cs.addSingleData(ml, request);
        return new ResponseEntity<>(sl, HttpStatus.OK);
    }

    @GetMapping("/column-type")
    public ResponseEntity<ColumnType[]> getColumnTypes() {
        ColumnType[] columnTypes = ColumnType.values();
        return new ResponseEntity<>(columnTypes, HttpStatus.OK);
    }


    @GetMapping("/view-type")
    public ResponseEntity<ViewType[]> getViewTypes() {
        ViewType[] viewTypes = ViewType.values();
        return new ResponseEntity<>(viewTypes, HttpStatus.OK);
    }

    @PostMapping("views")
    public ResponseEntity createView(){

    }

}
