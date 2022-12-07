package FloorStoreService;

import FloorStoreDao.*;
import FloorStoreDto.Orders;
import FloorStoreDto.Products;
import FloorStoreDto.StateTax;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class FloorStoreServiceLayerImpl implements FloorStoreServiceLayer {

    private FloorStoreAuditDao myAdao;
    private FloorStoreOrderDao myDao;

    private FloorStoreTaxDao myTdao;
    private FloorStoreProductDao myPdao;

    public FloorStoreServiceLayerImpl(FloorStoreAuditDao myAdao, FloorStoreOrderDao myDao, FloorStoreProductDao myPdao, FloorStoreTaxDao myTdao) {
        this.myAdao = myAdao;
        this.myDao = myDao;
        this.myTdao = myTdao;
        this.myPdao = myPdao;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = myDao.getAllOrders();
        return ordersList;
    }

    @Override
    public Orders getOrder(String orderNum) {
        return myDao.getOrder(orderNum);
    }

    @Override
    public void createOrder(String orderNum, Orders newOrder) throws IOException, OrderValidationException, FloorStorePersistenceException {
        validateOrderData(newOrder);
        myDao.addOrder(orderNum, newOrder);
        myAdao.writeAuditEntry("Order " + newOrder.getOrderNum() + " was Created.");
    }

    @Override
    public Orders calculateCosts(Orders newOrder) throws Exception {
        BigDecimal taxRate = new BigDecimal(0);
        BigDecimal area = newOrder.getArea();
        List<StateTax> taxRatesList = myTdao.loadStateTax();
        List<Products> productsList = myPdao.loadProducts();
        if (area.compareTo(BigDecimal.ZERO) >= 0) {

            for (StateTax tax : taxRatesList) {
                if (tax.getStateAbbreviation().equals(newOrder.getState())) {
                    taxRate = tax.getTrTaxRate();
                }
            }
            for (Products products : productsList) {
                if (products.getPfProductType().equalsIgnoreCase(newOrder.getProductType())) {
                    BigDecimal costSqFt = (products.getPfCostPerSquareFoot());
                    BigDecimal laborCostSqFt = (products.getPfLaborCostPerSquareFoot());
                    BigDecimal materialTotal = area.multiply(costSqFt);
                    BigDecimal materialCostTotal = materialTotal.setScale(2, RoundingMode.HALF_UP);
                    BigDecimal laborCostCalc = area.multiply(laborCostSqFt);
                    BigDecimal laborCostTotal = laborCostCalc.setScale(2, RoundingMode.HALF_UP);
                    BigDecimal orderSubTotal = materialCostTotal.add(laborCostTotal);
                    BigDecimal salesTaxCalc = orderSubTotal.multiply(taxRate.divide(BigDecimal.valueOf(100)));
                    BigDecimal salesTaxTotal = salesTaxCalc.setScale(2, RoundingMode.HALF_UP);
                    BigDecimal orderTotalCost = orderSubTotal.add(salesTaxTotal);

                    newOrder.setTaxRate(taxRate);
                    newOrder.setCostPerSqFt(costSqFt);
                    newOrder.setLaborCostPerSqFt(laborCostSqFt);
                    newOrder.setLaborCost(laborCostTotal);
                    newOrder.setMaterialCost(materialCostTotal);
                    newOrder.setSalesTax(salesTaxTotal);
                    newOrder.setOrderTotal(orderTotalCost);
                }
            }
        } else {
            throw new Exception("must enter area to continue");
        }

        return newOrder;
    }


    @Override
    public Orders removeOrder(String orderNum) throws FloorStorePersistenceException {
        Orders removedOrder = myDao.removeOrder(orderNum);
        myAdao.writeAuditEntry("Order: " + orderNum + "has been removed.");
        return removedOrder;
    }

    @Override
    public Orders editOrder(String orderNum, Orders orders) throws FloorStorePersistenceException {
        if (orders.getOrderNum().equals("")) {
            throw new FloorStorePersistenceException("Please enter order Number.");
        } else {
            myDao.editOrder(orderNum, orders);
        }
        return orders;
    }

    @Override
    public Orders editCustomerName(String orderNum, String customerName) throws FloorStorePersistenceException {
        Orders newOrders = myDao.editCustomerName(orderNum, customerName);
        myAdao.writeAuditEntry(orderNum + ": " + "customer name has been changed to:  " + customerName);
        return newOrders;
    }

    @Override
    public Orders editProductType(String orderNum, String productType) throws FloorStorePersistenceException {
        Orders newOrders = myDao.editProductType(orderNum, productType);
        myAdao.writeAuditEntry(orderNum + ": product type has been changed to:  " + productType);
        return newOrders;
    }

    @Override
    public Orders editCustomerState(String orderNum, String customerState) throws FloorStorePersistenceException {
        Orders newOrder = myDao.editCustomerState(orderNum, customerState);
        myAdao.writeAuditEntry(orderNum + ": customer state has been changed to: " + customerState);
        return newOrder;
    }

    @Override
    public Orders editArea(String orderNum, BigDecimal area) throws FloorStorePersistenceException {
        Orders newOrder = myDao.editArea(orderNum, area);
        myAdao.writeAuditEntry(orderNum + ": area has been changed to: " + area);
        return newOrder;
    }

    private void validateOrderData(Orders orders) throws OrderValidationException {
        if ((orders.getOrderDate() == null ||
                orders.getOrderDate() == null ||
                orders.getOrderNum().trim() == null ||
                orders.getCustomerName().trim() == null ||
                orders.getProductType().trim() == null ||
                orders.getArea() == null)) {
            throw new OrderValidationException("ERROR: All fields [order date, order number, customer name and product type and area] are required.");
        }
    }
}
