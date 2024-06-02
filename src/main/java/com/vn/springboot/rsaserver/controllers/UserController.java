package com.vn.springboot.rsaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vn.springboot.rsaserver.dto.CustomResponse;
import com.vn.springboot.rsaserver.dto.EmailDTO;
import com.vn.springboot.rsaserver.dto.LoginDTO;
import com.vn.springboot.rsaserver.entities.User;
import com.vn.springboot.rsaserver.services.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/create-user")
	public Mono<User> createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/check-user")
	public Mono<ResponseEntity<CustomResponse>> createUser(@RequestBody EmailDTO emailDTO) {
		Mono<User> existingUser = userService.getUser(emailDTO.getEmail());
		return existingUser.map(user -> ResponseEntity.ok(new CustomResponse(200, "success", user))).switchIfEmpty(Mono
				.just(ResponseEntity.status(HttpStatus.ACCEPTED).body(new CustomResponse(202, "No user found", null))));
	}

	@PostMapping("/login")
	public Mono<ResponseEntity<CustomResponse>> loginUser(@RequestBody LoginDTO loginDTO) {
		return userService.validateUser(loginDTO.getEmail(), loginDTO.getPassword())
				.map(user -> ResponseEntity.ok(new CustomResponse(200, "Login successful", user)))
				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new CustomResponse(401, "Invalid email or password", null))));
	}

	@PutMapping("/update-user/{id}")
	public Flux<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@GetMapping("/user/{id}")
	public Mono<User> fetchUser(@PathVariable("id") String id) {
		return userService.getUser(id);
	}

	@GetMapping("/users")
	public Flux<User> fetchUsers() {
		return userService.getUsers();
	}

	@DeleteMapping("/delete-user/{id}")
	public void deleteUser(@PathVariable("id") String id) {
		userService.deleteUser(id);
	}
}
