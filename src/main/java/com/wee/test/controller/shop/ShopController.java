package com.wee.test.controller.shop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Shop CRUD controller")
@RestController
public class ShopController {

    @Autowired
    private ShopRepository repository;

    @Autowired
    private ShopService service;

    @ApiOperation("Return all shops")
    @RequestMapping(path = "/data", method = RequestMethod.GET)
    public List<Shop> get(@RequestParam(required = false) String id) {
        if (id == null) {
            return repository.findAll();
        }

        var shop = repository.findById(id)
            .get();

        return List.of(shop);
    }

    @RequestMapping(path = "/data", method = RequestMethod.POST)
    public Shop updateCreate(@RequestParam(required = false) String id, @RequestBody Shop shop) {
        if (id == null) {
            return repository.save(shop);
        }

        var persisted = repository.findById(id)
            .get();

        persisted.setName(shop.getName());

        return repository.save(shop);
    }

    @RequestMapping(path = "/data/delete", method = RequestMethod.POST)
    public Shop delete(@RequestParam String id) {
        var shop = repository.findById(id)
            .get();

        repository.delete(shop);

        return repository.save(shop);
    }

    @RequestMapping(path = "/report", method = RequestMethod.POST)
    public String report(@RequestParam String id) {
        return service.report(id);
    }
}
