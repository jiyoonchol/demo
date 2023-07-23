package com.example.demo;

import com.example.demo.mapper.BoardMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public BoardMapper boardMapper() {
        return Mappers.getMapper(BoardMapper.class);
    }
}
