package com.example.app.service;

import java.util.List;

import com.example.app.model.Product;

public interface ProductService {

	List<Product> getAllProduct();

	Product getProduct(int id);

	void deleteProduct(int id);

	Product addProduct(Product product);

	Product updateProduct(int id, Product product);

	Product editProduct(int id, Product product);

	List<Product> sortProduct();

	List<Product> sortProductdes();

	List<Product> searchProduct(String productCategory);

	List<Product> searchProduct(String productCategory, String productName);

	List<Product> getproduct(Integer page, Integer size);

	List<Product> getproduct(Double minPrice, Double maxPrice);


}
