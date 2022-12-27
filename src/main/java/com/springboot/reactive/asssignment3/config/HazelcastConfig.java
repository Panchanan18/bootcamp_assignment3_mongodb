package com.springboot.reactive.asssignment3.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.springboot.reactive.asssignment3.entity.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
  @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        //config.setProperty("hazelcast.jmx", "true");
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public IMap<Integer, Job> userCache(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getMap("user-cache");
    }
}
