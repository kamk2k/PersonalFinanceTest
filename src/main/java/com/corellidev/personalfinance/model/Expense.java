package com.corellidev.personalfinance.model;

import javax.persistence.*;

/**
 * Created by Kamil on 2017-04-13.
 */
@Entity
public class Expense {

    public static final String NONE_CATEGORY = "none";

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPENSE_SEQ1")
    @SequenceGenerator(name="EXPENSE_SEQ1", sequenceName="EXPENSE_SEQ1", allocationSize=1)
    private Long id;
    private String name;
    private double value;
    private String category;
    private long time;

    public Expense(String name, double value, String category, long time) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.time = time;
    }

    protected Expense() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", category='" + category + '\'' +
                ", time=" + time +
                '}';
    }
}
