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
    @Query(value = "select sm.subscription_name, sm.subscription_description, sm.subscription_startdate, sm.subscription_enddate,\n" +
        "sm.amount, sm.discount_percentage from subscription_management sm , cart c  " +
        "where sm.id = c.subscription_id and c.user_id = :userId",
        nativeQuery = true)
    List<Map<String, Object>> findCartDetailsByUserID(@Param("userId") String userId);

    @Query(value = "select cm.id as course_id, cm.course_name, cm.valid_from, cm.valid_to, cm.image_path, cm.video_url, \n" +
        "cm.course_description, cm.course_objective, cm.url_1, cm.url_2, cm.url_3, cm.quizb_4_course, cm.quiza_4_course, \n" +
        "cm.course_status, cm.recommended_status,sm.session_number, sm.session_name, sm.session_description, sm.session_url, \n" +
        "sm.session_video_url, sm.quiz, sm.session_logo, sm.session_status, sm.subscription_required, \n" +
        "sm.courses_management_id, sm.id as session_id, sm2.subscription_name, sm2.subscription_startdate, sm2.subscription_enddate,\n" +
        "ua.enrollment_status, ua.id  as user_activity_id, ua.courseCompletionStatus from courses_management cm , session_management sm, \n" +
        "user_activity ua, subscription_management sm2 where cm.id = sm.courses_management_id  and sm2.id = cm.subscription_id        \n" +
        "and ua.subscription_id = sm2.id and ua.user_id = :userId",
        nativeQuery = true)
    List<Map<String, Object>> findSubscriptionDetailsByUserID(@Param("userId") String userId);

}
