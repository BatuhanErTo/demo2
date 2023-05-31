package com.spaghettierol.demo2.dto.request;

import com.spaghettierol.demo2.dto.ModuleDtoMutual;
import com.spaghettierol.demo2.model.PositionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateConvenerDto {
    @NotNull(message = "Name info can not be NULL!")
    @NotBlank(message = "Name info can not be BLANK!")
    private String name;
    @NotNull(message = "Position Type info can not be NULL!")
    private PositionType positionType;
    @NotNull(message = "Module info can not be NULL!")
    private Set<ModuleDtoMutual> moduleDtos;
}
