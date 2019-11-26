package com.wee.test.shop.web.rest;

import com.wee.test.shop.domain.Shop;
import com.wee.test.shop.dto.ReportDto;
import com.wee.test.shop.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Api("Shops operations.")
@RestController
@RequestMapping("/wee/api/v1/shops")
public class ShopController {

    @Autowired
    private ShopService service;

    @ApiOperation("Returns a shop to identifier.")
    @GetMapping("/id/{id}")
    public ResponseEntity<Shop> getById(
            @ApiParam(name = "id", value = "The identifier of shop.")
            @RequestParam String id) {

        final Optional<Shop> shop = service.find(id);

        if(!shop.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shop.get(), HttpStatus.OK);
    }

    @ApiOperation("Return all shops.")
    @GetMapping
    public ResponseEntity<List<Shop>> list(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation("Creates a shop.")
    @PostMapping
    public ResponseEntity<Shop> create(@RequestBody Shop shop) {
        return new ResponseEntity<>(service.save(shop), HttpStatus.CREATED);
    }

    @ApiOperation("Updates a shop.")
    @PutMapping("/id/{id}")
    public ResponseEntity<Shop> update(
            @ApiParam(name = "id", value = "The identifier of shop.")
            @RequestParam String id,
            @RequestBody Shop shop) {

        return new ResponseEntity<>(service.update(id, shop), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a shop.")
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@RequestParam String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Shows a quantity of items by shop. ")
    @GetMapping(path = "/report/{id}")
    public ResponseEntity<ReportDto> report(
            @ApiParam(name = "id", value = "Shop identifier")
            @PathVariable("id") String id) {

        if(!service.find(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.report(id), HttpStatus.OK);
    }

}
