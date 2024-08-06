package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import main.model.RentalAgreement;
import main.service.CheckoutService;

/**
 * Test cases for CheckoutService
 * 
 * @author matthew
 *
 */
public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @Before
    public void setUp() {
        checkoutService = new CheckoutService();
    }

    @Test
    public void testCase1() throws Exception {
        try {
            checkoutService.generateRentalAgreement("JAKR", "5", "101", "09/03/2015");
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Percentage should be an integer between 0 and 100 inclusive.", e.getMessage());
        }
    }

    @Test
    public void testCase2() throws Exception {
        RentalAgreement agreement = checkoutService.generateRentalAgreement("LADW", "3", "10", "07/02/2020");

        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 07, 5), agreement.getDueDate());
        assertTrue(BigDecimal.valueOf(1.99).compareTo(agreement.getDailyCharge()) == 0);

        assertEquals(2, agreement.getChargeDays());
        assertTrue(BigDecimal.valueOf(3.98).compareTo(agreement.getPreDiscountCharge()) == 0);

        assertEquals(10, agreement.getDiscountPercent());
        assertTrue(BigDecimal.valueOf(0.40).compareTo(agreement.getDiscountAmount()) == 0);
        assertTrue(BigDecimal.valueOf(3.58).compareTo(agreement.getFinalCharge()) == 0);
    }

    @Test
    public void testCase3() throws Exception {
        RentalAgreement agreement = checkoutService.generateRentalAgreement("CHNS", "5", "25", "07/02/2015");

        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 07, 7), agreement.getDueDate());
        assertTrue(BigDecimal.valueOf(1.49).compareTo(agreement.getDailyCharge()) == 0);

        assertEquals(3, agreement.getChargeDays());
        assertTrue(BigDecimal.valueOf(4.47).compareTo(agreement.getPreDiscountCharge()) == 0);

        assertEquals(25, agreement.getDiscountPercent());
        assertTrue(BigDecimal.valueOf(1.12).compareTo(agreement.getDiscountAmount()) == 0);
        assertTrue(BigDecimal.valueOf(3.35).compareTo(agreement.getFinalCharge()) == 0);

    }

    @Test
    public void testCase4() throws Exception {
        RentalAgreement agreement = checkoutService.generateRentalAgreement("JAKD", "6", "0", "09/03/2015");

        assertEquals(6, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate());
        assertTrue(BigDecimal.valueOf(2.99).compareTo(agreement.getDailyCharge()) == 0);

        assertEquals(3, agreement.getChargeDays());
        assertTrue(BigDecimal.valueOf(8.97).compareTo(agreement.getPreDiscountCharge()) == 0);

        assertEquals(0, agreement.getDiscountPercent());
        assertTrue(BigDecimal.valueOf(0).compareTo(agreement.getDiscountAmount()) == 0);
        assertTrue(BigDecimal.valueOf(8.97).compareTo(agreement.getFinalCharge()) == 0);
    }

    @Test
    public void testCase5() throws Exception {
        RentalAgreement agreement = checkoutService.generateRentalAgreement("JAKR", "9", "0", "07/02/2015");

        assertEquals(9, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 11), agreement.getDueDate());
        assertTrue(BigDecimal.valueOf(2.99).compareTo(agreement.getDailyCharge()) == 0);

        assertEquals(6, agreement.getChargeDays());
        assertTrue(BigDecimal.valueOf(17.94).compareTo(agreement.getPreDiscountCharge()) == 0);

        assertEquals(0, agreement.getDiscountPercent());
        assertTrue(BigDecimal.valueOf(0).compareTo(agreement.getDiscountAmount()) == 0);
        assertTrue(BigDecimal.valueOf(17.94).compareTo(agreement.getFinalCharge()) == 0);
    }

    @Test
    public void testCase6() throws Exception {
        RentalAgreement agreement = checkoutService.generateRentalAgreement("JAKR", "4", "50", "07/02/2020");

        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 6), agreement.getDueDate());
        assertTrue(BigDecimal.valueOf(2.99).compareTo(agreement.getDailyCharge()) == 0);

        assertEquals(1, agreement.getChargeDays());
        assertTrue(BigDecimal.valueOf(2.99).compareTo(agreement.getPreDiscountCharge()) == 0);

        assertEquals(50, agreement.getDiscountPercent());
        assertTrue(BigDecimal.valueOf(1.50).compareTo(agreement.getDiscountAmount()) == 0);
        assertTrue(BigDecimal.valueOf(1.49).compareTo(agreement.getFinalCharge()) == 0);
    }
}
