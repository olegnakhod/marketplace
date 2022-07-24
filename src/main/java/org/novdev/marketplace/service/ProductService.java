package org.novdev.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.novdev.marketplace.domain.Product;
import org.novdev.marketplace.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	private Logger logger = LoggerFactory.getLogger( ProductService.class);
	
	@Autowired
	ProductRepository productRepository;
	
	
	public Product save(Product product) {
		logger.info("Save product item:" +product);
		return productRepository.save(product);
	}
	
	public Product readById(Integer id) {
		logger.info("Find product by id:" + id);
		Product product= new Product();
		Optional<Product> optionalProduct = productRepository.findById(id);
		
		if(optionalProduct.isPresent()) {
			product = optionalProduct.get();
		}
		return product;
	}
	
	public void update(Product product, String name, Double  price, Integer quantityInStock) {
		logger.info("Update product:" +  product);
		if(!productRepository.getReferenceById(product.getId()).equals(null)) {
			Product productDB = productRepository.getReferenceById(product.getId());
			productDB.setName(name);
			productDB.setPrice(price);
			productDB.setQuantityInStock(quantityInStock);
			logger.info("Update product item:" + product + "to " + productDB);
			productRepository.save(productDB);
		}else {
			throw new NullPointerException( product +  "this product don`t find in DB");
		}

	}
	
	public void updateQuantityInStock(Product product, Integer quantityInStock){
		logger.info("Update product:" +  product);
		if(!productRepository.getReferenceById(product.getId()).equals(null)) {
			Product productDB = productRepository.getReferenceById(product.getId());
			productDB.setQuantityInStock(quantityInStock);
			logger.info("Update product item:" + product + "to " + productDB);
			productRepository.save(productDB);
		}else {
			throw new NullPointerException( product +  "this product don`t find in DB");
		}

	}
	
	public void delete(Product product) {
		logger.info("Delete   product item:" +   product);
		 productRepository.delete( product);
	}
	
	public List<Product> getAll(){
		logger.info("Get all products items");
		return productRepository.findAll();
	}
}
