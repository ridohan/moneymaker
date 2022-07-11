package com.ridohan.investment.service;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentValueRecord;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@ApplicationScoped
public class InvestmentServiceImpl implements InvestmentService {


    @Override
    public double calculateAverageYield(Investment investment) {
        double result = 0;
        if(investment.records != null && !investment.records.isEmpty() ){
            result = investment.records.stream().mapToDouble(InvestmentValueRecord::getYield).average().getAsDouble();

        }

        return result;
    }

    @Override
    public double calculateAverageAnnualYield(Investment investment, int year) {
        double result = 0;
        if(investment.records != null && !investment.records.isEmpty() ){
            result =  investment.records.stream().filter(record -> record.date.getYear()==year)
                    .mapToDouble(InvestmentValueRecord::getYield).average().getAsDouble();
        }

        return result;
    }

    @Override
    public double getAverageMonthlyInvestment(Investment investment){
        double result = 0;

        if(investment.records != null && !investment.records.isEmpty() ){
            Map<Integer,Double> yearlyInvestment = investment.records.stream()
                    .collect(groupingBy(record -> record.getDate().getYear()
                            ,summingDouble(InvestmentValueRecord::getInvestedAmount)));
            result = yearlyInvestment.values().stream().mapToDouble(Double::doubleValue).sum()/(12*yearlyInvestment.size());


        }

        return result;
    }
}
