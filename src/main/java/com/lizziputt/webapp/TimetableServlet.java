package com.lizziputt.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    public void destroy() {
        context.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate now = LocalDate.now();
        int days = now.lengthOfMonth();
        request.setAttribute("days", days);

        //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        String today = now.format(formatter);
        request.setAttribute("today", today);

        request.getRequestDispatcher(BASE_PATH + "/timetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}