package com.ridohan.investment.service;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentValueRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

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

    @Override
    public List<InvestmentValueRecord> getValueRecords(Investment investment) {
        List<InvestmentValueRecord> results = new ArrayList<>();
        if(investment.records != null && !investment.records.isEmpty() ) {
            results =  investment.records.stream().sorted(Comparator.comparing(InvestmentValueRecord::getDate)).collect(toList());
        }
        return results;
    }

    @Override
    public boolean deleteValueRecord(Investment investment,Long investmentValueRecordId) {
        InvestmentValueRecord record = InvestmentValueRecord.findById(investmentValueRecordId);
        if (record == null) {
            throw new NotFoundException();
        }
        investment.records.remove(record);
        return InvestmentValueRecord.deleteById(record.getId());
    }

    @Override
    public boolean deleteValueRecords(Investment investment, List<InvestmentValueRecord> investmentValueRecords) {
        boolean result = true;

        for(InvestmentValueRecord record : investmentValueRecords){
           boolean deletionSuccess = deleteValueRecord(investment, record.getId());
           if(!deletionSuccess){
               result = false;
           }
        }

        return result;
    }
}
