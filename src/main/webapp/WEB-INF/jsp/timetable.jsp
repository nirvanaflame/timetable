<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calendar</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
</head>
<body>
<%--<header>--%>
<%--    <h2 class="today">${param.today}</h2>--%>
<%--</header>--%>
<%--<section>
    <div class="calendar">
        <c:forEach var="day" items="${paramValues.days}">
            <button class="day-picker"><c:out value="${day}"/></button>
        </c:forEach>
    </div>
</section>--%>
<div class="container">

    <div class="calendar">

        <header>

            <h2>${today}</h2>

            <a class="btn-prev fontawesome-angle-left" href="#"></a>
            <a class="btn-next fontawesome-angle-right" href="#"></a>

        </header>
        <table>
            <thead>
            <tr>
                <td>ПН</td>
                <td>ВТ</td>
                <td>СР</td>
                <td>ЧТ</td>
                <td>ПТ</td>
                <td>СБ</td>
                <td>ВС</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <%--                    <button type="submit" class="prev-day btn">29</button>--%>
                    <input class="input-day" type="submit" id="29" onclick="console.log('pressed')">
                    <label for="29" class="prev-day">29</label>
                </td>
                <td class="prev-day">30</td>
                <td class="prev-day">31</td>
                <td class="prev-day">1</td>
                <td class="prev-day">2</td>
                <td class="prev-day">3</td>
                <td class="prev-day">4</td>
            </tr>
            <tr>
                <td class="prev-day">5</td>
                <td class="prev-day">6</td>
                <td class="prev-day">7</td>
                <td class="prev-day">8</td>
                <td class="prev-day">9</td>
                <td class="prev-day">10</td>
                <td class="prev-day">11</td>
            </tr>
            <tr>
                <td class="prev-day">12</td>
                <td class="prev-day">13</td>
                <td class="prev-day">14</td>
                <td class="prev-day">15</td>
                <td class="prev-day">16</td>
                <td class="current-day">17</td>
                <td>18</td>
            </tr>
            <tr>
                <td>19</td>
                <td>20</td>
                <td>21</td>
                <td>22</td>
                <td>23</td>
                <td>24</td>
                <td>25</td>
            </tr>
            <tr>
                <td>26</td>
                <td>27</td>
                <td>28</td>
                <td>29</td>
                <td>30</td>
                <td class="next-day">1</td>
                <td class="next-day">2</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="time">

        <div class="time-entry">
            <hr class="delimiter">
            <h3 class="subject">Math. Baranowskaja. 1104</h3>
            <p>08:00 - 9:30</p>
        </div>
    </div>
</div>
</body>
</html>
