package com.ridohan.investment.compound.orm;

import java.time.LocalDate;
import java.util.List;

public class CompoundSimulationResult {

    private List<CompoundResult> monthlyResults;
    private List<CompoundResult> yearlyResults;
    private double totalInterestAmount;
    private double totalInvestedAmount;
    private double totalValue;

    private LocalDate beginDate;
    private double yieldRate;
    private double initialInvestedAmount;
    private double monthlyInvestment;
    private int nbYears;

    public CompoundSimulationResult( List<CompoundResult> monthlyResults,List<CompoundResult> yearlyResults) {
        this.monthlyResults = monthlyResults;
        this.yearlyResults = yearlyResults;
        CompoundResult lastResult = monthlyResults.stream().reduce((first, second) -> second)
                .orElse(null);

        this.totalInterestAmount = lastResult.getInterestAmount();
        this.totalInvestedAmount = lastResult.getInvestedAmount();
        this.totalValue = lastResult.getInvestedAmount()+lastResult.getInvestedAmount();
    }

    public CompoundSimulationResult(List<CompoundResult> monthlyResults, List<CompoundResult> yearlyResults, LocalDate beginDate, double yieldRate, double initialInvestedAmount, double monthlyInvestment, int nbYears) {
        this.beginDate = beginDate;
        this.yieldRate = yieldRate;
        this.initialInvestedAmount = initialInvestedAmount;
        this.monthlyInvestment = monthlyInvestment;
        this.nbYears = nbYears;


        this.monthlyResults = monthlyResults;
        this.yearlyResults = yearlyResults;
        CompoundResult lastResult = monthlyResults.stream().reduce((first, second) -> second)
                .orElse(null);

        this.totalInterestAmount = lastResult.getInterestAmount();
        this.totalInvestedAmount = lastResult.getInvestedAmount();
        this.totalValue = lastResult.getInvestedAmount()+lastResult.getInterestAmount();
    }

    public CompoundSimulationResult() {

    }

    public List<CompoundResult> getMonthlyResults() {
        return monthlyResults;
    }

    public void setMonthlyResults(List<CompoundResult> monthlyResults) {
        this.monthlyResults = monthlyResults;
    }

    public List<CompoundResult> getYearlyResults() {
        return yearlyResults;
    }

    public void setYearlyResults(List<CompoundResult> yearlyResults) {
        this.yearlyResults = yearlyResults;
    }

    public double getTotalInterestAmount() {
        return totalInterestAmount;
    }

    public void setTotalInterestAmount(double totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
    }

    public double getTotalInvestedAmount() {
        return totalInvestedAmount;
    }

    public void setTotalInvestedAmount(double totalInvestedAmount) {
        this.totalInvestedAmount = totalInvestedAmount;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
