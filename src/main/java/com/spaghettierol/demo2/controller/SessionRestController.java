package com.spaghettierol.demo2.controller;

import com.spaghettierol.demo2.dto.SessionDto;
import com.spaghettierol.demo2.service.SessionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sessions")
@RequiredArgsConstructor
public class SessionRestController {
    private final SessionService sessionService;

    @GetMapping("/getAll")
    public ResponseEntity<List<SessionDto>> getAll(){
        return ResponseEntity.ok(sessionService.getAll());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<List<SessionDto>> deleteAllSession(){
        return ResponseEntity.ok(sessionService.deleteAllSession());
    }

    @GetMapping("/getByConvener/{id}")
    public ResponseEntity<List<SessionDto>> getByConvenerId(@PathVariable Long id){
        return ResponseEntity.ok(sessionService.getSessionByConvenerId(id));
    }
    @GetMapping
    public ResponseEntity<List<SessionDto>> getByModuleCodeAndConvenerId(@RequestParam("module") String code,@RequestParam("convener") Long id){
        return ResponseEntity.ok(sessionService.getSessionByModuleCodeAndConvenerId(code,id));
    }
    @GetMapping("/getByModule/{code}")
    public ResponseEntity<List<SessionDto>> getByModuleCode(@PathVariable String code){
        return ResponseEntity.ok(sessionService.getSessionByModuleCode(code));
    }
}
