package com.ridohan.investment.service;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentCategory;
import com.ridohan.investment.orm.InvestmentValueRecord;
import com.ridohan.investment.orm.Portfolio;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class DummyDataServiceImpl {

    final static String PORTFOLIO_NAME = "Portfolio de Red";


    @Transactional
    public List<Portfolio> init(){
        if(Portfolio.findByName(PORTFOLIO_NAME) == null) {
            InvestmentCategory immobilier = new InvestmentCategory("Immobilier");
            InvestmentCategory assuranceVie = new InvestmentCategory("Assurance Vie");
            InvestmentCategory bourse = new InvestmentCategory("Bourse");
            InvestmentCategory crypto = new InvestmentCategory("Crypto");
            InvestmentCategory.persist(immobilier,assuranceVie,bourse,crypto);

            Portfolio portFolio = new Portfolio();
            portFolio.name = PORTFOLIO_NAME;
            portFolio.owner = "Red";
            addInvestment("DEGIRO",bourse, portFolio,LocalDate.of(2020,02,20));
            addOtherInvestment("Boursorama",assuranceVie, portFolio,LocalDate.of(2015,03,21));

            portFolio.persist();
        }
        return Portfolio.listAll();
    }

    private void addInvestment(String name,InvestmentCategory investmentCategory, Portfolio portfolio, LocalDate creationDate){
        if(Investment.findByName(name) == null){
            Investment investment = new Investment(name,investmentCategory,creationDate);
            addInvestmentValueRecord(LocalDate.of(2020,01,01),100,110,investment);
            //addInvestmentValueRecord(LocalDate.of(2018,03,01),200,300,investment);
            addInvestmentValueRecord(LocalDate.of(2021,05,01),200,500,investment);
            addInvestmentValueRecord(LocalDate.of(2022,07,01),300,2000,investment);
            addInvestmentValueRecord(LocalDate.of(2030,07,01),300,2000,investment);

            investment.persist();
            portfolio.investments.add(investment);
        }

    }

    private void addOtherInvestment(String name,InvestmentCategory investmentCategory,  Portfolio portfolio, LocalDate creationDate){
        if(Investment.findByName(name) == null){
            Investment investment = new Investment(name,investmentCategory,creationDate);
            addInvestmentValueRecord(LocalDate.of(2017,01,01),1000,1100,investment);
            addInvestmentValueRecord(LocalDate.of(2018,05,01),2000,500,investment);
            addInvestmentValueRecord(LocalDate.of(2019,07,01),3000,2000,investment);

            investment.persist();
            portfolio.investments.add(investment);
        }

    }

    private void addInvestmentValueRecord(LocalDate date, double investedAmount, double value, Investment investment){
        InvestmentValueRecord record = new InvestmentValueRecord();

        record.investedAmount=investedAmount;
        record.date=date;
        record.value=value;
        record.persist();
        investment.records.add(record);
    }


}
