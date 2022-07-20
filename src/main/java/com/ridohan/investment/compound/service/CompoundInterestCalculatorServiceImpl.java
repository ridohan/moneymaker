package com.ridohan.investment.compound.service;

import com.ridohan.investment.compound.orm.CompoundResult;
import com.ridohan.investment.compound.orm.CompoundSimulationResult;
import com.ridohan.investment.orm.Investment;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;


@ApplicationScoped
public class CompoundInterestCalculatorServiceImpl implements CompoundInterestCalculatorService{

    @Override
    public CompoundSimulationResult calculateCompoundResults(LocalDate beginDate, double yieldRate, double initialInvestedAmount, double monthlyInvestment, int nbYears) {
        List<CompoundResult> monthlyResults = calculateCompoundTable(beginDate, yieldRate, initialInvestedAmount, monthlyInvestment, nbYears);
        List<CompoundResult> yearlResults = calculateCompoundTableYearly(monthlyResults);
        return new CompoundSimulationResult(monthlyResults,yearlResults,beginDate,yieldRate,initialInvestedAmount,monthlyInvestment,nbYears);
    }

    @Override
    public List<CompoundResult> calculateCompoundTable(LocalDate beginDate, double yieldRate, double initialInvestedAmount, double monthlyInvestment, int nbYears) {
        List<CompoundResult> results = new ArrayList<>();
        results.add(new CompoundResult(beginDate,initialInvestedAmount,0,0));
        double monthlyYieldRate = Math.pow(1D+yieldRate,1D/12D)-1D;
        for(int i=1;i<((nbYears*12)+1);i++){

            CompoundResult compoundResult = new CompoundResult();
            double lastInvestedValue;
            double monthlyInvested = monthlyInvestment;
            if(i==1){
                monthlyInvested +=initialInvestedAmount;
            }

            CompoundResult lastEntry = results.get(results.size()-1);
            lastInvestedValue = lastEntry.getInvestedAmount() + lastEntry.getInterestAmount();

            compoundResult.setDate(LocalDate.from(beginDate).plusMonths(i-1));
            compoundResult.setInvestedAmount(lastInvestedValue+monthlyInvestment);
            double interestAmount = compoundResult.getInvestedAmount()*monthlyYieldRate;



            compoundResult.setInterestAmount(interestAmount);
            compoundResult.setMonthlyInvestment(monthlyInvested);
            compoundResult.setTotalValue(compoundResult.getInterestAmount()+compoundResult.getInvestedAmount());
            compoundResult.setTotalInvested(lastEntry.getTotalInvested()+monthlyInvested);
            compoundResult.setTotalInterest(lastEntry.getTotalInterest()+interestAmount);
            results.add(compoundResult);
        }

        return results.subList(1,results.size());
    }

    @Override
    public List<CompoundResult> calculateCompoundTableYearly(LocalDate beginDate, double yieldRate, double initialInvestedAmount, double monthlyInvestment, int nbYears) {
        return calculateCompoundTableYearly(calculateCompoundTable(beginDate, yieldRate, initialInvestedAmount, monthlyInvestment, nbYears));
    }

    @Override
    public List<CompoundResult> calculateCompoundTableYearly(List<CompoundResult> monthlyCompoundResults) {
        Map<String,CompoundResult> results = monthlyCompoundResults.stream()
                .collect(groupingBy(compoundResult -> String.valueOf(compoundResult.getDate().getYear())
                        ,collectingAndThen(toList(),list -> {
                            double totalInterestValue = list.stream().mapToDouble(CompoundResult::getInterestAmount).sum();
                            double totalInvestedAmount = list.get(list.size()-1).getInvestedAmount();
                            double totalValue = list.get(list.size()-1).getTotalValue();
                            double totalMonthlyInvestment = list.stream().mapToDouble(CompoundResult::getMonthlyInvestment).sum();

                            double compoumdTotalInterest = list.get(list.size()-1).getTotalInterest();
                            double compoumdTotalInvest = list.get(list.size()-1).getTotalInvested();

                            LocalDate year = list.get(list.size()-1).getDate();
                            CompoundResult result = new CompoundResult(year,totalInvestedAmount,totalInterestValue,totalMonthlyInvestment,totalValue);

                            result.setTotalInterest(compoumdTotalInterest);
                            result.setTotalInvested(compoumdTotalInvest);
                            return result;
                        })));
        List<CompoundResult> yearlyCompoundResult = new ArrayList<>(results.values());

        return  yearlyCompoundResult.stream().sorted(Comparator.comparing(CompoundResult::getDate)).collect(toList());
    }




    @Override
    public List<CompoundResult> calculateCompoundTable(Investment investment, double monthlyInvestment, int nbYears) {
        return calculateCompoundTable(LocalDate.now(),investment.getYield(),investment.getInvestedAmount(),monthlyInvestment,nbYears);
    }


}
