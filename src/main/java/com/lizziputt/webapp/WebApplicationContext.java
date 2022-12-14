package com.lizziputt.webapp;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.classroom.ClassroomDao;
import com.lizziputt.timetable.student.Student;
import com.lizziputt.timetable.student.StudentDao;
import com.lizziputt.timetable.subject.Subject;
import com.lizziputt.timetable.subject.SubjectDao;
import com.lizziputt.timetable.teacher.Teacher;
import com.lizziputt.timetable.teacher.TeacherDao;
import com.lizziputt.timetable.timesheet.Timesheet;
import com.lizziputt.timetable.timesheet.TimesheetDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

@Getter
public class WebApplicationContext {
    public static String BASE_PATH = "/WEB-INF/jsp";

    private final ClassroomDao classroomDao;
    private final StudentDao studentDao;
    private final SubjectDao subjectDao;
    private final TeacherDao teacherDao;
    private final TimesheetDao timesheetDao;

    private final EntityManager em;

    public WebApplicationContext() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        em = emf.createEntityManager();

        classroomDao = new ClassroomDao(em, Classroom.class);
        studentDao = new StudentDao(em, Student.class);
        subjectDao = new SubjectDao(em, Subject.class);
        teacherDao = new TeacherDao(em, Teacher.class);
        timesheetDao = new TimesheetDao(em, Timesheet.class);
    }

    public void destroy() {
        em.close();
    }
}