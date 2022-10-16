package org.example;

public class Main {
    public static void main(String[] args) {
        TaxService taxService = new TaxService();
        TaxType taxType;
        Bill[] payments = new Bill[]{
                new Bill(50000,new IncomTaxType(), taxService),
                new Bill(50000,new VATaxType(), taxService),
                new Bill(110000, new ProgressiveTaxType(), taxService)
        };

        for (int i = 0; i < payments.length; ++i) {
            Bill bill = payments[i];
            bill.payTaxes();
        }
    }
}