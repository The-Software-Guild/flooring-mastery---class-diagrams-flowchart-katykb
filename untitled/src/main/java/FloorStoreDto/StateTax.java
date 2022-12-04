package FloorStoreDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class StateTax {

    private String stateAbbreviation;
    private String trStateName;
    private BigDecimal trTaxRate;

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public String getTrStateName() {
        return trStateName;
    }

    public void setTrStateName(String trStateName) {
        this.trStateName = trStateName;
    }

    public BigDecimal getTrTaxRate() {
        return trTaxRate;
    }

    public void setTrTaxRate(BigDecimal trTaxRate) {
        this.trTaxRate = trTaxRate;
    }
}
