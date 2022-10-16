package org.example;

public class IncomTaxType extends TaxType{
    @Override
    public double calculateTaxFor(double amount) {
        return amount*0.13;
    }
}