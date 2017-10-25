package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 490)
        );
//        getFilteredWithExceeded(mealList, LocalTime.of(10, 0), LocalTime.of(13, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();


        for (UserMealWithExceed tmp :
                getFilteredWithExceeded(mealList, LocalTime.of(10, 0), LocalTime.of(13, 0), 2000)) {
            System.out.println(tmp);
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        HashMap<LocalDate, Integer> mealDates = new HashMap<>();
        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();


        for (UserMeal meal : mealList) {

            LocalDate localDate = LocalDate.of(meal.getDateTime().getYear(), meal.getDateTime().getMonth(),
                    meal.getDateTime().getDayOfMonth());

            if (!mealDates.containsKey(localDate)) {
                mealDates.put(localDate, meal.getCalories());
            } else {
                mealDates.put(localDate, mealDates.get(localDate) + meal.getCalories());
            }

        }


        for (UserMeal meal : mealList) {
            boolean isExceed;

            LocalTime mealTime = LocalTime.of(meal.getDateTime().getHour(), meal.getDateTime().getMinute());
            LocalDate mealDate = LocalDate.of(meal.getDateTime().getYear(), meal.getDateTime().getMonth(), meal.getDateTime().getDayOfMonth());

            isExceed = mealDates.get(mealDate) > caloriesPerDay;

            if ((mealTime.isAfter(startTime) || mealTime.equals(startTime))
                    && (mealTime.isBefore(endTime) || mealTime.equals(endTime))) {
                userMealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExceed));
            }

        }


        // TODO return filtered list with correctly exceeded field

        return userMealWithExceeds;
    }

/*    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        HashMap<LocalDate, Integer> mealDates = new HashMap<>();
        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();

        HashMap<LocalDate, Integer> mealDates2 = mealList.stream().map((meal) -> {
            LocalDate localDate = LocalDate.of(meal.getDateTime().getYear(), meal.getDateTime().getMonth(),
                    meal.getDateTime().getDayOfMonth());

            if (!mealDates.containsKey(localDate)) mealDates.put(localDate, meal.getCalories());
            else {
                mealDates.put(localDate, mealDates.get(localDate) + meal.getCalories());
            }
            return mealDates;

        }).collect(Collectors.toMap());


        return null;
    }*/

}