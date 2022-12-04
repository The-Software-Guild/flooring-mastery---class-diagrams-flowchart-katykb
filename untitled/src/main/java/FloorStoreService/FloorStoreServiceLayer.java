package FloorStoreService;

import FloorStoreDao.FloorStorePersistenceException;
import FloorStoreDto.Orders;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FloorStoreServiceLayer {

    List<Orders> getAllOrders();

    public void createOrder(String orderNum, Orders newOrder) throws OrderDuplicateException, IOException, OrderValidationException, FloorStorePersistenceException;

    Orders calculateCosts(Orders newOrder) throws Exception;

    Orders getOrder(String orderNum) throws FloorStorePersistenceException;

    Orders removeOrder( String orderNum) throws FloorStorePersistenceException;

    Orders editOrder(String orderNum);

    Orders editOrderDate(String orderNum, LocalDate orderDate) throws FloorStorePersistenceException;

    Orders editCustomerName(String orderNum, String customerName) throws FloorStorePersistenceException;

    Orders editProductType(String orderNum, String productType) throws FloorStorePersistenceException;

    Orders editCustomerState(String orderNum, String customerState) throws FloorStorePersistenceException;

    Orders editTaxRate(String orderNum, BigDecimal taxRate) throws FloorStorePersistenceException;
}
