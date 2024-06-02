package com.vn.springboot.rsaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vn.springboot.rsaserver.entities.Shift;
import com.vn.springboot.rsaserver.services.ShiftService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ShiftController {
	@Autowired
	private ShiftService shiftService;

	@PostMapping("/create-shift")
	public Mono<ResponseEntity<Shift>> createOrUpdateShift(@RequestBody Shift shift) {
		return shiftService.createOrUpdateShift(shift).map(updatedShift -> ResponseEntity.ok(updatedShift))
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@GetMapping("/shifts")
	public Flux<Shift> getShifts() {
		return shiftService.getShifts();
	}

	@DeleteMapping("/delete-shift/{id}")
	public Flux<Shift> deleteUser(@PathVariable("id") String id) {
		System.out.println("Id--> " + id);
		return shiftService.deleteShift(id);
	}

	@PutMapping("/update-shift/{id}")
	public Flux<Shift> updateUser(@PathVariable("id") String id, @RequestBody Shift shift) {
		return shiftService.updateShift(id, shift);
	}
}
