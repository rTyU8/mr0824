package main.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.model.ChargeTableEntry;
import main.model.RentalAgreement;
import main.model.Tool;
import main.repository.ToolRepository;

/**
 * Checkout Service for generating a rental agreement Also provides
 * functionality for parsing inputs.
 * 
 * @author matthew
 *
 */
public class CheckoutService {

    private ToolRepository repository;

    public CheckoutService() {
        repository = new ToolRepository();
    }

    public RentalAgreement generateRentalAgreement(String toolCode, String rentalDayCount, String discountPercent,
            String checkoutDate) throws Exception {
        validateToolCode(toolCode);
        Tool toolFromCode = repository.getTool(toolCode);
        ChargeTableEntry chargeDetails = repository.getChargeDetails(toolFromCode.getToolType());
        int parsedRentalDaysCount = parseRentalDayCount(rentalDayCount);
        int parsedDiscountPercent = parseDiscountPercent(discountPercent);
        LocalDate parsedCheckoutDate = parseCheckoutDate(checkoutDate);

        RentalAgreement agreement = new RentalAgreement(toolFromCode, chargeDetails, parsedRentalDaysCount,
                parsedDiscountPercent, parsedCheckoutDate);
        return agreement;
    }

    public void validateToolCode(String toolCode) throws Exception {
        if (!repository.isToolAvailable(toolCode)) {
            throw new Exception("Invalid tool code provided.");
        }
    }

    public int parseDiscountPercent(String discountPercent) throws Exception {
        int discountPercentInt = Integer.parseInt(discountPercent);
        if (discountPercentInt < 0 || discountPercentInt > 100) {
            throw new Exception("Percentage should be an integer between 0 and 100 inclusive.");
        }
        return discountPercentInt;
    }

    public int parseRentalDayCount(String rentalDayCount) throws Exception {
        int rentalDays = Integer.parseInt(rentalDayCount);
        if (rentalDays < 1) {
            throw new Exception("Number of rental days should be an integer greater than or equal to 1.");
        }
        return rentalDays;
    }

    public LocalDate parseCheckoutDate(String checkoutDate) throws Exception {
        try {
            return LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid checkout date format provided, use MM/dd/yyyy format.");
        }
    }

}
