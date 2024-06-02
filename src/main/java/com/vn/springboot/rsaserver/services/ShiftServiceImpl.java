package com.vn.springboot.rsaserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.vn.springboot.rsaserver.entities.Shift;
import com.vn.springboot.rsaserver.repos.ShiftRepository;

@Service
public class ShiftServiceImpl implements ShiftService {
	@Autowired
	private ShiftRepository repository;

	@Override
	public Mono<Shift> createOrUpdateShift(Shift shift) {
		return repository.findByHallName(shift.getHallName()).collectList().flatMap(halls -> {
			for (Shift existingShift : halls) {
				if (existingShift.getStartDate().equals(shift.getStartDate())
						&& existingShift.getStartTime().equals(shift.getStartTime())) {
					return Mono.empty();
				}
			}
			return repository.save(shift);
		});
	}

	@Override
	public Flux<Shift> getShifts() {
		return repository.findAll();
	}

	@Override
	public Flux<Shift> deleteShift(String id) {
		return repository.deleteById(id).thenMany(repository.findAll());
	}
	
	@Override
	public Flux<Shift> updateShift(String id, Shift shift) {
		return repository.findById(id).flatMap(existingShift -> {
			existingShift.setEndDate(shift.getEndDate());
			existingShift.setEndTime(shift.getEndTime());
			existingShift.setHallName(shift.getHallName());
			existingShift.setStartDate(shift.getStartDate());
			existingShift.setStartTime(shift.getStartTime());
			existingShift.setAssignedTo(shift.getAssignedTo());
			existingShift.setStatus(shift.getStatus());
			return repository.save(existingShift);
		}).switchIfEmpty(Mono.empty()).thenMany(repository.findAll()).onErrorResume(e -> {
			System.out.println("Error handling user operations: " + e.getMessage());
			return Flux.empty();
		});
	}
}
