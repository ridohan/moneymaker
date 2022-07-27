package com.ridohan.investment.service;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentEntry;
import com.ridohan.investment.orm.InvestmentValueRecord;

import java.util.List;

public interface InvestmentService {

    double calculateAverageYield(Investment investment);

    double calculateAverageAnnualYield(Investment investment,int year);

    double getAverageMonthlyInvestment(Investment investment);

    double getAverageInvestedAmountMonthly(Investment investment);



    List<InvestmentValueRecord> getValueRecords(Investment investment);
    boolean deleteValueRecord(Investment investment,Long investmentValueRecordId);

    boolean deleteValueRecords(Investment investment,List<InvestmentValueRecord> investmentValueRecords);

}
