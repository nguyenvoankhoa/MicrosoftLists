package com.controller;

import com.model.SmartList;
import com.service.MicrosoftListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/microsoft-lists")
public class MicrosoftListController {
    MicrosoftListService mls;

    @PostMapping
    public ResponseEntity<SmartList> createList(@RequestBody String name) {
        SmartList sl = mls.createList(name);
        if (sl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sl);
    }

    @PostMapping()
    public ResponseEntity addFavouriteList(@RequestBody String name) {
        if (!mls.addFavourite(name)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(null);
    }
}
