package FloorStoreDao;

import FloorStoreDto.Orders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FloorStoreOrderDao {

    public Orders unmarshallOrder(String orderAsText);

    public void loadOrders();

    String marshallItem(Orders orders);

    public void writeItemsToFile();

    List<Orders> getAllOrders();

    Orders getOrder(String orderNum);

    Orders addOrder(String orderNum, Orders newOrder);

    Orders removeOrder(String orderNum);

    void editOrder(String orderNum, Orders orders) throws FloorStorePersistenceException;

    Orders editCustomerName(String orderNum, String customerName);

    Orders editProductType(String orderNum, String productType);

    Orders editCustomerState(String orderNum, String customerState);

    Orders editArea(String orderNum, BigDecimal taxRate);
}
