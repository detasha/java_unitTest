import org.example.*;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaxCalculationTests {

    @Test
    public void billCalculation() {
        //assert
        TaxService taxService = new TaxService();
        Bill bill = new Bill(50000, new IncomTaxType(), taxService);
        int expected = 6500;

        //act
        int actual = bill.payTaxes();

        //assert
        assertEquals(expected, actual);
    }


    @Test
    public void someBillCalculation() {
        //assert
        TaxService taxService = new TaxService();
        Bill[] payments = new Bill[]{
                new Bill(50000, new IncomTaxType(), taxService),
                new Bill(50000, new VATaxType(), taxService),
                new Bill(110000, new ProgressiveTaxType(), taxService)
        };
        int[] expected = new int[]{6500, 9000, 16500};

        //act
        int[] actual = new int[]{payments[0].payTaxes(), payments[1].payTaxes(), payments[2].payTaxes()};


        //assert
        assertArrayEquals(expected, actual);
    }

    @Test
    public void calculatedProgressiveTax(){
        //assert
        ProgressiveTaxType pay = new ProgressiveTaxType();
        double amount = 5000;
        double expected = 500;

        //act
        double actual = pay.calculateTaxFor(amount);

        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void calculatedVatTax(){
        //assert
        VATaxType pay = new VATaxType();
        double amount = 5000;
        double expected = 900;

        //act
        double actual = pay.calculateTaxFor(amount);

        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void calculatedIncomeTax(){
        //assert
        IncomTaxType pay = new IncomTaxType();
        double amount = 5000;
        double expected = 650;

        //act
        double actual = pay.calculateTaxFor(amount);

        //assert
        assertEquals(expected,actual);
    }


    // Hamcrest

    @Test
    public void someBillCalculationHamcrest_notEmpty() {
        //assert
        TaxService taxService = new TaxService();
        Bill[] payments = new Bill[]{
                new Bill(50000, new IncomTaxType(), taxService),
                new Bill(50000, new VATaxType(), taxService),
                new Bill(110000, new ProgressiveTaxType(), taxService)
        };
        List<Integer> list = Arrays.asList(payments[0].payTaxes(), payments[1].payTaxes(), payments[2].payTaxes());
        assertThat(list, is(not(empty())));
    }

    @Test
    public void someBillCalculationHamcrest_contains() {
        //assert
        TaxService taxService = new TaxService();
        Bill[] payments = new Bill[]{
                new Bill(50000, new IncomTaxType(), taxService),
                new Bill(50000, new VATaxType(), taxService),
                new Bill(110000, new ProgressiveTaxType(), taxService)
        };
        Integer[] array = new Integer[]{payments[0].payTaxes(), payments[1].payTaxes(), payments[2].payTaxes()};
        assertThat(array, arrayContainingInAnyOrder(6500,9000,16500));
    }

    @Test
    public void billCalculationHamcrest_notNull(){
        TaxService taxService = new TaxService();
        Bill bill = new Bill(50000, new IncomTaxType(), taxService);
        int expected = bill.payTaxes();
        assertThat(expected, notNullValue());
    }

    @Test
    public void billCalculationHamcrest_equals(){
        TaxService taxService = new TaxService();
        Bill bill = new Bill(50000, new IncomTaxType(), taxService);
        int expected = bill.payTaxes();
        assertThat(expected, equalTo(6500));
    }

    @Test
    public void calculatedProgressiveTaxHamcrest(){
        ProgressiveTaxType pay = new ProgressiveTaxType();
        double amount = 5000;
        double expected = pay.calculateTaxFor(amount);

        assertThat(expected,lessThanOrEqualTo(500.5));

    }

    @Test
    public void  calculatedIncomeTaxHamcrest (){
        IncomTaxType pay = new IncomTaxType();
        double amount = 5000;
        double expected = pay.calculateTaxFor(amount);

        assertThat(expected, closeTo(650, 0.05));
    }

    @Test
    public void calculatedVatTaxHamcrest(){
        VATaxType pay = new VATaxType();
        double amount = 5000;
        double expected = pay.calculateTaxFor(amount);

        //assert
        assertThat(expected, equalTo(900.00));
    }
}
