package com.ridohan.investment.orm;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cacheable
public class Portfolio extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @Column(length = 40, unique = true)
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

}
