package com.ridohan.investment.service;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentEntry;

public interface InvestmentService {

    double calculateAverageYield(Investment investment);

    double calculateAverageAnnualYield(Investment investment,int year);

    double getAverageMonthlyInvestment(Investment investment);
}
