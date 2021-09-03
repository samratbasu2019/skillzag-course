package com.org.skillzag.service.mapper;


import com.org.skillzag.domain.*;
import com.org.skillzag.service.dto.SessionManagementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SessionManagement} and its DTO {@link SessionManagementDTO}.
 */
@Mapper(componentModel = "spring", uses = {CoursesManagementMapper.class})
public interface SessionManagementMapper extends EntityMapper<SessionManagementDTO, SessionManagement> {

    @Mapping(source = "coursesManagement.id", target = "coursesManagementId")
    SessionManagementDTO toDto(SessionManagement sessionManagement);

    @Mapping(source = "coursesManagementId", target = "coursesManagement")
    SessionManagement toEntity(SessionManagementDTO sessionManagementDTO);

    default SessionManagement fromId(Long id) {
        if (id == null) {
            return null;
        }
        SessionManagement sessionManagement = new SessionManagement();
        sessionManagement.setId(id);
        return sessionManagement;
    }
}
