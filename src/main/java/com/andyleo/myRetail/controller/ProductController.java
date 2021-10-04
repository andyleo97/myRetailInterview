package com.andyleo.myRetail.controller;

import com.andyleo.myRetail.model.PriceInfo;
import com.andyleo.myRetail.model.Product;
import com.andyleo.myRetail.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(path = "/api/product/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable int id) throws Exception {
        Product product = null;
        product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping(path = "/api/product/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@PathVariable int id, @RequestBody Product product ) throws Exception{
        product = productService.savePriceInfo(product);
        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleNoProductFound(HttpClientErrorException ex){
        ResponseEntity<Product> response = null;
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMismatchDataException(MethodArgumentTypeMismatchException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> handleJsonError(JsonProcessingException ex){
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
