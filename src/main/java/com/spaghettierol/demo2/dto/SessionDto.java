package com.spaghettierol.demo2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {
    private Long id;
    private String topic;
    private Timestamp datetime;
    private int duration;
    private ModuleDtoMutual moduleDto;
}
