package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();

        List<UserMealWithExceed> filter = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        for(int i = 0; i < filter.size(); i++) {
            System.out.println(filter);
        }


    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> totalCaloriesOfDate = mealList
                .stream()
                .collect(Collectors.groupingBy((d) -> d.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> filteredList = mealList.stream().filter((meal) -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map((meal) -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), (totalCaloriesOfDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)))
                .collect(Collectors.toList());

                return filteredList;


//        List<UserMealWithExceed> list = new ArrayList<>();
//        int caloriesCalculation = 0;
//        boolean exceeds = false;
//
//        for(int i = 0; i < mealList.size(); i++) {
//            if(mealList.get(i).getDateTime().getHour() > startTime.getHour() & mealList.get(i).getDateTime().getHour() < endTime.getHour())
//            {
//                caloriesCalculation += mealList.get(i).getCalories();
//                if(caloriesCalculation > caloriesPerDay)
//                exceeds = true;
//
//                list.add(new UserMealWithExceed(mealList.get(i).getDateTime(), mealList.get(i).getDescription(), mealList.get(i).getCalories(), exceeds));
//            }
//        }
//
//        return list;
    }
}
