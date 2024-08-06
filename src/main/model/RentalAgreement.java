package main.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

import main.utils.CalendarUtils;

public class RentalAgreement {

    private Tool tool;
    
    private ChargeTableEntry chargeDetails;
        
    private int rentalDays;
    
    private int chargeDays;
    
    private LocalDate checkoutDate;
    
    private LocalDate dueDate;
    
    private BigDecimal dailyCharge;
    
    private BigDecimal preDiscountCharge;
    
    private int discountPercent;
    
    private BigDecimal discountAmount;
    
    private BigDecimal finalCharge;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");

    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();


	public RentalAgreement(Tool tool, ChargeTableEntry chargeDetails, 
			int rentalDays, int discountPercent, LocalDate checkout) {
		this.tool = tool;
		this.chargeDetails = chargeDetails;
		this.rentalDays = rentalDays;
		this.discountPercent = discountPercent;
		this.checkoutDate = checkout;
		this.dailyCharge = chargeDetails.getDailyCharge();
	    PERCENT_FORMAT.setMaximumFractionDigits(0);
	    
	    setDueDate();
	    setChargeDays();
	    setPreDiscountCharge();
	    setDiscountAmount();
	    setFinalCharge();
	}
	
	private void setDueDate() {
		dueDate = checkoutDate.plusDays(rentalDays);
	}
	
	private void setChargeDays() {
		int total = 0;
        for (LocalDate date = checkoutDate; date.isBefore(dueDate); date = date.plusDays(1)) {
            boolean isWeekend = CalendarUtils.isHoliday(date);
            boolean isHoliday = CalendarUtils.isWeekend(date);
            
        	if (isWeekend) {
            	total += chargeDetails.getHolidayCharge() ? 1 : 0;
            } else if (isHoliday) {
            	total += chargeDetails.getWeekendCharge() ? 1 : 0;
            } else if (chargeDetails.getWeekdayCharge()) {
            	total++;
            }
        }
        chargeDays = total;
	}
	
	private void setPreDiscountCharge() {
		BigDecimal unroundedResult = dailyCharge.multiply(BigDecimal.valueOf(chargeDays));
		preDiscountCharge = unroundedResult.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	private void setDiscountAmount() {
		BigDecimal unroundedResult = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent));
		unroundedResult = unroundedResult.multiply(BigDecimal.valueOf(0.01));
		discountAmount = unroundedResult.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	private void setFinalCharge() {
		finalCharge = preDiscountCharge.subtract(discountAmount);
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
    
    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
    
    public int getChargeDays() {
    	return chargeDays;
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