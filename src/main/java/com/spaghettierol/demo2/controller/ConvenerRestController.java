package com.spaghettierol.demo2.controller;

import com.spaghettierol.demo2.dto.ConvenerDto;
import com.spaghettierol.demo2.dto.request.CreateConvenerRequest;
import com.spaghettierol.demo2.dto.request.UpdateConvenerDto;
import com.spaghettierol.demo2.dto.response.GetModuleResponse;
import com.spaghettierol.demo2.service.ConvenerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/conveners")
@RequiredArgsConstructor
public class ConvenerRestController {
    private final ConvenerService convenerService;
    @GetMapping
    public ResponseEntity<List<ConvenerDto>> getAll(){
        return ResponseEntity.ok(convenerService.getAll());
    }

    @GetMapping("/{id}/modules")
    public ResponseEntity<List<GetModuleResponse>> getModuleByConvenerId(@PathVariable Long id){
        return ResponseEntity.ok(convenerService.getModuleByConvenerId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvenerDto> getConvenerByConvenerId(@PathVariable Long id){
        return ResponseEntity.ok(convenerService.getConvenerByConvenerId(id));
    }
    @PostMapping("/create")
    public ResponseEntity<ConvenerDto> createConvener(@Valid @RequestBody CreateConvenerRequest createConvenerRequest){
        return ResponseEntity.ok(convenerService.createConvener(createConvenerRequest));
    }

    @PostMapping("/add")
    public ResponseEntity<ConvenerDto> addModuleToConvener(@RequestParam Long convenerId, @RequestParam String code){
        return ResponseEntity.ok(convenerService.addModuleToConvener(convenerId,code));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConvenerDto> updateConvener(@PathVariable Long id,@Valid @RequestBody UpdateConvenerDto convenerDto){
        return ResponseEntity.ok(convenerService.updateConvener(id,convenerDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ConvenerDto> deleteConvener(@PathVariable Long id){
        return ResponseEntity.ok(convenerService.deleteConvener(id));
    }
}
