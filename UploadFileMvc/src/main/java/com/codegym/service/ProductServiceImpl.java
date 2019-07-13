package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.persistence.GeneralPersistence;
import com.codegym.persistence.ProductPersistenceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements GeneralService<Product> {
    @Autowired
    private ProductPersistenceImpl productPersistence;
    @Override
    public List<Product> findAll() {
        return productPersistence.findAll();
    }

    @Override
    public void save(Product product) {
        productPersistence.save(product);
    }

    @Override
    public Product findById(int id) {
        return productPersistence.findById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productPersistence.findByName(name);
    }

    @Override
    public void update(int id, Product product) {
        productPersistence.update(id,product);
    }

    @Override
    public void remove(int id) {
        productPersistence.remove(id);
    }

}
