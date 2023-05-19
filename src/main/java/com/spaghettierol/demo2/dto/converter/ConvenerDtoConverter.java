package com.spaghettierol.demo2.dto.converter;

import com.spaghettierol.demo2.dto.ConvenerDto;
import com.spaghettierol.demo2.model.Convener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConvenerDtoConverter {

    private final ModuleDtoMutualConverter moduleDtoConverter;
    public ConvenerDto convertToConvenerDto(Convener convener){
        if(convener.getModules() != null){
            return new ConvenerDto(
                    convener.getId(),
                    convener.getName(),
                    convener.getPosition(),
                    convener.getModules().stream()
                            .map(moduleDtoConverter::convertToModuleDto)
                            .collect(Collectors.toSet())
            );
        }
        return new ConvenerDto(
                convener.getId(),
                convener.getName(),
                convener.getPosition(),
                null
        );
    }
}
