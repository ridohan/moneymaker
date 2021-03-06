package com.ridohan.investment.orm;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Cacheable
public class Investment extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @ManyToOne
    public InvestmentCategory category;


    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<InvestmentEntry> entries = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<InvestmentValueRecord> records = new HashSet<>();


    public LocalDate creationDate;

    public static Investment findByName(String name){
        return find("name", name).firstResult();
    }

    public Investment(String name) {
        this.name = name;
    }

    public Investment(String name,InvestmentCategory investmentCategory, LocalDate date) {
        this.name = name;
        this.category = investmentCategory;
        this.creationDate = date;
    }

    public Investment() {

    }

    public LocalDate getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate)
    {
        this.creationDate = creationDate;
    }

    public double getYield() {
        return (getValue()/getInvestedAmount())-1;
    }

    public double getInvestedAmount(){
        double result = 0;

        if(this.records != null && !this.records.isEmpty() ) {
            Optional<InvestmentValueRecord> lastRecord = this.records.stream().max(Comparator.comparing(InvestmentValueRecord::getDate));
            result = lastRecord.get().getInvestedAmount();
        }
        return result;
    }



    public double getValue(){
        double result = 0;

        if(this.records != null && !this.records.isEmpty() ) {
            Optional<InvestmentValueRecord> lastRecord = this.records.stream().max(Comparator.comparing(InvestmentValueRecord::getDate));
            result = lastRecord.get().getValue();
        }
        return result;
    }

    public InvestmentCategory getCategory() {
        return category;
    }

    public double getDelta() {
        return (getValue()-getInvestedAmount());
    }

    public double getAverageInvestedAmountMonthly()
    {
        double result;
        LocalDate start = this.getCreationDate() ;
        LocalDate stop = LocalDate.now();
        long months = ChronoUnit.MONTHS.between( start , stop );

        result = this.getInvestedAmount()/months;

        return result;
    }


}
