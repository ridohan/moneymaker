package com.ridohan.investment.orm;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Cacheable
public class InvestmentValueRecord extends PanacheEntity {

    public LocalDate date;
    public double value;
    public double investedAmount;

    @Transient
    public double yield;

    public LocalDate getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public double getYield() {
        return (value/investedAmount)-1;
    }

    //    public InvestmentCategory getCategory() {
//        return category;
//    }
//
//    public void setCategory(InvestmentCategory category) {
//        this.category = category;
//    }

}
