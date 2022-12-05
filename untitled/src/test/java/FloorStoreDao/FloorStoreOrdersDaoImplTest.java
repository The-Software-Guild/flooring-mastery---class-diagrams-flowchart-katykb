package FloorStoreDao;

import FloorStoreDto.Orders;
import FloorStoreDto.Products;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.ObjectRowListProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FloorStoreOrdersDaoImplTest {

    FloorStoreOrderDao orderDao;
    FloorStoreProductDao productDao;
    FloorStoreTaxDao taxDao;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Orders> orders = orderDao.getAllOrders();
        for(Orders orders1 : orders){
            orderDao.removeOrder(orders1.getOrderNum());
        }
    }

    @AfterEach
    public void tearDown() {
    }


    @org.junit.jupiter.api.Test
    void getAllOrders() {
        String orderNum = "";
        Orders orders = new Orders(orderNum);
        orders.setOrderDate(LocalDate.parse("2023-04-04"));
        orders.setCustomerName("Aaron Brown");
        orders.setState("TX");
        orders.setTaxRate(BigDecimal.valueOf(4.25));
        orders.setProductType("Wood");
        orders.setArea(BigDecimal.valueOf(88.9));

        orders.setCostPerSqFt(BigDecimal.valueOf(3.00));
        orders.setLaborCostPerSqFt(BigDecimal.valueOf(2.75));

       orders.setMaterialCost();
        private BigDecimal laborCost;
        private BigDecimal salesTax;
        private BigDecimal orderTotal;

        private LocalDate orderDate;


    }

    @org.junit.jupiter.api.Test
    void addOrder() {
    }

    @org.junit.jupiter.api.Test
    void removeOrder() {
    }

    @org.junit.jupiter.api.Test
    void editCustomerName() {
    }
}