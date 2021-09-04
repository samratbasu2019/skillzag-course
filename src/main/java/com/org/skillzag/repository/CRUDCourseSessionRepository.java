package com.org.skillzag.repository;

import com.org.skillzag.domain.CoursesManagement;
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
public interface CRUDCourseSessionRepository extends CrudRepository<CoursesManagement, Long> {
    @Query(value = "select cm.course_id, cm.course_name, cm.course_objective, cm.course_status, cm.valid_from , cm.valid_to,\n" +
        "sm.session_name , sm.session_description , sm.session_number , sm.session_status , sm.session_url , sm.session_video_url , sm.subscription_required \n" +
        "from courses_management cm, session_management sm where cm.id = sm.courses_management_id",
        nativeQuery = true)
    List<Map<String, Object>> findAllCourseSession();


    @Query(value = "select cm.course_id, cm.course_name, cm.course_objective, cm.course_status, cm.valid_from , cm.valid_to,\n" +
        "sm.session_name , sm.session_description , sm.session_number , sm.session_status , sm.session_url , sm.session_video_url , sm.subscription_required \n" +
        "from courses_management cm, session_management sm where cm.id = sm.courses_management_id and cm.course_name = :name",
        nativeQuery = true)
    List<Map<String, Object>> findCourseSessionByCourseName(@Param("name") String name);

}
