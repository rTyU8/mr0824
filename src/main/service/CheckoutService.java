package main.service;

import java.time.LocalDate;

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
		RentalAgreement agreement = new RentalAgreement(toolFromCode, rentalDayCount, discountPercent, checkoutDate);
		return agreement;
	}
	
	private void validateInputs(String toolCode, int rentalDayCount, int discountPercent) throws Exception {
		if (!repository.isToolAvailable(toolCode)) {
			throw new Exception("Invalid tool code provided.");
		}
		if (discountPercent < 0 || discountPercent > 100) {
			throw new Exception("Percentage should be an integer between 0 and 100 inclusive.");
		}
		if (rentalDayCount < 1) {
			throw new Exception("Number of rental days should be an integer greater than or equal to 1.");
		}
	}
	
	
}
