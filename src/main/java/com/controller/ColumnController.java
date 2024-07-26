package com.controller;

import com.model.SmartList;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.service.MicrosoftListService;
import com.service.SmartListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    private MicrosoftListService mls;
    private SmartListService sls;


}
