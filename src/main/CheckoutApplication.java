package main;

import java.util.Date;
import java.util.Scanner;

import main.model.CheckoutState;
import main.model.RentalAgreement;
import main.repository.ToolRepository;

public class CheckoutApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CheckoutState checkoutState = CheckoutState.GetToolCode;
        ToolRepository repository = new ToolRepository();
        
        String toolCode;
        int rentalDayCount;
        int discountPercent;
        Date checkoutDate;
        boolean interactive = true;
        
        System.out.println("Checkout an item here (type 'exit' to quit):");

        while (interactive) {
            String input;

            switch (checkoutState) {
                case GetToolCode:
                    System.out.print("Enter tool code: ");
                    input = scanner.nextLine();
                    validateTool
                    checkoutState = CheckoutState.GetRentalDayCount;
                    break;
                case GetRentalDayCount:
                    System.out.print("Enter the number of days you want to rent the tool: ");
                    input = scanner.nextLine();
                    checkoutState = CheckoutState.GetDiscountPercent;
                    break;
                case GetDiscountPercent:
                    System.out.print("Enter the discount percentage (whole number between 0-100): ");
                    input = scanner.nextLine();
                    checkoutState = CheckoutState.GetCheckoutDate;
                    break;
                case GetCheckoutDate:
                    System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                    input = scanner.nextLine();
                    checkoutState = CheckoutState.GenerateRentalAgreement;
                    break;
                case GenerateRentalAgreement:
                	interactive = false;
                    break;
                default:
                	System.out.println("Unreachable..");
                	interactive = false;
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
