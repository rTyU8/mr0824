package main.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RentalAgreement {

    private Tool tool;
    
    private ChargeTableEntry chargeDetails;
    
    private int rentalDays;
    
    private int chargeDays;
    
    private Date checkoutDate;
    
    private Date dueDate;
    
    private double dailyCharge;
    
    private double preDiscountCharge;
    
    private int discountPercent;
    
    private double discountAmount;
    
    private double finalCharge;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");

    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();

    // Set the maximum number of fraction digits to 2 (optional)

	public RentalAgreement(Tool tool, int rentalDays, int discountPercent, Date checkout) {
		this.tool = tool;
		this.rentalDays = rentalDays;
		this.discountPercent = discountPercent;
		this.checkoutDate = checkout;
	    PERCENT_FORMAT.setMaximumFractionDigits(0);
	}
    
    // Getter and Setter for tool
    public Tool getTool() {
        return tool;
    }

    public ChargeTableEntry getChargeDetails() {
        return chargeDetails;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }
    public double getDailyCharge() {
        return dailyCharge;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }
    
    public String toString() {
    	return "Tool code: " + tool.getCode() +
    			"\nTool type: " + tool.getToolType() +
    			"\nTool brand: " + tool.getBrand() +
                "\nRental Days: " + rentalDays +
                "\nCheck out Date: " + DATE_FORMAT.format(checkoutDate) +
                "\nDue Date: " + DATE_FORMAT.format(dueDate) +
                "\nDaily Rental Charge: " + CURRENCY_FORMAT.format(dailyCharge) +
                "\nCharge Days: " + chargeDays +
                "\nPre-Discount Charge: " + CURRENCY_FORMAT.format(preDiscountCharge) +
                "\nDiscount Percent: " + PERCENT_FORMAT.format(discountPercent) +
                "\nDiscount Amount: " + CURRENCY_FORMAT.format(discountAmount) +
                "\nFinal Charge: " + CURRENCY_FORMAT.format(finalCharge);
    }
}