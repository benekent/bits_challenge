package com.bitsidechallenge.service;

import com.bitsidechallenge.entities.Product;
import com.bitsidechallenge.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo){
        this.repo = repo;
    }

    public BigDecimal findPriceByCode(String code){
        return repo.findById(code).get().getPrice();
    }

}
