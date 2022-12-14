package com.lizziputt.webapp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.lizziputt.webapp.WebApplicationContext.BASE_PATH;

@WebServlet(name = "TimetableServlet", value = "/time")
public class TimetableServlet extends HttpServlet {
    private WebApplicationContext context;

    @Override
    public void init() {
        context = new WebApplicationContext();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate now = LocalDate.now();
        int days = now.lengthOfMonth();
        request.setAttribute("days", days);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        String today = now.format(formatter);
        request.setAttribute("today", today);

        request.getRequestDispatcher(BASE_PATH + "/timetable.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}