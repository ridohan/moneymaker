package com.ridohan.investment.orm;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Entity
@Cacheable
public class Portfolio extends PanacheEntity {

    @Column(length = 40, unique = true,nullable = false)
    public String name;

    @Column(length = 40, unique = true,nullable = true)
    public String owner;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.EAGER)
    public Set<Investment> investments = new HashSet<>();

    public static Portfolio findByName(String name){
        return find("name", name).firstResult();
    }

    public Portfolio(String name) {
        this.name = name;
    }

    public Portfolio() {

    }


    public double getInvestedAmount(){
        return investments.stream().mapToDouble(Investment::getInvestedAmount).sum();
    }

    public double getValue(){
        return investments.stream().mapToDouble(Investment::getValue).sum();
    }

    public Map<String,Double> getInvestmentDiversification(){
        Map<String,Double> result;

        result = investments.stream()
                .collect(groupingBy(investment -> investment.getCategory().name
                        ,summingDouble(investment -> investment.getInvestedAmount()/getInvestedAmount())));


        return result;
    }



}
