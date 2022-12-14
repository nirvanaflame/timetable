package com.lizziputt.consoleapp;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.classroom.ClassroomDao;
import com.lizziputt.consoleapp.service.ClassroomService;
import com.lizziputt.timetable.student.Student;
import com.lizziputt.timetable.student.StudentDao;
import com.lizziputt.consoleapp.service.StudentService;
import com.lizziputt.timetable.subject.Subject;
import com.lizziputt.timetable.subject.SubjectDao;
import com.lizziputt.consoleapp.service.SubjectService;
import com.lizziputt.timetable.teacher.Teacher;
import com.lizziputt.timetable.teacher.TeacherDao;
import com.lizziputt.consoleapp.service.TeacherService;
import com.lizziputt.timetable.timesheet.Timesheet;
import com.lizziputt.timetable.timesheet.TimesheetDao;
import com.lizziputt.consoleapp.service.TimesheetService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

@Getter
public class ConsoleApplicationContext {
    private final ClassroomService classroomService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final TimesheetService timesheetService;

    public ConsoleApplicationContext() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        ClassroomDao classroomDao = new ClassroomDao(em, Classroom.class);
        StudentDao studentDao = new StudentDao(em, Student.class);
        SubjectDao subjectDao = new SubjectDao(em, Subject.class);
        TeacherDao teacherDao = new TeacherDao(em, Teacher.class);
        TimesheetDao timesheetDao = new TimesheetDao(em, Timesheet.class);

        classroomService = new ClassroomService(classroomDao);
        studentService = new StudentService(studentDao);
        subjectService = new SubjectService(subjectDao);
        teacherService = new TeacherService(teacherDao);
        timesheetService = new TimesheetService(timesheetDao, subjectService, classroomService, studentService, teacherService);
    }
}