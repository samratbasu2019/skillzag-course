package com.org.skillzag.service.mapper;


import com.org.skillzag.domain.*;
import com.org.skillzag.service.dto.UserActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserActivity} and its DTO {@link UserActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserActivityMapper extends EntityMapper<UserActivityDTO, UserActivity> {



    default UserActivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserActivity userActivity = new UserActivity();
        userActivity.setId(id);
        return userActivity;
    }
}
