package com.ridohan.investment.compound.service;

import com.ridohan.investment.compound.orm.CompoundResult;

import java.time.LocalDate;
import java.util.List;

public interface CompoundInterestCalculatorService {

    List<CompoundResult> calculateCompoundTable(LocalDate beginDate,double yieldRate, double initialInvestedAmount,double monthlyInvestment,int nbYears);
}
