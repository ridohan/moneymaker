package com.ridohan.investment.compound.orm;

import java.time.LocalDate;

public class CompoundResult {

    private LocalDate date;
    private double investedAmount;
    private double interestAmount;

    public CompoundResult(LocalDate date, double investedAmount, double interestAmount) {
        this.date = date;
        this.investedAmount = investedAmount;
        this.interestAmount = interestAmount;
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
}
