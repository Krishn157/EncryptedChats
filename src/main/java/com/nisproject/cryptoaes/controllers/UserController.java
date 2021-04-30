package com.nisproject.cryptoaes.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.nisproject.cryptoaes.models.UserEntity;
import com.nisproject.cryptoaes.models.Response.UserResponse;
import com.nisproject.cryptoaes.repositories.UserRepo;
import com.nisproject.cryptoaes.repositories.interfaces.UserId;
import com.nisproject.cryptoaes.utils.JwtUtil;

//@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
//		RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS })
@RestController
public class UserController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepo userRepo;

	@GetMapping("curr_user")
	public UserResponse getCurrentUser(WebRequest request, Principal auth) {
		UserEntity userEntity = userRepo.findByUserId(auth.getName());
		return new UserResponse(userEntity.getUserId(), userEntity.getPin());
	}

	@GetMapping("get_all/{userId}")
	public List<UserId> getAllUsers(@PathVariable String userId) {
		return userRepo.findAllUsers(userId);

	}

}
