package com.spaghettierol.demo2.dto.converter;

import com.spaghettierol.demo2.dto.SessionDto;
import com.spaghettierol.demo2.dto.response.GetSessionResponse;
import com.spaghettierol.demo2.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionDtoConverter {
    private final ModuleDtoMutualConverter moduleDtoConverter;
    public SessionDto convertToSessionDto(Session session){
        return new SessionDto(
                session.getId(),
                session.getTopic(),
                session.getDatetime(),
                session.getDuration(),
                moduleDtoConverter.convertToModuleDto(session.getModule())
        );
    }

    public GetSessionResponse convertToGetSessionResponse(Session session){
        if(session == null){
            return new GetSessionResponse();
        }
        return new GetSessionResponse(
                session.getTopic(),
                session.getDatetime(),
                session.getDuration()
        );
    }
}
