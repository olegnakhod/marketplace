package org.novdev.marketplace.repository;

import java.util.List;

import org.novdev.marketplace.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Integer>{
	
	@Query("SELECT b FROM Bucket b WHERE b.product.id =:productId")
	List<Bucket> getAllByProductId(Integer productId);
	
	@Query("SELECT b FROM Bucket b WHERE b.user.id =:userId")
	List<Bucket> getAllByUserId(Integer userId);

}
