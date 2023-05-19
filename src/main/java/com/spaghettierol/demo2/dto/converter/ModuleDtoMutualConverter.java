package com.spaghettierol.demo2.dto.converter;

import com.spaghettierol.demo2.dto.ModuleDtoMutual;
import com.spaghettierol.demo2.model.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModuleDtoMutualConverter {
    public ModuleDtoMutual convertToModuleDto(Module module) {
        return new ModuleDtoMutual(
                module.getId(),
                module.getCode(),
                module.getTitle(),
                module.getLevel(),
                module.isOptional()
        );
    }

}
