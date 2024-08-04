package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import main.model.CheckoutState;
import main.model.RentalAgreement;
import main.repository.ToolRepository;
import main.service.CheckoutService;

public class CheckoutApplication {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        CheckoutState checkoutState = CheckoutState.GetToolCode;
        CheckoutService checkoutService = new CheckoutService();
        RentalAgreement agreement;
        
        String toolCode;
        int rentalDayCount;
        int discountPercent;
        Date checkoutDate;
        boolean interactiveInput = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        
        System.out.println("Checkout an item here:");

        while (interactiveInput) {
            String input;

            switch (checkoutState) {
                case GetToolCode:
                    System.out.print("Enter tool code: ");
                    toolCode = scanner.nextLine();
                    checkoutState = CheckoutState.GetRentalDayCount;
                    break;
                case GetRentalDayCount:
                    System.out.print("Enter the number of days you want to rent the tool: ");
                    input = scanner.nextLine();
                    rentalDayCount = Integer.parseInt(scanner.nextLine());                    
                    checkoutState = CheckoutState.GetDiscountPercent;
                    break;
                case GetDiscountPercent:
                    System.out.print("Enter the discount percentage (whole number between 0-100): ");
                    input = scanner.nextLine();
                    discountPercent = Integer.parseInt(scanner.nextLine());                    
                    checkoutState = CheckoutState.GetCheckoutDate;
                    break;
                case GetCheckoutDate:
                    System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                    input = scanner.nextLine();
                    checkoutDate = dateFormat.parse(scanner.nextLine());                    
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
