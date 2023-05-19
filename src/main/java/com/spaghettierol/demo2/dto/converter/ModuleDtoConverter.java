package com.spaghettierol.demo2.dto.converter;

import com.spaghettierol.demo2.dto.ModuleDto;
import com.spaghettierol.demo2.dto.response.GetModuleResponse;
import com.spaghettierol.demo2.model.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModuleDtoConverter {
    private final SessionDtoConverter sessionDtoConverter;
    private final ConvenerDtoConverter convenerDtoConverter;
    public ModuleDto convertToModuleDto(Module module){
        if(module.getSessions() == null || module.getConveners() == null){
            return new ModuleDto(
                    module.getId(),
                    module.getCode(),
                    module.getTitle(),
                    module.getLevel(),
                    module.isOptional(),
                   null,
                    null
            );
        }
        return new ModuleDto(
                module.getId(),
                module.getCode(),
                module.getTitle(),
                module.getLevel(),
                module.isOptional(),
                module.getConveners().stream()
                        .map(convenerDtoConverter::convertToConvenerDto)
                        .collect(Collectors.toSet()),
                module.getSessions().stream()
                        .map(sessionDtoConverter::convertToSessionDto)
                        .collect(Collectors.toSet())
        );
    }



    public GetModuleResponse convertToGetModuleResponse(Module module){
        if(module == null){
            return new GetModuleResponse();
        }
        return new GetModuleResponse(
                module.getCode(),
                module.getTitle(),
                module.getLevel(),
                module.isOptional()
        );
    }


}
