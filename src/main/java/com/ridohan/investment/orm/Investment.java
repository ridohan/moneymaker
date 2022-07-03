package com.ridohan.investment.orm;


 import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;

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

    public Investment() {

    }

    //    public InvestmentCategory getCategory() {
//        return category;
//    }
//
//    public void setCategory(InvestmentCategory category) {
//        this.category = category;
//    }

}
