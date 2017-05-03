package com.corellidev.personalfinance;

import com.corellidev.personalfinance.model.ExpenseModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kamil on 2017-04-13.
 */
public interface ExpenseRepository extends CrudRepository<ExpenseModel, Long> {

    List<ExpenseModel> findByTimestampGreaterThan(long timestamp);
}
