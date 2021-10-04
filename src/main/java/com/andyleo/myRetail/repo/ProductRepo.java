package com.andyleo.myRetail.repo;

import com.andyleo.myRetail.model.PriceInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<PriceInfo, String> {

    PriceInfo findPriceInfoByProductId(int productId);

}
