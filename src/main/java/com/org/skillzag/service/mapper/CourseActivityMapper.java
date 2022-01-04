package com.org.skillzag.service.mapper;


import com.org.skillzag.domain.*;
import com.org.skillzag.service.dto.CourseActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CourseActivity} and its DTO {@link CourseActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourseActivityMapper extends EntityMapper<CourseActivityDTO, CourseActivity> {



    default CourseActivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseActivity courseActivity = new CourseActivity();
        courseActivity.setId(id);
        return courseActivity;
    }
}
