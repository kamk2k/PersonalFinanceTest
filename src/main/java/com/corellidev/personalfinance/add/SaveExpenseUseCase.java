package com.corellidev.personalfinance.add;

import com.corellidev.personalfinance.ExpenseRepository;
import com.corellidev.personalfinance.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Kamil on 2017-04-13.
 */
@Component
public class SaveExpenseUseCase {

    @Autowired
    private ExpenseRepository expenseRepository;

    public SaveExpenseUseCase() {
    }

    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

}
