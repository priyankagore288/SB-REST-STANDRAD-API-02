package com.example.app.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.app.model.Product;
import com.example.app.repository.ProductRepository;
import com.example.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(int id) {
		if(productRepository.existsById(id))
		{
			return productRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product addProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int id, Product product) {
		if(productRepository.existsById(id))
		{
			product.setProductId(id);
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public Product editProduct(int id, Product product) {
		if(productRepository.existsById(id))
		{
		 Product exproduct = productRepository.findById(id).get();
		 
		  if(product.getProductName()!=null)
		  {
			  exproduct.setProductName(product.getProductName());
		  }
		   
		  if(product.getCompanyName()!=null)
		  {
			  exproduct.setCompanyName(product.getCompanyName());
		  }
		  
		  if(product.getProductCategory()!=null)
		  {
			  exproduct.setProductCategory(product.getProductCategory());
		  }
		  if(product.getProductColor()!=null)
		  {
			  exproduct.setProductColor(product.getProductColor());
		  }
		  
		  if(product.getProductPrice()!=null)
		  {
			  exproduct.setProductPrice(product.getProductPrice());
		  }
		    return productRepository.save(exproduct);
		}
		return null;
	}

	@Override
	public List<Product> sortProduct() {
	 return	productRepository.findAllByOrderByProductPriceAsc();
	
	}

	@Override
	public List<Product> sortProductdes() {
		
		return productRepository.findAllByOrderByProductPriceDesc();
	}

	

	@Override
	public List<Product> searchProduct(String productCategory) {
		
		return productRepository.findAllByProductCategory(productCategory);
	}

	@Override
	public List<Product> searchProduct(String productCategory, String productName) {
		
		return productRepository.findAllByProductCategoryAndProductName(productCategory, productName);
	}

	@Override
	public List<Product> getproduct(Integer page, Integer size) {
		PageRequest request = PageRequest.of(page, size);
		
		Page<Product> pa = productRepository.findAll(request);
		
		  List<Product> products = pa.getContent();
		
		return products;
	}

	@Override
	public List<Product> getproduct(Double minPrice, Double maxPrice) {
		return	productRepository.findAllByProductPriceBetween(minPrice, maxPrice);
		 
	}
	
	

}
