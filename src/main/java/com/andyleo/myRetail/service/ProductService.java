package com.andyleo.myRetail.service;

import com.andyleo.myRetail.model.PriceInfo;
import com.andyleo.myRetail.model.Product;
import com.andyleo.myRetail.repo.ProductRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    public Product getProductById(int productId) throws Exception {
        Product product = new Product();
        product.setPriceInfo(productRepo.findPriceInfoByProductId(productId));
        product.setTitle(getProductTitle(productId));
        if(product.getPriceInfo() == null){
            return null;
        }
        return product;
    }

    public String getProductTitle(int productId) throws Exception{
        RestTemplate restfulTemplate = new RestTemplate();
        String responseBody = null;
        ResponseEntity<String> response;
        String productTitle = null;

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(getPath(productId)).build();
        response = restfulTemplate.getForEntity(uriComponents.encode().toUri(), String.class);
        responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);

        if (root != null) {
            productTitle = root.get("product").get("item").get("product_description").get("title").asText();
        }

        return productTitle;
    }

    public String getPath(int productId){
        String HOST = "https://redsky.target.com/v3/pdp/tcin/";
        String EXCLUDES_PATH = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate#_blank";
        String path = HOST + productId + EXCLUDES_PATH;
        return path;
    }

    public Product savePriceInfo(Product product) throws Exception{
        if(product != null) {
            PriceInfo updated = productRepo.save(product.getPriceInfo());
            product.setPriceInfo(updated);
            product.setTitle(getProductTitle(product.getPriceInfo().getProductId()));
        }
        return product;
    }
}
