package com.springboot.reactive.asssignment3.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobResponse {
    private int jobId;
    private String jobName;
    private double javaExp;
    private double springExp;
    private String status;
}
