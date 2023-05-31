package com.spaghettierol.demo2.controller;

import com.spaghettierol.demo2.dto.ConvenerDto;
import com.spaghettierol.demo2.dto.ModuleDto;
import com.spaghettierol.demo2.dto.SessionDto;
import com.spaghettierol.demo2.dto.request.*;
import com.spaghettierol.demo2.dto.response.GetSessionResponse;
import com.spaghettierol.demo2.service.ModuleService;
import com.spaghettierol.demo2.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/modules")
@RequiredArgsConstructor
public class ModuleRestController {
    private final ModuleService moduleService;
    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<ModuleDto>> getAll(){
        return ResponseEntity.ok(moduleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleDto> getByModuleId(@PathVariable Long id){
        return ResponseEntity.ok(moduleService.getByModuleId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ModuleDto> createModule(@Valid @RequestBody CreateModuleRequest createModuleRequest){
        return ResponseEntity.ok(moduleService.createModule(createModuleRequest));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ModuleDto> updateModule(@PathVariable Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(moduleService.updateModule(id,fields));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ModuleDto> deleteModule(@PathVariable Long id){
        return ResponseEntity.ok(moduleService.deleteModule(id));
    }

    @GetMapping("/{code}/sessions")
    public ResponseEntity<List<GetSessionResponse>> getSessionsByModuleCode(@PathVariable String code){
        return ResponseEntity.ok(sessionService.getSessionsByModuleCode(code));
    }

    @PostMapping("/{code}/sessions")
    public ResponseEntity<SessionDto> createSessionWithModuleCode(@PathVariable String code, @RequestBody CreateSessionRequest createSessionRequest){
        return ResponseEntity.ok(sessionService.createSessionWithModuleCode(code,createSessionRequest));
    }

    @GetMapping("/{code}/sessions/{id}")
    private  ResponseEntity<SessionDto> getSessionByModuleCode(@PathVariable String code, @PathVariable Long id){
        return ResponseEntity.ok(sessionService.getSessionByModuleCode(code,id));
    }

    @PatchMapping("/update/{code}/session/{id}")
    private  ResponseEntity<SessionDto> partialUpdateSession(@PathVariable String code, @PathVariable Long id,@RequestBody Map<String, Object> fields){
        return ResponseEntity.ok(sessionService.partialUpdateSession(code,id,fields));
    }

    @PutMapping("/update/{code}/session/{id}")
    private ResponseEntity<SessionDto> updateSession(@PathVariable String code, @PathVariable Long id, @RequestBody UpdateSessionDto sessionDto){
        return ResponseEntity.ok(sessionService.updateSession(code,id,sessionDto));
    }

    @DeleteMapping("/delete/{code}/session/{id}")
    private ResponseEntity<SessionDto> updateSession(@PathVariable String code, @PathVariable Long id){
        return ResponseEntity.ok(sessionService.deleteSession(code,id));
    }
}
