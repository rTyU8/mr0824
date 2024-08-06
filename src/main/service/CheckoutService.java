package main.service;

import java.time.LocalDate;

import main.model.ChargeTableEntry;
import main.model.RentalAgreement;
import main.model.Tool;
import main.repository.ToolRepository;

public class CheckoutService {

	private ToolRepository repository;
	
	public CheckoutService() {
		repository = new ToolRepository();
	}
	
	public RentalAgreement generateRentalAgreement(String toolCode, int rentalDayCount,
			int discountPercent, LocalDate checkoutDate) throws Exception {
		validateInputs(toolCode, rentalDayCount, discountPercent);
		Tool toolFromCode = repository.getTool(toolCode);
		ChargeTableEntry chargeDetails = repository.getChargeDetails(toolFromCode.getToolType());
		RentalAgreement agreement = new RentalAgreement(toolFromCode, chargeDetails, rentalDayCount, discountPercent, checkoutDate);
		return agreement;
	}
	
	public void validateToolCode(String toolCode) throws Exception {
		if (!repository.isToolAvailable(toolCode)) {
			throw new Exception("Invalid tool code provided.");
		}
	}
	
	public void validateDiscountPercent(int discountPercent) throws Exception {
		if (discountPercent < 0 || discountPercent > 100) {
			throw new Exception("Percentage should be an integer between 0 and 100 inclusive.");
		}
	}
	
	public void validateRentalDayCount(int rentalDayCount) throws Exception {
		if (rentalDayCount < 1) {
			throw new Exception("Number of rental days should be an integer greater than or equal to 1.");
		}
	}
	
	private void validateInputs(String toolCode, int rentalDayCount, int discountPercent) throws Exception {
		if (!repository.isToolAvailable(toolCode)) {
			throw new Exception("Invalid tool code provided.");
		}

		if (rentalDayCount < 1) {
			throw new Exception("Number of rental days should be an integer greater than or equal to 1.");
		}
		if (discountPercent < 0 || discountPercent > 100) {
			throw new Exception("Percentage should be an integer between 0 and 100 inclusive.");
		}
	}
	
	
}
