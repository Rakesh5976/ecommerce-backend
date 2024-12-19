package com.shopping.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shopping.config.JwtProvider;
import com.shopping.exception.UserException;
import com.shopping.model.User;
import com.shopping.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository,JwtProvider jwtProvider) {
		this.jwtProvider=jwtProvider;
		this.userRepository=userRepository;
	}
	
	
	
	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User>user=userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not found with id:"+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromToken(jwt);
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not found with email"+email);
		}
		
		return user;
	}

}


	
