package com.vn.springboot.rsaserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.springboot.rsaserver.entities.Sample;
import com.vn.springboot.rsaserver.repos.SampleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleRepository repository;

	@Override
	public Flux<Sample> fetchAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Sample> fetchSample(String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Sample> createSample(Sample sample) {
		return repository.save(sample);
	}

	@Override
	public Mono<Sample> updateSample(Sample sample) {
		return repository.findById(sample.getId()).flatMap(existingSample -> {
			existingSample.setEmail(sample.getEmail());
			existingSample.setFirstName(sample.getFirstName());
			existingSample.setLatsName(sample.getLatsName());
			return repository.save(existingSample);
		});
	}

	@Override
	public void deleteSample(String id) {
		System.out.println("id: " + id);
		repository.deleteById(id);
	}

}
