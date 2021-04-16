package com.waracle.cakemanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager.model.CakeEntity;
import com.waracle.cakemanager.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CakeController {

    Logger logger = LoggerFactory.getLogger(CakeController.class);


    @Autowired
    CakeService cakeService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(
            value = "/cakes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CakeEntity> createCake(@RequestBody CakeEntity cake) {

        cakeService.createCake(cake);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/cakes")
    public ResponseEntity<List<CakeEntity>> cakes() {

        return new ResponseEntity<>(cakeService.findAll(),HttpStatus.OK);

    }


    @GetMapping("/")
    public ResponseEntity<String> cakeHumanReadable() {


        String cakeEntitiesHumanReadable = cakeService.findAll().stream().map(it -> {
            try {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(it);
            } catch (JsonProcessingException e) {
                logger.error("error in getting human readable cakes",e);
                return "";
            }
        })
                .collect(Collectors.joining(""));


        //via curl (and test profile) this is easily readable
        // curl http://localhost:8080
        return new ResponseEntity<>(cakeEntitiesHumanReadable,HttpStatus.OK);


    }
}


