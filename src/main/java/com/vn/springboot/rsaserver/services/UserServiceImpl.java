package com.vn.springboot.rsaserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.springboot.rsaserver.entities.User;
import com.vn.springboot.rsaserver.repos.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public Mono<User> createUser(User user) {
		return repository.save(user);
	}

	@Override
	public Flux<User> updateUser(String id, User user) {
		return repository.findById(id).flatMap(existingUser -> {
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setDob(user.getDob());
			existingUser.setStatus(user.getStatus()+"d");
			existingUser.setPassword(user.getPassword());
			return repository.save(existingUser);
		}).switchIfEmpty(Mono.empty()).thenMany(repository.findAll()).onErrorResume(e -> {
			System.out.println("Error handling user operations: " + e.getMessage());
			return Flux.empty();
		});
	}

	@Override
	public void deleteUser(String id) {
		repository.deleteById(id);
	}

	@Override
	public Mono<User> getUser(String email) {
		return repository.findByEmail(email).collectList().flatMap(users -> {
			if (users.size() == 1) {
				return Mono.just(users.get(0));
			} else if (users.isEmpty()) {
				return Mono.empty();
			} else {
				return Mono.error(new IllegalStateException("More than one user found for email: " + email));
			}
		});
	}

	@Override
	public Mono<User> validateUser(String email, String password) {
		return repository.findByEmail(email).filter(user -> user.getPassword().equals(password)).singleOrEmpty()
				.switchIfEmpty(Mono.empty());
	}

	@Override
	public Flux<User> getUsers() {
		return repository.findAll();
	}
}
