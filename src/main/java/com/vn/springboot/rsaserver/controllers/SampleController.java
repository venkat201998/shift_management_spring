package com.vn.springboot.rsaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vn.springboot.rsaserver.entities.Sample;
import com.vn.springboot.rsaserver.services.SampleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {

	@Autowired
	private SampleService sampleService;

	@PostMapping("/create-sample")
	public Mono<Sample> createSample(@RequestBody Sample sample) {
		return sampleService.createSample(sample);
	}

	@GetMapping("/get-samples")
	public Flux<Sample> fetchSamples() {
		return sampleService.fetchAll();
	}

	@PostMapping("/get-sample")
	public Mono<Sample> fetchSample(@RequestBody Sample sample) {
		return sampleService.fetchSample(sample.getId());
	}

	@PostMapping("/delete-sample")
	public void deleteSample(@RequestBody Sample sample) {
		sampleService.deleteSample(sample.getId());
	}

	@PostMapping("/update-sample")
	public Mono<Sample> updateSample(@RequestBody Sample sample) {
		return sampleService.updateSample(sample);
	}
}
