package com.controller;

import com.service.ControllerService;
import com.view.MicrosoftListDTO;
import com.view.SmartListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/microsoft-lists")
public class MicrosoftListController {

    private final ControllerService controllerService;

    @Autowired
    public MicrosoftListController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @GetMapping
    public ResponseEntity<MicrosoftListDTO> getMicrosoftList() {
        MicrosoftListDTO dto = controllerService.getMicrosoftList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("lists")
    public ResponseEntity<SmartListDTO> createList(@RequestBody String name) {
        SmartListDTO dto = controllerService.createList(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("favourite-lists")
    public ResponseEntity<MicrosoftListDTO> addFavouriteList(@RequestBody String name) {
        MicrosoftListDTO dto = controllerService.addFavouriteList(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
