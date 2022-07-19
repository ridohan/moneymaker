package com.ridohan.investment.compound.service;

import com.ridohan.investment.compound.orm.CompoundResult;
import com.ridohan.investment.orm.Investment;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class CompoundInterestCalculatorServiceImpl implements CompoundInterestCalculatorService{

    @Override
    public List<CompoundResult> calculateCompoundTable(LocalDate beginDate, double yieldRate, double initialInvestedAmount, double monthlyInvestment, int nbYears) {
        List<CompoundResult> results = new ArrayList<>();
        results.add(new CompoundResult(beginDate,initialInvestedAmount,0));
        double monthlyYieldRate = Math.pow(1D+yieldRate,1D/12D)-1D;
        for(int i=1;i<((nbYears*12)+1);i++){
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

    @Override
    public List<CompoundResult> calculateCompoundTableYearly(LocalDate beginDate, double yieldRate, double initialInvestedAmount, double monthlyInvestment, int nbYears) {
        return calculateCompoundTable(beginDate, yieldRate, initialInvestedAmount, monthlyInvestment, nbYears).stream().filter(compoundResult -> compoundResult.getDate().getMonthValue()==(beginDate.getMonthValue()-1)).collect(Collectors.toList());
    }

    @Override
    public List<CompoundResult> calculateCompoundTable(Investment investment, double monthlyInvestment, int nbYears) {
        return calculateCompoundTable(LocalDate.now(),investment.getYield(),investment.getInvestedAmount(),monthlyInvestment,nbYears);
    }


}
