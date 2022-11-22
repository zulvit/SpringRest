package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.database.ShopProductList;
import com.zulvit.userDatabaseSpring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ShopProductList shopProductList;

    @Autowired
    public ProductService(ShopProductList shopProductList) {
        this.shopProductList = shopProductList;
    }

    public Product findById(Long id) {
        return shopProductList.getOne(id);
    }

    public List<Product> findAll() {
        return shopProductList.findAll();
    }

    public Product saveProduct(Product product) {
        return shopProductList.save(product);
    }

    public void deleteById(Long id) {
        shopProductList.deleteById(id);
    }

    public void update(Long id, Product updatedProduct) {
        Product producToBeUpdated = findById(id);
        producToBeUpdated.setPrice(updatedProduct.getPrice());
        producToBeUpdated.setName(updatedProduct.getName());
        saveProduct(updatedProduct);
    }
}
