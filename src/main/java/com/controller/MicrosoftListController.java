package com.controller;

import com.model.MicrosoftList;
import com.model.SmartList;
import com.model.column.ColumnType;
import com.service.ConfigService;
import com.service.JsonService;
import com.service.MicrosoftListService;
import com.view.MicrosoftListDTO;
import com.view.SmartListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/microsoft-lists")
public class MicrosoftListController {
    private static final Logger LOGGER = Logger.getLogger(MicrosoftListController.class.getName());

    MicrosoftList ml;
    private MicrosoftListService mls;

    public MicrosoftListController() {
        JsonService js = new JsonService();
        try {
            String listPath = ConfigService.loadProperties("list.file.name");
            ml = js.loadListsFromJson(listPath);
            this.mls = new MicrosoftListService();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load MicrosoftList to JSON file ", e);
        }
    }

    @GetMapping
    public ResponseEntity<MicrosoftListDTO> getMicrosoftList() {
        return ResponseEntity.ok(new MicrosoftListDTO(ml));
    }

    @PostMapping("lists")
    public ResponseEntity<SmartListDTO> createList(@RequestBody String name) {
        SmartList sl = mls.createList(ml, name);
        if (sl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SmartListDTO smartListDTO = new SmartListDTO(sl);
        return new ResponseEntity<>(smartListDTO, HttpStatus.OK);
    }

    @PostMapping("favourite-lists")
    public ResponseEntity addFavouriteList(@RequestBody String name) {
        if (!mls.addFavourite(ml, name)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }





}
