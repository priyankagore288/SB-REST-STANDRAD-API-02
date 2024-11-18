package com.example.app.rest;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;

import com.example.app.Application;
import com.example.app.model.Product;
import com.example.app.service.ProductService;

@RestController
@RequestMapping(value = "/api/v2")
public class ProductController {
	
	
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/products",
		produces =  {"application/json"})
	
	public ResponseEntity<List<Product>> getAllProducts()
	{
	   List<Product>prlist = productService.getAllProduct();
	   
	   return new ResponseEntity<List<Product>>(prlist,HttpStatus.OK);
	}
	//http://localhost:8080/api/v2/products/{id}
	@GetMapping(value = "/products/{id}",
			produces = {"application/xml","application/json"})
	public ResponseEntity<Product> getProduct(@PathVariable int id)
	{
	  Product product = productService.getProduct(id);
	  if(product!=null) {
	  return new ResponseEntity<Product>(product,HttpStatus.OK);
	  }
	  return new  ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value="/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id)
	{
		 productService.deleteProduct(id);
		 return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/products",
			consumes = {"application/xml","application/json"},
	        produces = {"application/xml","application/json"}
			)
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
	 Product product1 = productService.addProduct(product);
	 return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product)
	{
	    Product prod = productService.updateProduct(id,product);
	    if(prod!=null)
	    {
	    	return new ResponseEntity<Product>(prod,HttpStatus.OK);
	    			
	    }
	    return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value = "/products/{id}")
	public ResponseEntity<Product> editProduct(@PathVariable int id,@RequestBody Product product)
	{
		Product product2 = productService.editProduct(id,product);
		if(product2!=null)
		{
			return new ResponseEntity<Product>(product2,HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		
	}
	
	
	//sorting
	
	@GetMapping(value = "/products/sort-by-price")
	public ResponseEntity<List<Product>> sortProduct()
	{
	 List<Product> plist =	productService.sortProduct();
	 
	 return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
	
	@GetMapping(value = "/products/sort-by-price-desc")
	public ResponseEntity<List<Product>> sortProductdesc()
	{
	 List<Product> prlist = productService.sortProductdes();
	 
	 return new ResponseEntity<List<Product>>(prlist,HttpStatus.OK);
		
	}
	
	
	//searching
	
	@GetMapping(value = "/products/search-category")
	public ResponseEntity<List<Product>> searchProductByCategory(@RequestParam String productCategory)
	{
	 List<Product> prolist = productService.searchProduct(productCategory);
	 
	 return new ResponseEntity<List<Product>>(prolist,HttpStatus.OK);
	}
	
	@GetMapping(value = "/products/search")
	public ResponseEntity<List<Product>> searchProductByCategory(@RequestParam (required = false) String productCategory,@RequestParam(defaultValue = ("TV")) String productName)
	{
	 List<Product> prolist = productService.searchProduct(productCategory,productName);
	 
	 return new ResponseEntity<List<Product>>(prolist,HttpStatus.OK);
	}
	
	
	//pagination
	@GetMapping(value = "/products/page")
	public ResponseEntity<List<Product>> getProduct(@RequestParam (defaultValue = "0")Integer page,@RequestParam(defaultValue = "5") Integer size)
	{
		List<Product> products = productService.getproduct(page,size);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	//filteration
	
	@GetMapping(value = "/products/filter")
	public ResponseEntity<List<Product>> getProduct(@RequestParam (defaultValue = "0")Double minPrice,@RequestParam(defaultValue = "100000") Double maxPrice)
	{
		List<Product> products = productService.getproduct(minPrice,maxPrice);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	
}

