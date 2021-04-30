package com.nisproject.cryptoaes.controllers;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisproject.cryptoaes.dto.UserDto;
import com.nisproject.cryptoaes.models.UserEntity;
import com.nisproject.cryptoaes.models.Request.LoginRequest;
import com.nisproject.cryptoaes.models.Request.UserRequest;
import com.nisproject.cryptoaes.repositories.UserRepo;
import com.nisproject.cryptoaes.services.MyUserDetailsService;
import com.nisproject.cryptoaes.utils.GeneratePin;
import com.nisproject.cryptoaes.utils.JwtUtil;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private GeneratePin generatePin;

	// User login
	@PostMapping("/login")
	public String createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Credentials", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserId());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return jwt;

	}

	// User sign-up
	@PostMapping("/register")
	public String register(@RequestBody UserRequest user) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);

		if (!user.getPassword().equals(user.getConfpassword())) {
			throw new RuntimeException("Password mismatch");
		}

		userDto.setEncryptedPassword(encoder.encode(userDto.getPassword()));

		userDto.setPin(generatePin.generateSecurePin(6, userDto.getUserId()));

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);

		userRepo.save(userEntity);

		final String jwt = jwtTokenUtil
				.generateToken(new User(userEntity.getUserId(), userEntity.getEncryptedPassword(), new ArrayList<>()));

		return jwt;
	}

}
