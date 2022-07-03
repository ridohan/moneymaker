package com.ridohan.investment.service;

import com.ridohan.investment.orm.Investment;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InvestmentServiceImpl implements InvestmentService {


    @Override
    public double calculateAverageYield(Investment investment) {
        double result = 0;
        if(investment.records != null && !investment.records.isEmpty() ){
            result = investment.records.stream().mapToDouble(value -> value.getYield()).average().getAsDouble();

        }

        return result;
    }

    @Override
    public double calculateAverageAnnualYield(Investment investment, int year) {
        double result = 0;
        if(investment.records != null && !investment.records.isEmpty() ){
            result = investment.records.stream().filter(record -> record.date.getYear()==year)
                    .mapToDouble(value -> value.getYield()).average().getAsDouble();
        }

        return result;
    }
}
