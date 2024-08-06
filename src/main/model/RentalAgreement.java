package main.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

public class RentalAgreement {

    private Tool tool;
    
    private ChargeTableEntry chargeDetails;
    
    private int rentalDays;
    
    private int chargeDays;
    
    private LocalDate checkoutDate;
    
    private LocalDate dueDate;
    
    private double dailyCharge;
    
    private BigDecimal preDiscountCharge;
    
    private int discountPercent;
    
    private BigDecimal discountAmount;
    
    private BigDecimal finalCharge;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");

    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();


	public RentalAgreement(Tool tool, int rentalDays, int discountPercent, LocalDate checkout) {
		this.tool = tool;
		this.rentalDays = rentalDays;
		this.discountPercent = discountPercent;
		this.checkoutDate = checkout;
	    PERCENT_FORMAT.setMaximumFractionDigits(0);
	    
	    setDueDate(rentalDays, checkoutDate);
	    
	}
	
	private void setDueDate(int rentalDays, LocalDate checkoutDate) {
		this.dueDate = checkoutDate.plusDays(rentalDays);
	}
	
	private void setChargeDays() {
		
	}
	
	private void setPreDiscountCharge() {
		BigDecimal unroundedResult = dailyCharge.multiply(BigDecimal.valueOf(chargeDays));
		this.preDiscountCharge = unroundedResult.setScale(BigDecimal.ROUND_HALF_UP);
	}
	
	private void setDiscountAmount() {
		BigDecimal unroundedResult = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent));
		this.discountAmount = unroundedResult.setScale(BigDecimal.ROUND_HALF_UP);
	}
	
	private void setFinalCharge() {
		this.finalCharge = preDiscountCharge.subtract(discountAmount);
	}
    
    public Tool getTool() {
        return tool;
    }

    public ChargeTableEntry getChargeDetails() {
        return chargeDetails;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }
    
    public double getDailyCharge() {
        return dailyCharge;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
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