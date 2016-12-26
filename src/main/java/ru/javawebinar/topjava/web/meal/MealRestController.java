package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;


/**
 * GKislin
 * 06.03.2015.
 */
public class MealRestController {
    private MealService service;
    private MealRepository repository;

    public List<MealWithExceed> getUserFood() {

        repository = new InMemoryMealRepositoryImpl();

    return MealsUtil.getWithExceeded(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);

    }


}
