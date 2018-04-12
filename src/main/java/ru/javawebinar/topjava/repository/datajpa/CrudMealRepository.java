package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Transactional
    Meal save(Meal meal);


    List<Meal> findAllByUserId(Sort sort, int userId);

    Meal findByIdAndUserId(int id, int userId);

    List<Meal> findAllByDateTimeBetweenAndUserId(Sort sort, LocalDateTime startDate, LocalDateTime endDate, int userId);





}
