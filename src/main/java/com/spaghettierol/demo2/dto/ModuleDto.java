package com.spaghettierol.demo2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {
    private Long id;
    private String code;
    private String title;
    private int level;
    private boolean optional;
    private Set<ConvenerDto> convenerDtos;
    private Set<SessionDto> sessionDtos;
}
