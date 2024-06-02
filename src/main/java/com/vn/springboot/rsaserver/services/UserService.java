package com.vn.springboot.rsaserver.services;

import com.vn.springboot.rsaserver.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	Mono<User> createUser(User user);

	Flux<User> updateUser(String id, User user);

	void deleteUser(String id);

	Mono<User> getUser(String id);

	Flux<User> getUsers();

	Mono<User> validateUser(String email, String password);
}
