package com.org.skillzag.service.mapper;


import com.org.skillzag.domain.*;
import com.org.skillzag.service.dto.CoursesManagementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CoursesManagement} and its DTO {@link CoursesManagementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoursesManagementMapper extends EntityMapper<CoursesManagementDTO, CoursesManagement> {


    @Mapping(target = "sessionManagements", ignore = true)
    @Mapping(target = "removeSessionManagement", ignore = true)
    CoursesManagement toEntity(CoursesManagementDTO coursesManagementDTO);

    default CoursesManagement fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoursesManagement coursesManagement = new CoursesManagement();
        coursesManagement.setId(id);
        return coursesManagement;
    }
}
