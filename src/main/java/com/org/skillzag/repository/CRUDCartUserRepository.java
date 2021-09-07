package com.org.skillzag.repository;

import com.org.skillzag.domain.SubscriptionManagement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Spring Data  repository for the Questions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CRUDCartUserRepository extends CrudRepository<SubscriptionManagement, Long> {
    @Query(value = " select sm.subscription_name, sm.subscription_description, sm.subscription_startdate, sm.subscription_enddate,\n" +
        "sm.amount, sm.discount_percentage from subscription_management sm , cart c  " +
        "where sm.id = c.subscription_id and c.user_id = :userId",
        nativeQuery = true)
    List<Map<String, Object>> findCartDetailsByUserID(@Param("userId") String userId);

    @Query(value = " select p.amount, cm.course_name, cm.course_description, sm.subscription_name, sm.subscription_startdate, sm.subscription_enddate ,\n" +
        "p.user_id from payment p , subscription_management sm ,courses_management cm \n" +
        "where p.subscription_id = sm.id and cm.subscription_id = sm.id and p.user_id = :userId",
        nativeQuery = true)
    List<Map<String, Object>> findSubscriptionDetailsByUserID(@Param("userId") String userId);

}
