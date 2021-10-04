package com.andyleo.myRetail.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "ProductPrice")
public class PriceInfo {

    @Id
    private String id;
    @Field("productId")
    private int productId;
    @Field("price")
    private double price;
    @Field("currencyCode")
    private String currencyCode;
}
