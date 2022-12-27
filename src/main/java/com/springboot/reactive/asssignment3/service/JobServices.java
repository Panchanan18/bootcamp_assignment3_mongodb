package com.springboot.reactive.asssignment3.service;

import com.hazelcast.map.IMap;
import com.springboot.reactive.asssignment3.entity.Employee;
import com.springboot.reactive.asssignment3.entity.Job;
import com.springboot.reactive.asssignment3.entity.JobResponse;
import com.springboot.reactive.asssignment3.repository.JobDtoRepository;
import com.springboot.reactive.asssignment3.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class JobServices {
    @Autowired
    private IMap<Integer, Job> userCache;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobDtoRepository jobDtoRepository;

//    @Autowired
//    private WebClient.Builder webClientBuilder;


    public Mono<JobResponse> createJob(Job job) {
        log.info("Inside the createJob method of JobServices");

        return jobRepository.existsByjobId(job.getJobId())
                .flatMap(aBoolean -> {
                    if (aBoolean) {
                        return Mono.just(new JobResponse(job.getJobId(), job.getJobName(), job.getJavaExp(), job.getSpringExp(), "Already Exists"))
                                .log("Job already exists");
                    } else {
                        return jobRepository.save(job).
                                map(j -> new JobResponse(job.getJobId(), job.getJobName(), job.getJavaExp(), job.getSpringExp(), "Created"))
                                .log("New job Created");

                    }

                        }
                );
    }


    public Flux<Employee> findEmpForJobId(int jobId) {
        log.info("Inside the findEmpForJobId method of JobServices");


        Flux<Employee> employeeFlux = jobRepository.findByjobId(jobId).flatMap(job -> {

            return WebClient.create("http://localhost:8081")
                    .get()
                    .uri(uriBuilder -> uriBuilder.path("/findEmpSkillGreater")
                            .queryParam("javaExp", job.getJavaExp())
                            .queryParam("springExp", job.getSpringExp())
                            .build())
                    .retrieve()
                    .bodyToFlux(Employee.class).log("Found all the employees for the job");


        });
        return employeeFlux;

    }
//    public Mono<Job> getJobProfileFromCache(int id) {
//
//        if (userCache.containsKey(id)) {
//            return Mono.just(userCache.get(id))
//                    .doOnNext(p-> log.info("Employee with id " + p.getJobId() + " found in cache"));
//        } else {
//            return jobDtoRepository.findByjobId(id)
//                    .doOnNext(data -> userCache.put(id, data))
//                    .doOnNext(p -> log.info("Employee with id " + p.getJobId() + " set in cache"));
//        }
//    }
//
//    public Mono<Job> addUser(Job jobDto) {
//
//        return jobDtoRepository.insert(jobDto)
//                // Store user in cache
//                .doOnSuccess(v -> userCache.put(jobDto.getJobId(), jobDto))
//                .doOnNext(p -> log.info("Employee with id " + p.getJobId() + " set in cache and database"));
//    }
    //cache methods

    public Mono<Job> getJobProfileFromCache(int id) {

        Mono<Job> result = getUserFromCache(id);
        return   result
                .switchIfEmpty(getUserFromDB(id))
                .flatMap(user -> saveUserToCache(user));
    }


    private Mono<Job> getUserFromCache(int id) {
        return Mono.fromCompletionStage(userCache.getAsync(id));
    }


    private Mono<? extends Job> saveUserToCache(Job user) {
        userCache.setAsync(user.getJobId(), user);
        return Mono.just(user);
    }

    private Mono<Job> getUserFromDB(int id) {
        return jobDtoRepository.findByjobId(id);
    }

}


//}

