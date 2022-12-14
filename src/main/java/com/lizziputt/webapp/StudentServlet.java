package com.lizziputt.webapp;

import com.lizziputt.timetable.student.Student;
import com.lizziputt.timetable.timesheet.Timesheet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.lizziputt.webapp.WebApplicationContext.BASE_PATH;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private WebApplicationContext context;

    @Override
    public void init() {
        context = new WebApplicationContext();
    }

    @Override
    public void destroy() {
        super.destroy();
        context.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Timesheet> list = context.getTimesheetDao().findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher(BASE_PATH + "/student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}