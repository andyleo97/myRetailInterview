package com.andyleo.myRetail.model;

import lombok.Data;

@Data
public class Product {

    private int id;

    private String title;

    private PriceInfo priceInfo;
}
