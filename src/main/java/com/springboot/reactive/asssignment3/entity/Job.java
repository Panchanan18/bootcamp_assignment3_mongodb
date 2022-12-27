package com.springboot.reactive.asssignment3.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection= "job")
public class Job implements Serializable {

    @NotNull(message = "the job id can not be null")
    @Id
    private int jobId;
    @NotNull(message = "the job name can not be null")
    @NotBlank(message = "the job name can not be blank")
    private String jobName;
    @NotNull(message = "the java experience can not be null")
    private double javaExp;
    @NotNull(message = "the spring experience can not be null")
    private double springExp;
}
