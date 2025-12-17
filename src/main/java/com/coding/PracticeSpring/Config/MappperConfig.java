package com.coding.PracticeSpring.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
