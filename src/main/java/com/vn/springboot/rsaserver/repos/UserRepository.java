package com.vn.springboot.rsaserver.repos;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.vn.springboot.rsaserver.entities.User;

import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
	Flux<User> findByEmail(String email);
}
