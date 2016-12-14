package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Elisey on 14.12.2016.
 */
public class MealServlet extends HttpServlet {

    List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(MealsUtil.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000);



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meals", mealWithExceeds);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/meals.jsp");
        requestDispatcher.forward(request, response);
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }
}
