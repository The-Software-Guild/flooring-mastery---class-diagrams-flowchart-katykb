package FloorStoreDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Products {

    private String pProductType;
    private BigDecimal pCostPerSquareFoot;
    private BigDecimal pLaborCostPerSquareFoot;


    public void setpProductType(String pProductType) {
        this.pProductType = pProductType;
    }

    public String getPfProductType() {
        return pProductType;
    }

    public BigDecimal getPfCostPerSquareFoot() {
        return pCostPerSquareFoot;
    }

    public void setPfCostPerSquareFoot(BigDecimal pfCostPerSquareFoot) {
        this.pCostPerSquareFoot = pfCostPerSquareFoot;
    }

    public BigDecimal getPfLaborCostPerSquareFoot() {
        return pLaborCostPerSquareFoot;
    }

    public void setPfLaborCostPerSquareFoot(BigDecimal pfLaborCostPerSquareFoot) {
        this.pLaborCostPerSquareFoot = pfLaborCostPerSquareFoot;
    }
}
