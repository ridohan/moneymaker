package com.ridohan.investment.compound.service;

import com.ridohan.investment.compound.orm.CompoundResult;
import com.ridohan.investment.compound.orm.CompoundSimulationResult;
import com.ridohan.investment.orm.Investment;

import java.time.LocalDate;
import java.util.List;

public interface CompoundInterestCalculatorService {

    CompoundSimulationResult calculateCompoundResults(LocalDate beginDate,double yieldRate, double initialInvestedAmount,double monthlyInvestment,int nbYears);
    List<CompoundResult> calculateCompoundTable(LocalDate beginDate,double yieldRate, double initialInvestedAmount,double monthlyInvestment,int nbYears);
    List<CompoundResult> calculateCompoundTableYearly(LocalDate beginDate,double yieldRate, double initialInvestedAmount,double monthlyInvestment,int nbYears);
    List<CompoundResult> calculateCompoundTableYearly(List<CompoundResult> monthlyCompoundResults);
    List<CompoundResult> calculateCompoundTable(Investment investment, double monthlyInvestment, int nbYears);
}
