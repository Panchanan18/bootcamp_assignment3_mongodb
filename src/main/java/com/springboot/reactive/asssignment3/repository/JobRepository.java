package com.springboot.reactive.asssignment3.repository;

import com.springboot.reactive.asssignment3.entity.Job;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface JobRepository extends ReactiveMongoRepository<Job,Integer> {

    Mono<Boolean> existsByjobId(int jobId);

    Flux<Job> findByjobId(int jobId);

}
