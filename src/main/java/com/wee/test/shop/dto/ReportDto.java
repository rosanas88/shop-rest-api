package com.wee.test.shop.dto;

public class ReportDto {

    private  String shopName;
    private  Integer quantity;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ReportDto{" + "shopName='" + shopName + '\'' + ", quantity=" + quantity + '}';
    }
}
