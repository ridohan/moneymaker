package com.ridohan.investment.compound.orm;

import java.time.LocalDate;

public class CompoundResult {

    private LocalDate date;
    private double investedAmount;
    private double interestAmount;
    private double monthlyInvestment;
    private double totalValue;

    private double totalInvested;

    private double totalInterest;

    public CompoundResult(LocalDate date, double investedAmount, double interestAmount, double monthlyInvestment) {
        this.date = date;
        this.investedAmount = investedAmount;
        this.interestAmount = interestAmount;
        this.monthlyInvestment = monthlyInvestment;
    }

    public CompoundResult(LocalDate date, double investedAmount, double interestAmount, double monthlyInvestment, double totalValue) {
        this.date = date;
        this.investedAmount = investedAmount;
        this.interestAmount = interestAmount;
        this.monthlyInvestment = monthlyInvestment;
        this.totalValue = totalValue;
    }

    public CompoundResult() {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public double getMonthlyInvestment() {
        return monthlyInvestment;
    }

    public void setMonthlyInvestment(double monthlyInvestment) {
        this.monthlyInvestment = monthlyInvestment;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(double totalInvested) {
        this.totalInvested = totalInvested;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }
}
