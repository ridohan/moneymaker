package com.ridohan.investment.resources;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentCategory;
import com.ridohan.investment.orm.InvestmentValueRecord;
import com.ridohan.investment.orm.Portfolio;

import javax.sound.sampled.Port;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Path("/init")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InitDummyDataResource {


    @GET
    @Transactional
    public List<Portfolio> init() {
        if(Portfolio.findByName("Portfolio de Red") == null) {
            InvestmentCategory immobilier = new InvestmentCategory("Immobilier");
            InvestmentCategory assuranceVie = new InvestmentCategory("Assurance Vie");
            InvestmentCategory bourse = new InvestmentCategory("Bourse");
            InvestmentCategory crypto = new InvestmentCategory("Crypto");
            InvestmentCategory.persist(immobilier,assuranceVie,bourse,crypto);

            Portfolio portFolio = new Portfolio();
            portFolio.name = "Portfolio de Red";
            portFolio.owner = "Red";
            addInvestment("DEGIRO",bourse, portFolio);
            addOtherInvestment("Boursorama",assuranceVie, portFolio);

            portFolio.persist();
        }
        return Portfolio.listAll();

    }



    private void addInvestment(String name,InvestmentCategory investmentCategory, Portfolio portfolio){
        if(Investment.findByName(name) == null){
            Investment investment = new Investment(name,investmentCategory);
            addInvestmentValueRecord(LocalDate.of(2020,01,01),100,110,investment);
            addInvestmentValueRecord(LocalDate.of(2021,05,01),200,50,investment);
            addInvestmentValueRecord(LocalDate.of(2022,07,01),300,200,investment);

            investment.persist();
            portfolio.investments.add(investment);
        }

    }

    private void addOtherInvestment(String name,InvestmentCategory investmentCategory,  Portfolio portfolio){
        if(Investment.findByName(name) == null){
            Investment investment = new Investment(name,investmentCategory);
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