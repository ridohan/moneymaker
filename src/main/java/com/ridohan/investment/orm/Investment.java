package com.ridohan.investment.orm;


 import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
 import java.util.*;

@Entity
@Cacheable
public class Investment extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public InvestmentCategory category;


    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.EAGER)
    public Set<InvestmentEntry> entries = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.EAGER)
    public Set<InvestmentValueRecord> records = new HashSet<>();


    public static Investment findByName(String name){
        return find("name", name).firstResult();
    }

    public Investment(String name) {
        this.name = name;
    }

    public Investment(String name,InvestmentCategory investmentCategory) {
        this.name = name;
        this.category = investmentCategory;
    }

    public Investment() {

    }


    public double getYield() {
        return (getValue()/getInvestedAmount())-1;
    }

    public double getInvestedAmount(){
        double result = 0;

        Optional<InvestmentValueRecord> lastRecord = this.records.stream().max(Comparator.comparing(InvestmentValueRecord::getDate));
        if(lastRecord.isPresent()){
            result = lastRecord.get().getInvestedAmount();
        }
        return result;
    }

    public double getValue(){
        double result = 0;

        Optional<InvestmentValueRecord> lastRecord = this.records.stream().max(Comparator.comparing(InvestmentValueRecord::getDate));
        if(lastRecord.isPresent()){
            result = lastRecord.get().getValue();
        }
        return result;
    }

    public InvestmentCategory getCategory() {
        return category;
    }
}
