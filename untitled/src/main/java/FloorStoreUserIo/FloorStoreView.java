package FloorStoreUserIo;

import FloorStoreDto.Orders;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FloorStoreView {
    private FloorStoreUserIo io;

    public FloorStoreView(FloorStoreUserIo io) {
        this.io = io;
    }

    public String printMainMenu() {

        io.print("Main Menu");
        io.print("1- View All Orders");
        io.print("2- Add and Order");
        io.print("3- Edit an Order");
        io.print("4- Remove an Order");
        io.print("5- Exit");
        return io.readString("Please select from the menu");
    }

    //goes with main menu option 1: view all orders
    public void displayAllOrdersBanner() {
        io.print("======= All Orders =======");
    }

    public void displayOrderBanner() {
        io.print("======= Order =======");
    }

    public ArrayList<String> printAllOrders(List<Orders> ordersList) {
        int j = 1;
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "null");
        for (Orders o : ordersList) {
            io.print(j + "." + o.toString());
            list.add(j, o.getOrderNum());
            j++;
        }
        return list;
    }

    //goes with main menu option 2: add an order
    public Orders collectNewOrderInfo() {
        String orderNum = io.readString("Please enter an order number.");
        LocalDate orderDate = LocalDate.parse(io.readString("Please enter the date in DD-MM-YYYY format."));
        String customerName = io.readString("Please enter customer's first and last name.");
        String customerState = io.readString("Please enter the customer's state.");
        String productType = io.readString("What product type would the customer like?");
        BigDecimal areaOfOrder = new BigDecimal(io.readString("What is the area of the project?"));
        Orders collectedInfo = new Orders(orderNum);
        collectedInfo.setCustomerName(customerName);
        collectedInfo.setState(customerState);
        collectedInfo.setProductType(productType);
        collectedInfo.setArea(areaOfOrder);
        collectedInfo.setOrderDate(orderDate);
        System.out.println(orderNum + customerName + productType + customerState);
        return collectedInfo;
    }

    public void displayOrderDetails(Orders order){
        System.out.println("Order Number: " + order.getOrderNum());
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Customer State: " + order.getState());
        System.out.println("Product Choice: " + order.getProductType());
        System.out.println("State Tax Rate: " + order.getTaxRate());
        System.out.println("Area of Project " + order.getArea());
        System.out.println("Material Cost Per Square Foot: " + order.getCostPerSqFt());
        System.out.println("Labor Cost Per Square foot: " + order.getLaborCostPerSqFt());
        System.out.println("TOTAL Labor Cost of Project: " + order.getLaborCost());
        System.out.println("TOTAL Material Cost of Project: " + order.getMaterialCost());
        System.out.println("Sales Tax TOTAL: " + order.getSalesTax());
        System.out.println("TOTAL Cost of  Project: " + order.getOrderTotal());
    }


    public void displayErrorMessage(String message) throws IOException {
        io.print("=== ERROR ===");
        io.print(message);

    }

    //OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
    //3,Albert Einstein,KY,6.00,Carpet,217.00,2.25,2.10,488.25,455.70,56.64,1000.59


    public void displayUnknownCommand() {
        io.print("Invalid input. Please input 1-5" + "\n");
    }


    public void displayCreateNewOrderBanner() {
        io.print("======= Create a New Order =======");
    }

    public void displayOrderSuccessfullyCreatedBanner() {
        io.print("======= New Order Successfully Created =======");
    }

    public void displayOrderList(List<Orders> ordersList) {
        for (Orders currentOrder : ordersList) {
            String orderInfo = String.format("#%s : %s %s %s %s %s %s %s %s %s %s %s",
                    currentOrder.getOrderNum(),
                    currentOrder.getCustomerName(),
                    currentOrder.getState(),
                    currentOrder.getTaxRate(),
                    currentOrder.getProductType(),
                    currentOrder.getArea(),
                    currentOrder.getLaborCostPerSqFt(),
                    currentOrder.getMaterialCost(),
                    currentOrder.getLaborCost(),
                    currentOrder.getSalesTax(),
                    currentOrder.getOrderTotal(),
                    currentOrder.getOrderDate());
            io.print(orderInfo);
        }
        io.readString("Please hit enter to continue.");

    }

    public void displayRemoveOrderBanner() {
        io.print("======= Remove Order =======");
    }

    public void displayRemoveSuccessBanner() {
        io.print("======= Order has been removed =======");
    }

    public String getOrderNumberByChoice() {
        return io.readString("Please enter the order number.");
    }

    public void displayOrder(Orders orders){
        if(orders != null){
            io.print(orders.getOrderNum());
            io.print(orders.getCustomerName());
            io.print(orders.getState());
            io.print(orders.getTaxRate());
            io.print(orders.getProductType());
            io.print(orders.getArea());
            io.print(orders.getCostPerSqFt());
            io.print(orders.getLaborCostPerSqFt());
            io.print(orders.getMaterialCost());
            io.print(orders.getLaborCost());
            io.print(orders.getSalesTax());
            io.print(orders.getOrderTotal());
            io.print(orders.getOrderDate());
            io.print("");
        }else {
            io.print("No such order");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemovedOrder(String orderRecord) {
        if (orderRecord != null) {
            io.print("Order successfully removed.");
        } else {
            io.print("No order exists.");
        }
        io.readString("Please hit enter to continue");
    }

    public String printEditMenuAndGetSelection() {
        io.print("1. Edi Order Date.");
        io.print("2. Edit Customer Name.");
        io.print("3. Edit Product Type");
        io.print("4. Edit Customer's State");
        io.print("5. Edit state tax Rate");
        io.print("6. Return to Main Menu");

        return io.readString("Please select from the above choices.");

    }

    public LocalDate getNewDate() {
        return io.readLocalDate("Please enter the new order date.");
    }

    public String getNewCustomerName() {
        return io.readString("Please enter new customer name.");
    }

    public String getNewProductType() {
        return io.readString("Please enter new product type.");
    }

    public String getNewState() {
        return io.readString("Please enter new customer state.");
    }

    public BigDecimal getNewTaxRate() {
        return io.readBigDecimal("Please enter new tax rate.");
    }

    public void displayEditOrderBanner(){io.print("======= Edit Order =======");}

    public void displayEditOrderSuccessBanner(){io.print("======= Order successfully edited. Please hit enter to continue.");}

    public void displayEditOrderDateBanner() {
        io.print("======= Edit Order Date =======");
    }

    public void displayEditProductTypeBanner() {
        io.print("======= Edit Product Type =======");
    }

    public void displayEditCustomersNameBanner() {
        io.print("======= Edit Customer's Name =======");
    }

    public void displayEditCustomersStateBanner() {
        io.print("======= Edit Customer's State =======");
    }

    public void displayEditTaxRateBanner() {
        io.print("======= Edit State Tax Rate =======");
    }

    public void displayNullOrder() {
        io.print("No such order.");
        io.readString("Please hit enter to continue.");
    }
    public void displayExitBanner(){io.print("======= Good Bye! =======");}
}
