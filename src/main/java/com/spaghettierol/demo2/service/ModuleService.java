package com.spaghettierol.demo2.service;

import com.spaghettierol.demo2.dto.ModuleDto;
import com.spaghettierol.demo2.dto.SessionDto;
import com.spaghettierol.demo2.dto.converter.ModuleDtoConverter;
import com.spaghettierol.demo2.dto.converter.SessionDtoConverter;
import com.spaghettierol.demo2.dto.request.CreateModuleRequest;
import com.spaghettierol.demo2.dto.request.CreateSessionRequest;
import com.spaghettierol.demo2.dto.response.GetSessionResponse;
import com.spaghettierol.demo2.exception.ModuleException;
import com.spaghettierol.demo2.exception.SessionException;
import com.spaghettierol.demo2.model.Convener;
import com.spaghettierol.demo2.model.Module;
import com.spaghettierol.demo2.model.Session;
import com.spaghettierol.demo2.repository.ModuleRepository;
import org.springframework.util.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleDtoConverter moduleDtoConverter;

    private boolean isModuleAlreadyExist(String code){
        return moduleRepository.existsModuleByCode(code);
    }
    protected void checkIfModuleExist(String code){
        if(!isModuleAlreadyExist(code)){
            throw new ModuleException("Module does not exist!");
        }
    }
    protected void addConvenerToModule(Module module, Convener convener){
        module.setConveners(Set.of(convener).stream().collect(Collectors.toSet()));
        moduleRepository.save(module);
    }
    public List<ModuleDto> getAll() {
        return moduleRepository.findAll()
                .stream()
                .map(moduleDtoConverter::convertToModuleDto)
                .collect(Collectors.toList());
    }

    public ModuleDto getByModuleId(Long id){
        return moduleDtoConverter.convertToModuleDto(findModuleById(id));
    }

    public ModuleDto createModule(CreateModuleRequest createModuleRequest) {
        if(isModuleAlreadyExist(createModuleRequest.getCode())){
            throw new ModuleException("This module already exist!");
        }
        Module module = new Module();
        module.setCode(createModuleRequest.getCode());
        module.setLevel(createModuleRequest.getLevel());
        module.setTitle(createModuleRequest.getTitle());
        module.setOptional(createModuleRequest.isOptional());
        moduleRepository.save(module);
        return moduleDtoConverter.convertToModuleDto(module);
    }
    public ModuleDto updateModule(Long id, Map<String,Object> fields) {
        Module module = findModuleById(id);
        fields.forEach(
                (key,value)->{
                    Field field = ReflectionUtils.findField(Module.class,key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,module,value);
                }
        );
        return moduleDtoConverter.convertToModuleDto(module);
    }

    public ModuleDto deleteModule(Long id) {
        Module module = findModuleById(id);
        moduleRepository.delete(module);
        return null;
    }
    protected Module findModuleByCode(String code){
        Module module = moduleRepository.findModuleByCode(code);
        if(module == null){
            throw new ModuleException("Module does not exist!");
        }
        return module;
    }
    protected Module findModuleById(Long id){
        return moduleRepository.findById(id).
                orElseThrow(() -> new ModuleException("Module does not exist!"));
    }

    protected Set<Module> findModulesOfOneConvener(Set<Module> moduleSet){
        return moduleSet.stream()
                .filter(module -> module.getConveners().size() == 1)
                .collect(Collectors.toSet());
    }

    protected void deleteModulesOfSet(Set<Module> moduleSet){
        if(moduleSet == null){
            return;
        }
        moduleRepository.deleteAll(moduleSet);
    }

    protected void saveModule(Module module) {
        moduleRepository.save(module);
    }
}
