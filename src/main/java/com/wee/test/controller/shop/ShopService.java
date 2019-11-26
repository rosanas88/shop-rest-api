package com.wee.test.controller.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ShopRepository repository;

    public String report(String shopId) {
        return "{}";
    }
}
