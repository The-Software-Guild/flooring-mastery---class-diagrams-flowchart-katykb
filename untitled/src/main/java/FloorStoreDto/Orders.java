package FloorStoreDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Orders {

    private String orderNum;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;

    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;

    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal salesTax;
    private BigDecimal orderTotal;

    private LocalDate orderDate;

    public Orders(String orderNum) {
        this.orderNum = orderNum;
    }


    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return  getOrderNum().equals(orders.getOrderNum()) &&
                getCustomerName().equals(orders.getCustomerName()) &&
                getState().equals(orders.getState()) &&
                getTaxRate().equals(orders.getTaxRate()) &&
                getProductType().equals(orders.getProductType()) &&
                getArea().equals(orders.getArea()) &&
                getCostPerSqFt().equals(orders.getCostPerSqFt()) &&
                getLaborCostPerSqFt().equals(orders.getLaborCostPerSqFt()) &&
                getMaterialCost().equals(orders.getMaterialCost()) &&
                getLaborCost().equals(orders.getLaborCost()) &&
                getSalesTax().equals(orders.getSalesTax()) &&
                getOrderTotal().equals(orders.getOrderTotal()) &&
                getOrderDate().equals(orders.getOrderDate());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.orderDate);
        hash = 89 * hash + Objects.hashCode(this.customerName);
        hash = 89 * hash + Objects.hashCode(this.orderNum);
        hash = 89 * hash + Objects.hashCode(this.state);
        hash = 89 * hash + Objects.hashCode(this.taxRate);
        hash = 89 * hash + Objects.hashCode(this.area);
        hash = 89 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 89 * hash + Objects.hashCode(this.productType);
        hash = 89 * hash + Objects.hashCode(this.laborCostPerSqFt);
        hash = 89 * hash + Objects.hashCode(this.laborCost);
        hash = 89 * hash + Objects.hashCode(this.materialCost);
        hash = 89 * hash + Objects.hashCode(this.salesTax);
        hash = 89 * hash + Objects.hashCode(this.orderTotal);
        return hash;
    }
}