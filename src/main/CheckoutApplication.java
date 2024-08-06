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
        
        String toolCode = "";
        String rentalDayCount = "";
        String discountPercent = "";
        String checkoutDate = "";
        boolean interactiveInput = true;
        
        System.out.println("Checkout an item here:");

        while (interactiveInput) {
            switch (checkoutState) {
                case GetToolCode:
                    System.out.print("Enter tool code: ");
                    toolCode = scanner.nextLine();
                    checkoutState = CheckoutState.GetRentalDayCount;
                    break;
                case GetRentalDayCount:
                    System.out.print("Enter the number of days you want to rent the tool: ");
                    rentalDayCount = scanner.nextLine();    
                    checkoutState = CheckoutState.GetDiscountPercent;
                    break;
                case GetDiscountPercent:
                    System.out.print("Enter the discount percentage (whole number between 0-100): ");
                    discountPercent = scanner.nextLine();   
                    checkoutState = CheckoutState.GetCheckoutDate;
                    break;
                case GetCheckoutDate:
                    System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                    checkoutDate = scanner.nextLine();   
                    checkoutState = CheckoutState.GenerateRentalAgreement;
                    break;
                case GenerateRentalAgreement:
                    RentalAgreement agreement = checkoutService.generateRentalAgreement(toolCode, rentalDayCount, discountPercent, checkoutDate);
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
}
