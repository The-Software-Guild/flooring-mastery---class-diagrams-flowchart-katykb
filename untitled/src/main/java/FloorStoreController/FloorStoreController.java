package FloorStoreController;

import FloorStoreDao.FloorStorePersistenceException;
import FloorStoreDto.Orders;
import FloorStoreService.FloorStoreServiceLayer;
import FloorStoreService.OrderDuplicateException;
import FloorStoreService.OrderValidationException;
import FloorStoreUserIo.FloorStoreView;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FloorStoreController {

    private final FloorStoreView myView;
    private final FloorStoreServiceLayer myService;

    public FloorStoreController(FloorStoreView myView, FloorStoreServiceLayer myService) {
        this.myView = myView;
        this.myService = myService;
    }

    public void run() throws Exception {
//        myView.printMainMenu();
//        myView.getMainMenuSelection();
        boolean keepGoing = true;
        while (keepGoing) {
            String menuSelection = myView.printMainMenu();
            switch (menuSelection) {
                case "1":
                    listAllOrders();
                    break;

                case "2":
                    addOrder();
                    break;

                case "3":
                    editOrder();
                    break;

                case "4":
                    removeOrder();
                    break;

                case "5":
                    exitMessage();
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();

    }

    private void listAllOrders() {
        List<Orders> ordersList = myService.getAllOrders();
        myView.displayAllOrdersBanner();
        myView.displayOrderList(ordersList);
    }

    private void addOrder() throws Exception {
        myView.displayCreateNewOrderBanner();
        Orders newOrder = myView.collectNewOrderInfo();
        Orders getCostInfo = myService.calculateCosts(newOrder);
        myView.displayOrderDetails(getCostInfo);
        myService.createOrder(newOrder.getOrderNum(), getCostInfo);
        myView.displayOrderSuccessfullyCreatedBanner();
    }

    private void editOrder() throws FloorStorePersistenceException {
        myView.displayEditOrderBanner();
        String orderNum = myView.getOrderNumberByChoice();
        Orders currentOrder = (Orders) myService.getOrder(orderNum);
        if (currentOrder == null) {
            myView.displayNullOrder();
        } else {
            myView.displayOrder(currentOrder);
            String editMenuSelection = "";
            boolean keepGoing = true;
            while (keepGoing) {
                editMenuSelection = myView.printEditMenuAndGetSelection();
                switch (editMenuSelection) {
//
                    case "1":
                        editCustomerName(orderNum, currentOrder);
                        break;
                    case "2":
                        editProductType(orderNum, currentOrder);
                        break;
                    case "3":
                        editCustomerState(orderNum, currentOrder);
                        break;
                    case "4":
                        editArea(orderNum, currentOrder);
                        break;
                    case "5":
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false) {
                    break;
                }
            }
        }

    }

    private void editCustomerState(String orderNum, Orders orders) throws FloorStorePersistenceException {
        myView.displayEditCustomersStateBanner();
        String newState = myView.getNewState(orders);
        myService.editCustomerState(orderNum, newState);
        myView.displayEditOrderSuccessBanner();

    }

    private void editProductType(String orderNum, Orders orders) throws FloorStorePersistenceException {
        myView.displayEditProductTypeBanner();
        String newProduct = myView.getNewProductType(orders);
        myService.editProductType(orderNum, newProduct);
        myView.displayEditOrderSuccessBanner();

    }

    private void editCustomerName(String orderNum, Orders orders) throws FloorStorePersistenceException {
        myView.displayEditCustomersNameBanner();
        String newCustomerName = myView.getCustomerName(orders);
        myService.editCustomerName(orderNum, newCustomerName);
        myView.displayEditOrderSuccessBanner();

    }

    private void editArea(String orderNum, Orders orders) throws FloorStorePersistenceException{
        myView.displayEditAreaBanner();
        BigDecimal area = myView.editArea(orders);
        myService.editArea(orderNum, area);
        myView.displayEditOrderSuccessBanner();
    }



    private void removeOrder() throws FloorStorePersistenceException {
        myView.displayRemoveOrderBanner();
        String orderNum = myView.getOrderNumberByChoice();
        myService.removeOrder(orderNum);
        myView.displayRemoveSuccessBanner();
        //myView.displayRemovedOrder(orderNum);

    }

    private void unknownCommand() {

        myView.displayUnknownCommand();
    }

    private void exitMessage() {
        myView.displayExitBanner();
    }
}
