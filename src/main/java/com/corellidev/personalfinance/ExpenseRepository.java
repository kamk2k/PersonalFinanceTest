package com.corellidev.personalfinance;

import com.corellidev.personalfinance.model.Expense;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kamil on 2017-04-13.
 */
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    List<Expense> findByTimeGreaterThan(long time);
}
