package com.andyleo.myRetail;

import com.andyleo.myRetail.model.PriceInfo;
import com.andyleo.myRetail.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class ProductTests {

    @Autowired
    private MockMvc mockMvc;

    Product product;
    
    @BeforeAll
    public void before() throws Exception {
        this.product = new Product();
        product.setId(0);
        product.setTitle("The Big Lebowski (Blu-ray)");
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.setPrice(8.99);
        priceInfo.setCurrencyCode("USD");
        priceInfo.setProductId(13860428);
        priceInfo.setId("6157ef99a9977c7b35c67266");
        product.setPriceInfo(priceInfo);
        mockMvc.perform(put("/api/product/13860428").contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().writeValueAsString(product))).andDo(print()).andReturn();
    }

    @Test
    public void PutProductTest() throws Exception {
        assert product.getPriceInfo().getPrice() == 8.99;
        product.getPriceInfo().setPrice(3.99);
        MvcResult putResult = mockMvc.perform(put("/api/product/13860428").contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().writeValueAsString(product))).andDo(print()).andReturn();
        String json = putResult.getResponse().getContentAsString();
        Product product1 = new ObjectMapper().readValue(json, Product.class);
        assert product1.getPriceInfo().getPrice() == 3.99;
    }

    @Test
    public void getProductTest() throws Exception{
        MvcResult getResult = mockMvc.perform(get("/api/product/13860428")).andDo(print()).andReturn();
        String json = getResult.getResponse().getContentAsString();
        Product product1 = new ObjectMapper().readValue(json, Product.class);
        assert product1.getPriceInfo().getPrice() == 8.99;
    }

}
