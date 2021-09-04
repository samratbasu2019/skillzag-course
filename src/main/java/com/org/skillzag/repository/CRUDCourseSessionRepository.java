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
    @Query(value = "select cm.id as course_id, cm.course_name, cm.valid_from, cm.valid_to, cm.image_path, cm.video_url, \n" +
        "cm.course_description, cm.course_objective, cm.url_1, cm.url_2, cm.url_3, cm.quizb_4_course, cm.quiza_4_course, \n" +
        "cm.course_status, cm.recommended_status,sm.session_number, sm.session_name, sm.session_description, sm.session_url, \n" +
        "sm.session_video_url, sm.quiz, sm.session_logo, sm.session_status, sm.subscription_required, \n" +
        "sm.courses_management_id, sm.id as session_id from courses_management cm , session_management sm \n" +
        "where cm.id = sm.courses_management_id",
        nativeQuery = true)
    List<Map<String, Object>> findAllCourseSession();


    @Query(value = "select cm.id as course_id, cm.course_name, cm.valid_from, cm.valid_to, cm.image_path, cm.video_url, \n" +
        "cm.course_description, cm.course_objective, cm.url_1, cm.url_2, cm.url_3, cm.quizb_4_course, cm.quiza_4_course, \n" +
        "cm.course_status, cm.recommended_status,sm.session_number, sm.session_name, sm.session_description, sm.session_url, \n" +
        "sm.session_video_url, sm.quiz, sm.session_logo, sm.session_status, sm.subscription_required, \n" +
        "sm.courses_management_id, sm.id as session_id from courses_management cm , session_management sm \n" +
        "where cm.id = sm.courses_management_id and cm.course_name = :name",
        nativeQuery = true)
    List<Map<String, Object>> findCourseSessionByCourseName(@Param("name") String name);


    @Query(value = "select cm.id as course_id, cm.course_name, cm.valid_from, cm.valid_to, cm.image_path, cm.video_url, \n" +
        "cm.course_description, cm.course_objective, cm.url_1, cm.url_2, cm.url_3, cm.quizb_4_course, cm.quiza_4_course, \n" +
        "cm.course_status, cm.recommended_status,sm.session_number, sm.session_name, sm.session_description, sm.session_url, \n" +
        "sm.session_video_url, sm.quiz, sm.session_logo, sm.session_status, sm.subscription_required, \n" +
        "sm.courses_management_id, sm.id as session_id from courses_management cm , session_management sm \n" +
        "where cm.id = sm.courses_management_id and cm.id = :courseID",
        nativeQuery = true)
    List<Map<String, Object>> findCourseSessionByCourseID(@Param("courseID") Long courseID);

}
