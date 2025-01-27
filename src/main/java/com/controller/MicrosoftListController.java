package com.controller;

import com.dto.request.TemplateToListRequestDTO;
import com.service.ControllerService;
import com.dto.MicrosoftListDTO;
import com.dto.SmartListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping("lists/blank")
    public ResponseEntity<SmartListDTO> createBlankList(@RequestBody String name) {
        SmartListDTO dto = controllerService.createList(name);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("lists/from-template")
    public ResponseEntity<SmartListDTO> createListFromTemplate(@RequestBody TemplateToListRequestDTO request) {
        SmartListDTO dto = controllerService.createListFromTemplate(request);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("lists/favourites")
    public ResponseEntity<MicrosoftListDTO> addFavouriteList(@RequestBody String name) {
        MicrosoftListDTO dto = controllerService.addFavouriteList(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("lists/save-template")
    public ResponseEntity<MicrosoftListDTO> saveListAsTemplate(@RequestBody TemplateToListRequestDTO request){
        MicrosoftListDTO dto = controllerService.saveListToTemplate(request);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("lists")
    public ResponseEntity<List<SmartListDTO>> getAllLists(){
        List<SmartListDTO> dto = controllerService.getAllLists();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
