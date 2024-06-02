package com.vn.springboot.rsaserver.repos;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.vn.springboot.rsaserver.entities.Sample;

@Repository
public interface SampleRepository extends ReactiveMongoRepository<Sample, String> {

}
