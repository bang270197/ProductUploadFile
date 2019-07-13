package com.codegym.persistence;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPersistenceImpl implements GeneralPersistence<Product> {
    private static Map<Integer, Product> products;
    static {
        products = new HashMap<>();
    }
    @Override
    public List findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> listResult = new ArrayList<>();
        List<Product> list = new ArrayList<>(products.values());

        for (Product customer: list){
            if (customer.getName().equals(name)){
                listResult.add(customer);
            }
        }
        return listResult;
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

}
