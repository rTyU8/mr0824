package main;

import java.time.LocalDate;
import java.util.Scanner;

import main.model.CheckoutState;
import main.model.RentalAgreement;
import main.service.CheckoutService;

public class CheckoutApplication {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        CheckoutState checkoutState = CheckoutState.GetToolCode;
        CheckoutService checkoutService = new CheckoutService();
        RentalAgreement agreement;
        
        String toolCode = null;
        int rentalDayCount = 1;
        int discountPercent = 0;
        LocalDate checkoutDate = null;
        boolean interactiveInput = true;
        
        System.out.println("Checkout an item here:");

        while (interactiveInput) {

            switch (checkoutState) {
                case GetToolCode:
                    System.out.print("Enter tool code: ");
                    toolCode = scanner.nextLine();
                    checkoutService.validateToolCode(toolCode);
                    checkoutState = CheckoutState.GetRentalDayCount;
                    break;
                case GetRentalDayCount:
                    System.out.print("Enter the number of days you want to rent the tool: ");
                    rentalDayCount = Integer.parseInt(scanner.nextLine());    
                    checkoutService.validateRentalDayCount(rentalDayCount);
                    checkoutState = CheckoutState.GetDiscountPercent;
                    break;
                case GetDiscountPercent:
                    System.out.print("Enter the discount percentage (whole number between 0-100): ");
                    discountPercent = Integer.parseInt(scanner.nextLine());   
                    checkoutService.validateDiscountPercent(discountPercent);
                    checkoutState = CheckoutState.GetCheckoutDate;
                    break;
                case GetCheckoutDate:
                    System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                    checkoutDate = LocalDate.parse(scanner.nextLine());   
                    checkoutState = CheckoutState.GenerateRentalAgreement;
                    break;
                case GenerateRentalAgreement:
                	agreement = checkoutService.generateRentalAgreement(toolCode, rentalDayCount, discountPercent, checkoutDate);
                    System.out.println(agreement.toString());
                    interactiveInput = false;
                    break;
                default:
                	System.out.println("Unreachable..");
                	interactiveInput = false;
                    break;
            }

        }

        scanner.close();
    }

    private void printCheckoutTextPrompt(CheckoutState state) {
        switch (state) {
            case GetToolCode:
                System.out.print("Enter tool code: ");
                break;
            case GetRentalDayCount:
                System.out.print("Enter the number of days you want to rent the tool: ");
                break;
            case GetDiscountPercent:
                System.out.print("Enter the discount percentage (whole number between 0-100): ");
                break;
            case GetCheckoutDate:
                System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                break;
            default:
                break;
        }
    }
}
