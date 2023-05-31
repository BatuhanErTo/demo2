package com.spaghettierol.demo2.service;

import com.spaghettierol.demo2.dto.ConvenerDto;
import com.spaghettierol.demo2.dto.converter.ConvenerDtoConverter;
import com.spaghettierol.demo2.dto.converter.ModuleDtoConverter;
import com.spaghettierol.demo2.dto.request.CreateConvenerRequest;
import com.spaghettierol.demo2.dto.request.UpdateConvenerDto;
import com.spaghettierol.demo2.dto.response.GetModuleResponse;
import com.spaghettierol.demo2.exception.ConvenerException;
import com.spaghettierol.demo2.model.Convener;
import com.spaghettierol.demo2.model.Module;
import com.spaghettierol.demo2.repository.ConvenerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConvenerService {
    private final ConvenerRepository convenerRepository;
    private final ConvenerDtoConverter convenerDtoConverter;
    private final ModuleDtoConverter moduleDtoConverter;
    private final ModuleService moduleService;

    public List<ConvenerDto> getAll() {
        return convenerRepository.findAll().stream()
                .map(convenerDtoConverter::convertToConvenerDto)
                .collect(Collectors.toList());
    }
    public List<GetModuleResponse> getModuleByConvenerId(Long id){
        Convener convener = findByConvenerId(id);
        return convener.getModules()
                .stream().map(moduleDtoConverter::convertToGetModuleResponse)
                .collect(Collectors.toList());

    }
    public ConvenerDto createConvener(CreateConvenerRequest createConvenerRequest) {
        Convener convener = new Convener();
        convener.setName(createConvenerRequest.getName());
        convener.setPosition(createConvenerRequest.getPositionType());
        convenerRepository.save(convener);
        return convenerDtoConverter.convertToConvenerDto(convener);
    }

    public ConvenerDto addModuleToConvener(Long convenerId, String code) {
        Module module = moduleService.findModuleByCode(code);
        Convener convener = findByConvenerId(convenerId);
        checkIfConvenerAlreadyContainsModule(convener,module);

        convener.getModules().add(module);
        convenerRepository.save(convener);
        moduleService.addConvenerToModule(module, convener);

        return convenerDtoConverter.convertToConvenerDto(convener);
    }
    public ConvenerDto getConvenerByConvenerId(Long id) {
        Convener convener = findByConvenerId(id);
        return convenerDtoConverter.convertToConvenerDto(convener);
    }

    public ConvenerDto updateConvener(Long id, UpdateConvenerDto convenerDto) {
        Convener convener = findByConvenerId(id);
        convener.setName(convenerDto.getName());
        convener.setPosition(convenerDto.getPositionType());
        Set<Module> modules = new HashSet<>();
        convenerDto.getModuleDtos()
                .forEach(moduleDtoMutual -> modules.add(moduleService.findModuleById(moduleDtoMutual.getId())));
        convener.setModules(modules);
        convenerRepository.save(convener);
        return convenerDtoConverter.convertToConvenerDto(convener);
    }

    public ConvenerDto deleteConvener(Long id) {
        Convener convener = findByConvenerId(id);
        Set<Module> modules = moduleService.findModulesOfOneConvener(convener.getModules());
        convener.getModules().forEach(module -> module.getConveners().remove(convener));
        moduleService.deleteModulesOfSet(modules);
        convenerRepository.delete(convener);
        return convenerDtoConverter.convertToConvenerDto(convener);
    }
    protected Convener findByConvenerId(Long id){
        return convenerRepository.findById(id)
                .orElseThrow(() -> new ConvenerException("Convener info does not exist!"));
    }
    private void checkIfConvenerAlreadyContainsModule(Convener convener,Module module){
        if(convener.getModules().contains(module))
            throw new ConvenerException("Convener already teach this module");
    }
}
