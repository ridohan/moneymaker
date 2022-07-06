package com.ridohan.investment.orm;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Cacheable
public class InvestmentEntry extends PanacheEntity {

    public LocalDate date;

    public double investedAmount;




}
