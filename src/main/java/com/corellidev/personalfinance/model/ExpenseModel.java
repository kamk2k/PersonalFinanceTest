package com.corellidev.personalfinance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Kamil on 2017-04-13.
 */
@Entity
public class ExpenseModel {

    public static final String NONE_CATEGORY = "none";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private double value;
    private String category;
    private long timestamp;

    public ExpenseModel(String name, double value, String category, long timestamp) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.timestamp = timestamp;
    }

    protected ExpenseModel() {};

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", category='" + category + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
