package com.vn.springboot.rsaserver.services;

import com.vn.springboot.rsaserver.entities.Shift;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShiftService {
	Mono<Shift> createOrUpdateShift(Shift shift);
	
	Flux<Shift> getShifts();
	
	Flux<Shift> deleteShift(String id);
	
	Flux<Shift> updateShift(String id, Shift shift);
}
