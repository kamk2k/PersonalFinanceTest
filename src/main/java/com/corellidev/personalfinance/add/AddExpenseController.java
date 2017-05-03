package com.corellidev.personalfinance.add;

import com.corellidev.personalfinance.ExpenseRepository;
import com.corellidev.personalfinance.model.ExpenseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Kamil on 2017-04-13.
 */
@RestController
public class AddExpenseController {

    @Autowired
    private SaveExpenseUseCase saveExpenseUseCase;

    @Autowired
    private ExpenseRepository expenseRepository;

    @RequestMapping("/add")
    public String index(@RequestParam(value = "name", defaultValue = "") String name,
                        @RequestParam(value = "value", defaultValue = "0") String value,
                        @RequestParam(value = "cat", defaultValue = ExpenseModel.NONE_CATEGORY) String category) {
        double doubleValue;
        try {
            doubleValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            doubleValue = 0;
            System.out.print(e.toString());
        }
        ExpenseModel model = new ExpenseModel(name, doubleValue, category, new Date().getTime());
        saveExpenseUseCase.saveExpense(model);
        return "Greetings from Spring Boot! " + model.toString();
    }

    @RequestMapping("/")
    public String defTest() {
        String result = "";
        for (ExpenseModel model : expenseRepository.findAll()) {
            result = result + model.toString() + " ";
        }

        return result;
    }
}
