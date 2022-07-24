package org.novdev.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.novdev.marketplace.domain.Bucket;
import org.novdev.marketplace.repository.BucketRepository;
import org.novdev.marketplace.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
	
	private Logger logger = LoggerFactory.getLogger( BucketService.class);
	
	@Autowired
	BucketRepository bucketRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public Bucket save(Bucket bucket) {
		logger.info("Save bucket item:" + bucket);
		return bucketRepository.save(bucket);
	}
	
	public Bucket readById(Integer id) {
		logger.info("Find bucket by id:" + id);
		Bucket bucket= new Bucket();
		Optional<Bucket> optionalBucket = bucketRepository.findById(id);
		
		if(optionalBucket.isPresent()) {
			bucket = optionalBucket.get();
		}
		return bucket;
	}
	
	public void delete(Bucket bucket) {
		logger.info("Delete   bucket item:" + bucket);
		bucketRepository.delete(bucket);
	}
	
	public List<Bucket> getAll() {
		logger.info("Get all buckets items");
		return bucketRepository.findAll();
	}
	
	public List<Bucket> readAllByProductId(Integer productId) {
		logger.info("Get all buckets items for that product");
		return bucketRepository.getAllByProductId(productId);
	}
	
	public List<Bucket> readAllByUserId(Integer userId) {
		logger.info("Get all buckets items for that user");
		return bucketRepository.getAllByUserId(userId);
	}
}
