package com.vn.springboot.rsaserver.services;

import com.vn.springboot.rsaserver.entities.Sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SampleService {
	public Flux<Sample> fetchAll();

	public Mono<Sample> fetchSample(String id);

	public Mono<Sample> createSample(Sample sample);

	public Mono<Sample> updateSample(Sample sample);

	public void deleteSample(String id);
}
