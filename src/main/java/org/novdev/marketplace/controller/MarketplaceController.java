package org.novdev.marketplace.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.novdev.marketplace.domain.Bucket;
import org.novdev.marketplace.domain.Product;
import org.novdev.marketplace.domain.User;
import org.novdev.marketplace.service.BucketService;
import org.novdev.marketplace.service.ProductService;
import org.novdev.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MarketplaceController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BucketService bucketService;
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(
			@RequestParam String name,
			@RequestParam String surname,
			@RequestParam Double money) {
		ModelAndView map = new ModelAndView("home");
		User user = new User (name,surname,money);
		userService.save(user);
		return map;
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(
			@RequestParam String name,
			@RequestParam Double price,
			@RequestParam Integer quantityInStock) {
		ModelAndView map = new ModelAndView("home");
		Product product = new Product (name,price,quantityInStock);
		productService.save(product);
		return map;
	}
	
	@RequestMapping(value = "/viewAll", method = RequestMethod.GET)
	private ModelAndView viewAllProducts(HttpServletRequest req) {
		ModelAndView map = new ModelAndView("viewAll");
		map.addObject("productsViewer", productService.getAll());
		map.addObject("usersViewer", userService.getAll());
		return map;
	}
	
	@RequestMapping(value = "/bayProduct", method = RequestMethod.POST)
	public String bayProduct(
			@RequestParam Integer productID,
			@RequestParam Integer userID) throws Exception {
		Product product = productService.readById(productID);
		User user = userService.readById(userID);
		
		if(user.getMoney() > product.getPrice() & product.getQuantityInStock() >= 1) {
			bucketService.save(new Bucket (user, product));
			productService.updateQuantityInStock(product, product.getQuantityInStock() - 1);
			userService.updateMoney(user, user.getMoney() - product.getPrice());
		}else {
			throw new Exception("You donn`t by this product");
		}

		
		
		return "redirect:/viewAll";
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String bayProduct(
			@RequestParam Integer productID) throws Exception {
		
		Product product = productService.readById(productID);
		List<Bucket> bucketWithProduct = bucketService.readAllByProductId(productID);
		
		if(bucketWithProduct.size() > 0) {
			for(Bucket bucket :  bucketWithProduct) {
				bucketService.delete(bucket);
			}
		}
		
		productService.delete(product);
		
		
		return "redirect:/viewAll";
	}
	
	@RequestMapping(value = "/bucket", method = RequestMethod.GET)
	private ModelAndView viewAllUser(HttpServletRequest req) {
		
		ModelAndView map = new ModelAndView("bucket");
		req.setAttribute("mode", "VIEW_USER");
		
		List<User> users = userService.getAll();
		List<User> usersWithProduct = new ArrayList<>();
		
		for(User user : users) {
			List<Bucket> buckets = bucketService.readAllByUserId(user.getId());
			if(buckets.size() > 0 ) {
				usersWithProduct.add(user);
			}
		}
		
		map.addObject("usersViewer", usersWithProduct);
		
		return map;
	}
	
	@RequestMapping(value = "/viewAllProductForUser", method = RequestMethod.GET)
	private ModelAndView viewAllProductForUser(@RequestParam Integer userId,HttpServletRequest req) {
		
		ModelAndView map = new ModelAndView("bucket");
		req.setAttribute("mode", "VIEW_PRODUCT");
		
		List<Bucket> buckets = bucketService.readAllByUserId(userId);
		List<Product> products = new ArrayList<>();
		
		for(Bucket bucket : buckets) {
			products.add(bucket.getProduct());
		}
		
		map.addObject("productsViewer", products);
		
		return map;
	}
	
	@RequestMapping(value = "/deleteBucket", method = RequestMethod.GET)
	private ModelAndView deleteBucket(@RequestParam Integer userId, @RequestParam Integer productId,HttpServletRequest req) {
		
		ModelAndView map = new ModelAndView("bucket");
		req.setAttribute("mode", "VIEW_PRODUCT");
		
		List<Bucket> buckets = bucketService.readAllByUserId(userId);
		Optional<Bucket> bucketOption = buckets.stream().filter(x -> x.getProduct().getId().equals(productId)).findFirst();
		
		if(bucketOption.isPresent()) {
			bucketService.delete(bucketOption.get());
		}
		
		return map;
	}
	
}
