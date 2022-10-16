package org.example;

public class Bill {
    private double amount;
    private TaxType taxType;
    private TaxService taxService;

    public Bill(double amount, TaxType taxType, TaxService taxService) {
        this.amount = amount;
        this.taxType = taxType;
        this.taxService = taxService;
    }

    public int payTaxes() {
        double taxAmount = taxType.calculateTaxFor(amount);
        taxService.payOut(taxAmount);
        return (int) taxAmount;
    }
}