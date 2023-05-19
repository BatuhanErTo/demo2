package com.spaghettierol.demo2.dto;

import com.spaghettierol.demo2.model.PositionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConvenerDto {
    private Long id;
    private String name;
    private PositionType positionType;
    private Set<ModuleDtoMutual> moduleDtos;
}
