package main.model;

public class ChargeTableEntry {

    private ToolType tooltype;

    private double dailyCharge;

    private boolean weekdayCharge;

    private boolean weekendCharge;

    private boolean holidayCharge;
    
    public ChargeTableEntry(ToolType toolType, 
    		double dailyCharge, boolean weekdayCharge, 
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
    
    public double getDailyCharge() {
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