package main;

import java.util.Scanner;

import main.model.CheckoutState;
import main.model.RentalAgreement;
import main.repository.ToolRepository;

public class CheckoutApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CheckoutState checkoutState = CheckoutState.GetToolCode;
        RentalAgreement agreement = new RentalAgreement();
        ToolRepository repository = new ToolRepository();

        System.out.println("Checkout an item here (type 'exit' to quit):");

        while (true) {
            String input;

            switch (checkoutState) {
                case GetToolCode:
                    System.out.print("Enter tool code: ");
                    input = scanner.nextLine();
                    break;
                case GetRentalDayCount:
                    System.out.print("Enter the number of days you want to rent the tool: ");
                    input = scanner.nextLine();
                    break;
                case GetDiscountPercent:
                    System.out.print("Enter the discount percentage (whole number between 0-100): ");
                    input = scanner.nextLine();
                    break;
                case GetCheckoutDate:
                    System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                    input = scanner.nextLine();
                    break;
                case GenerateRentalAgreement:
                    System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                    input = scanner.nextLine();
                    break;
                default:
                    break;
            }

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("You entered: " + input);
        }

        System.out.println("Goodbye.");
        scanner.close();
    }

    private void printCheckoutTextPrompt(CheckoutState state) {
        switch (checkoutState) {
            case CheckoutState.GetToolCode:
                System.out.print("Enter tool code: ");
                break;
            case CheckoutState.GetRentalDayCount:
                System.out.print("Enter the number of days you want to rent the tool: ");
                break;
            case CheckoutState.GetDiscountPercent:
                System.out.print("Enter the discount percentage (whole number between 0-100): ");
                break;
            case CheckoutState.GetCheckoutDate:
                System.out.print("Enter the checkout date (in mm/dd/yyyy format): ");
                break;
            default:
                break;
        }
    }
}
