package FloorStoreDao;

import FloorStoreDto.Orders;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class FloorStoreOrdersDaoImpl implements FloorStoreOrderDao {

    private final String ORDERS_FILE = "orders.txt";
    private final String DELIMITER = ",";

    //private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-DD-YYYY");
    private Map<String, Orders> ordersMap = new HashMap<>();


    //OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,
    // LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
    //sample data: 1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06
    @Override
    public Orders unmarshallOrder(String orderAsText) {
        String[] storedOrders = orderAsText.split(DELIMITER);
        String orderNumber = (storedOrders[0]);

        Orders ordersFromFile = new Orders(orderNumber);
        ordersFromFile.setCustomerName(storedOrders[1]);
        ordersFromFile.setState(storedOrders[2]);
        BigDecimal taxRate = new BigDecimal(storedOrders[3]);
        ordersFromFile.setProductType(storedOrders[4]);
        BigDecimal area = new BigDecimal(storedOrders[5]);
        BigDecimal costPerSqFt = new BigDecimal(storedOrders[6]);
        BigDecimal laborCostPerSqFt = new BigDecimal(storedOrders[7]);
        BigDecimal materialCost = new BigDecimal(storedOrders[8]);
        BigDecimal laborCost = new BigDecimal(storedOrders[9]);
        BigDecimal salesTax = new BigDecimal(storedOrders[10]);
        BigDecimal orderTotal = new BigDecimal(storedOrders[11]);
        LocalDate localDate = LocalDate.parse(String.format(storedOrders[12]));
        ordersFromFile.setTaxRate(taxRate);
        ordersFromFile.setArea(area);
        ordersFromFile.setCostPerSqFt(costPerSqFt);
        ordersFromFile.setLaborCostPerSqFt(laborCostPerSqFt);
        ordersFromFile.setMaterialCost(materialCost);
        ordersFromFile.setLaborCost(laborCost);
        ordersFromFile.setSalesTax(salesTax);
        ordersFromFile.setOrderTotal(orderTotal);
        ordersFromFile.setOrderDate(localDate);
        return ordersFromFile;
    }

    public void loadOrders() {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ORDERS_FILE)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String currentLine;
        Orders currentOrder;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            ordersMap.put(currentOrder.getOrderNum(), currentOrder);
            //System.out.println(currentLine);
        }
        scanner.close();

    }

    @Override
    //OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,
    // LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
    public String marshallItem(Orders orders) {
        String orderAsText = orders.getOrderNum() + DELIMITER +
                orders.getCustomerName() + DELIMITER +
                orders.getState() + DELIMITER +
                orders.getTaxRate() + DELIMITER +
                orders.getProductType() + DELIMITER +
                orders.getArea() + DELIMITER +
                orders.getCostPerSqFt() + DELIMITER +
                orders.getLaborCostPerSqFt() + DELIMITER +
                orders.getMaterialCost() + DELIMITER +
                orders.getLaborCost() + DELIMITER +
                orders.getSalesTax() + DELIMITER +
                orders.getOrderTotal() + DELIMITER +
                orders.getOrderDate() + DELIMITER;
        return orderAsText;
    }

    @Override
    public void writeItemsToFile() {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDERS_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String ordersAsText;
        List<Orders> ordersList = this.getAllOrders();
        for (Orders currentOrder : ordersList) {
            ordersAsText = marshallItem(currentOrder);
            out.println(ordersAsText);
            out.flush();
        }

        out.close();
    }

    @Override
    public List<Orders> getAllOrders() {
        loadOrders();
        return new ArrayList<Orders>(ordersMap.values());
    }

    @Override
    public Orders getOrder(String orderNum) {
        loadOrders();
        return ordersMap.get(orderNum);
    }

    @Override
    public Orders addOrder(String orderNum, Orders newOrder) {
        loadOrders();
        Orders prevOrder = ordersMap.put(orderNum, newOrder);
        writeItemsToFile();
        return prevOrder;
    }

    @Override
    public Orders removeOrder(String orderNum) {
        loadOrders();
        Orders removedOrder = ordersMap.remove(orderNum);
        writeItemsToFile();
        return removedOrder;
    }

    @Override
    public void  editOrder(String orderNum,  Orders orders) {
        try {
            writeItemsToFile();
        } catch (Exception e) {
            System.out.println("order not edited.");
        }
    }
//        loadOrders();
//        Orders currentOrder = ordersMap.get(orderNum);
//        currentOrder.setOrderDate(orderDate);
//        writeItemsToFile();
//        return currentOrder;


    @Override
    public Orders editCustomerName(String orderNum, String customerName) {
        loadOrders();
        Orders currentOrder = ordersMap.get(orderNum);
        currentOrder.setCustomerName(customerName);
        writeItemsToFile();
        return currentOrder;
    }

    @Override
    public Orders editProductType(String orderNum, String productType) {
        loadOrders();
        Orders currentOrder = ordersMap.get(orderNum);
        currentOrder.setProductType(productType);
        writeItemsToFile();
        return currentOrder;
    }

    @Override
    public Orders editCustomerState(String orderNum, String customerState) {
        loadOrders();
        Orders currentOrder = ordersMap.get(orderNum);
        currentOrder.setState(customerState);
        writeItemsToFile();
        return currentOrder;
    }

    @Override
    public Orders editArea(String orderNum, BigDecimal area) {
        loadOrders();
        Orders currentOrder = ordersMap.get(orderNum);
        currentOrder.setArea(area);
        writeItemsToFile();
        return currentOrder;
    }
}

