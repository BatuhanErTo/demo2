package com.spaghettierol.demo2.service;

import com.spaghettierol.demo2.dto.SessionDto;
import com.spaghettierol.demo2.dto.converter.SessionDtoConverter;
import com.spaghettierol.demo2.dto.request.CreateSessionRequest;
import com.spaghettierol.demo2.dto.response.GetSessionResponse;
import com.spaghettierol.demo2.exception.SessionException;
import com.spaghettierol.demo2.model.Convener;
import com.spaghettierol.demo2.model.Module;
import com.spaghettierol.demo2.model.Session;
import com.spaghettierol.demo2.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionDtoConverter sessionDtoConverter;
    private final ModuleService moduleService;
    private final ConvenerService convenerService;

    public List<SessionDto> getAll(){
        return sessionRepository.findAll()
                .stream().map(sessionDtoConverter::convertToSessionDto)
                .collect(Collectors.toList());
    }
    public List<GetSessionResponse> getSessionsByModuleCode(String code) {
        Module module = moduleService.findModuleByCode(code);
        return module.getSessions().stream()
                .map(sessionDtoConverter::convertToGetSessionResponse)
                .collect(Collectors.toList());
    }
    public SessionDto createSessionWithModuleCode(String code, CreateSessionRequest createSessionRequest) {
        Module module = moduleService.findModuleByCode(code);
        Session session = saveSession(createSessionRequest);
        module.setSessions(new HashSet<>(Set.of(session)));
        moduleService.saveModule(module);
        return sessionDtoConverter.convertToSessionDto(session);
    }
    public SessionDto getSessionByModuleCode(String code, Long id) {
        Module module = moduleService.findModuleByCode(code);
        if(!isSessionExistById(id)){
            throw new SessionException("Session does not exist!");
        }
        Session session = findSessionOfSpecificId(module.getSessions(),id);
        return sessionDtoConverter.convertToSessionDto(session);
    }
    public SessionDto partialUpdateSession(String code, Long id, Map<String, Object> fields) {
        Module module = moduleService.findModuleByCode(code);
        Session session = findSessionOfSpecificId(module.getSessions(),id);
        fields.forEach(
                (key,value)->{
                    Field field = ReflectionUtils.findField(Session.class,key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,session,value);
                }
        );
        sessionRepository.save(session);
        return sessionDtoConverter.convertToSessionDto(session);
    }
    public SessionDto updateSession(String code, Long id, SessionDto sessionDto) {
        Module module = moduleService.findModuleByCode(code);
        Session session = findSessionOfSpecificId(module.getSessions(),id);
        session.setTopic(sessionDto.getTopic());
        session.setDatetime(sessionDto.getDatetime());
        session.setDuration(session.getDuration());
        //session.setModule(module);
        sessionRepository.save(session);
        return sessionDtoConverter.convertToSessionDto(session);
    }

    public SessionDto deleteSession(String code, Long id) {
        Module module = moduleService.findModuleByCode(code);
        Session session = findSessionOfSpecificId(module.getSessions(),id);
        sessionRepository.delete(session);
        return null;
    }
    public List<SessionDto> deleteAllSession() {
        sessionRepository.deleteAll();
        return null;
    }

    public List<SessionDto> getSessionByModuleCode(String code){
        return findSessionByModuleCode(code).stream()
                .map(sessionDtoConverter::convertToSessionDto)
                .collect(Collectors.toList());
    }

    public List<SessionDto> getSessionByModuleCodeAndConvenerId(String code, Long id){
        return findSessionByModuleCodeAndConvenerId(code,id).stream()
                .map(sessionDtoConverter::convertToSessionDto)
                .collect(Collectors.toList());
    }

    public List<SessionDto> getSessionByConvenerId(Long id){
        return findSessionByConvenerId(id).stream()
                .map(sessionDtoConverter::convertToSessionDto)
                .collect(Collectors.toList());
    }
    private Set<Session> findSessionByConvenerId(Long id){
        Convener convener = convenerService.findByConvenerId(id);
        return convener.getModules().stream()
                .flatMap(module -> module.getSessions().stream())
                .collect(Collectors.toSet());
    }
    private Set<Session> findSessionByModuleCodeAndConvenerId(String code, Long id){
        moduleService.checkIfModuleExist(code);
        Convener convener = convenerService.findByConvenerId(id);
        Module module = (Module) convener.getModules().stream().filter(module1 -> module1.getCode().equals(code));
        return module.getSessions();
    }
    private List<Session> findSessionByModuleCode(String code){
        moduleService.checkIfModuleExist(code);
        return sessionRepository.findSessionByModule_Code(code);
    }
    private Session findSessionOfSpecificId(Set<Session> sessions,Long id){
        return (Session) sessions.stream()
                .filter(session -> session.getId() == id);
    }
    protected Session saveSession(CreateSessionRequest createSessionRequest){
        Session session = new Session();
        session.setTopic(createSessionRequest.getTopic());
        session.setDatetime(createSessionRequest.getDatetime());
        session.setDuration(createSessionRequest.getDuration());
        return  session;
    }
    protected boolean isSessionExistById(Long id){
        return sessionRepository.existsSessionById(id);
    }

}
