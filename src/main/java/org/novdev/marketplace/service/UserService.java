package org.novdev.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.novdev.marketplace.domain.User;
import org.novdev.marketplace.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger( UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		logger.info("Save product item:" + user);
		return userRepository.save(user);
	}
	
	public User readById(Integer id) {
		logger.info("Find user by id:" + id);
		User user = new User();
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		return user;
	}
	
	public void update(User user, String name, String surname, Double money) {
		logger.info("Update user:" +  user);
		if(!userRepository.getReferenceById(user.getId()).equals(null)) {
			User  userDB = userRepository.getReferenceById(user.getId());
			userDB.setName(name);
			userDB.setSurname(surname);
			userDB.setMoney(money);
			logger.info("Update user item:" + user + "to " + userDB);
			userRepository.save(userDB);
		}else {
			throw new NullPointerException( user +  "this product don`t find in DB");
		}

	}
	
	public void updateMoney(User user,Double money) {
		logger.info("Update user:" +  user);
		if(!userRepository.getReferenceById(user.getId()).equals(null)) {
			User  userDB = userRepository.getReferenceById(user.getId());
			userDB.setMoney(money);
			logger.info("Update user item:" + user + "to " + userDB);
			userRepository.save(userDB);
		}else {
			throw new NullPointerException( user +  "this product don`t find in DB");
		}

	}
	
	public void delete(User user) {
		logger.info("Delete  user item:" +  user);
		userRepository.delete(user);
	}
	
	public List<User> getAll(){
		logger.info("Get all users items");
		return userRepository.findAll();
	}
	
}
