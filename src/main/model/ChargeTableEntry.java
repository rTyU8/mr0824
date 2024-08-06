package main.model;

import java.math.BigDecimal;

/**
 * Models charge info for a tool.
 * 
 * @author matthew
 *
 */
public class ChargeTableEntry {

    private ToolType tooltype;

    private BigDecimal dailyCharge;

    private boolean weekdayCharge;

    private boolean weekendCharge;

    private boolean holidayCharge;
    
    public ChargeTableEntry(ToolType toolType, 
    		BigDecimal dailyCharge, boolean weekdayCharge, 
    		boolean weekendCharge, boolean holidayCharge) {
        this.tooltype = toolType;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }
    
    public ToolType getToolType() {
    	return tooltype;
    }
    
    public BigDecimal getDailyCharge() {
    	return dailyCharge;
    }
    
    public boolean getWeekdayCharge() {
    	return weekdayCharge;
    }
    
    public boolean getWeekendCharge() {
    	return weekendCharge;
    }
    
    public boolean getHolidayCharge() {
    	return holidayCharge;
    }
    
}