package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    MealRepository mealRepository;

    public static Map<Integer, Meal> getRepository() {
        return repository;
    }

    private static Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
    if(meal.isNew()) {
        meal.setId(counter.incrementAndGet());
    }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }

//    public List<MealWithExceed> getFilteredMeal() {
//        mealRepository = new InMemoryMealRepositoryImpl();
//        return MealsUtil.getFilteredWithExceeded(repository.values(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
//    }

//    public List<MealWithExceed> getFilteredByDateAndTimeWithExceed(Collection<Meal> meals, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int caloriesPerDay) {
//
//
//
//        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
//                .collect(
//                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                );
//        return meals.stream()
//                .filter(meal -> )
//    }
}

