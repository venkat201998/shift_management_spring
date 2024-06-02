package com.vn.springboot.rsaserver.repos;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.vn.springboot.rsaserver.entities.Shift;

import reactor.core.publisher.Flux;

@Repository
public interface ShiftRepository extends ReactiveMongoRepository<Shift, String> {
	//Inheritance
	Flux<Shift> findByHallName(String hallName);
}
