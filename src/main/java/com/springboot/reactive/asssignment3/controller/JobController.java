package com.springboot.reactive.asssignment3.controller;

import com.springboot.reactive.asssignment3.entity.Employee;
import com.springboot.reactive.asssignment3.entity.Job;
import com.springboot.reactive.asssignment3.entity.JobResponse;
import com.springboot.reactive.asssignment3.service.JobServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Slf4j
public class JobController {
    @Autowired
    private JobServices jobServices;

    @PostMapping("/createJob")
    public Mono<JobResponse> createJob(@RequestBody @Valid Job job){
        log.info("Inside the createJob method of JobController");
        return this.jobServices.createJob(job);

    }
    @GetMapping("/findEmpForJob/{jobId}")
    public Flux<Employee> findEmpForJobId(@PathVariable("jobId") int jobId){
        log.info("Inside the findEmpForJobId method of JobController");

        return this.jobServices.findEmpForJobId(jobId);
    }

    @GetMapping("/getJobProfileFromCache/{jobId}")
    public Mono<Job> getJObProfileFromCache(@PathVariable Integer jobId){
        log.info("Inside the getJobProfileFromCache method of JobController");

        return this.jobServices.getJobProfileFromCache(jobId);
    }


}
