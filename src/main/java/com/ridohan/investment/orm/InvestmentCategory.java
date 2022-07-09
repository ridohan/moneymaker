package com.ridohan.investment.orm;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Cacheable
public class InvestmentCategory extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    public InvestmentCategory(String name) {
        this.name = name;
    }

    public InvestmentCategory() {
    }
}
