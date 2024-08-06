package main.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import main.model.ChargeTableEntry;
import main.model.Tool;
import main.model.ToolType;


/**
 * Repository class for tools
 * 
 * @author matthew
 *
 */
public class ToolRepository {
    
	private Map<String, Tool> toolSupply;
	private Map<ToolType, ChargeTableEntry> chargeTable;
	
	public ToolRepository() {
		toolSupply = new HashMap<>();
		chargeTable = new HashMap<>();
		
		toolSupply.put("CHNS", new Tool("CHNS", ToolType.Chainsaw, "Stihl"));
		toolSupply.put("LADW", new Tool("LADW", ToolType.Ladder, "Werner"));
		toolSupply.put("JAKD", new Tool("JAKD", ToolType.Jackhammer, "DeWalt"));
		toolSupply.put("JAKR", new Tool("JAKR", ToolType.Jackhammer, "Ridgid"));
		
		chargeTable.put(ToolType.Ladder, 
			new ChargeTableEntry(ToolType.Ladder, BigDecimal.valueOf(1.99), true, true, false));
		chargeTable.put(ToolType.Chainsaw, 
			new ChargeTableEntry(ToolType.Chainsaw, BigDecimal.valueOf(1.49), true, false, true));
		chargeTable.put(ToolType.Jackhammer, 
			new ChargeTableEntry(ToolType.Jackhammer, BigDecimal.valueOf(2.99), true, false, false));
	}
	
	public boolean isToolAvailable(String toolCode) {
		return toolSupply.containsKey(toolCode);
	}
	
	public Tool getTool(String toolCode) {
		return toolSupply.get(toolCode);
	}
	
	public ChargeTableEntry getChargeDetails(ToolType type) {
		return chargeTable.get(type);
	}
}