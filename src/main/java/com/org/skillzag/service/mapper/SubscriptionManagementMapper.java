package com.org.skillzag.service.mapper;


import com.org.skillzag.domain.*;
import com.org.skillzag.service.dto.SubscriptionManagementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SubscriptionManagement} and its DTO {@link SubscriptionManagementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubscriptionManagementMapper extends EntityMapper<SubscriptionManagementDTO, SubscriptionManagement> {



    default SubscriptionManagement fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubscriptionManagement subscriptionManagement = new SubscriptionManagement();
        subscriptionManagement.setId(id);
        return subscriptionManagement;
    }
}
