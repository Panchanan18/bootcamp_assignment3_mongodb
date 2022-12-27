package com.springboot.reactive.asssignment3.repository;

import com.springboot.reactive.asssignment3.entity.Job;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

public interface JobDtoRepository extends ReactiveMongoRepository<Job,Integer> {

    Mono<Job> findByjobId(int jobId);
}
