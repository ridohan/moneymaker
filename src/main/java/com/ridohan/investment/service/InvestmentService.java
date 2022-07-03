package com.ridohan.investment.service;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentEntry;

public interface InvestmentService {

    public double calculateAverageYield(Investment investment);

    public double calculateAverageAnnualYield(Investment investment,int year);
}
