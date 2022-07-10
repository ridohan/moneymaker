package com.ridohan.investment.compound.service;

import com.ridohan.investment.compound.orm.CompoundResult;
import org.javatuples.Triplet;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ApplicationScoped
public class CompoundInterestCalculatorServiceImpl implements CompoundInterestCalculatorService{

    @Override
    public List<CompoundResult> calculateCompoundTable(LocalDate beginDate, double yieldRate, double initialInvestedAmount, double monthlyInvestment, int nbYears) {
        List<CompoundResult> results = new ArrayList<>();
        results.add(new CompoundResult(beginDate,initialInvestedAmount,0));
        double monthlyYieldRate = Math.pow(1D+yieldRate,1D/12D)-1D;
        for(int i=1;i<nbYears*12;i++){
            CompoundResult compoundResult = new CompoundResult();

            CompoundResult lastEntry = results.get(results.size()-1);
            double lastInvestedValue = lastEntry.getInvestedAmount() + lastEntry.getInterestAmount();

            compoundResult.setDate(LocalDate.from(beginDate).plusMonths(i));
            compoundResult.setInvestedAmount(lastInvestedValue+monthlyInvestment);
            compoundResult.setInterestAmount(compoundResult.getInvestedAmount()*monthlyYieldRate);

            results.add(compoundResult);
        }

        return results;
    }


}
