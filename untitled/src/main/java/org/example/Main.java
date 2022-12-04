package org.example;


import FloorStoreController.FloorStoreController;
import FloorStoreDao.*;
import FloorStoreService.FloorStoreServiceLayer;
import FloorStoreService.FloorStoreServiceLayerImpl;
import FloorStoreUserIo.FloorStoreUserIo;
import FloorStoreUserIo.FloorStoreUserIoImpl;
import FloorStoreUserIo.FloorStoreView;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws Exception {
        FloorStoreUserIo myIo = new FloorStoreUserIoImpl();
        // Instantiate the View and wire the UserIO implementation into it
        FloorStoreView myView = new FloorStoreView(myIo);
        // Instantiate the DAO
        FloorStoreOrderDao myDao = new FloorStoreOrdersDaoImpl();
        // Instantiate the Audit DAO
       FloorStoreAuditDao myAuditDao = new FloorStoreAuditDaoImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        FloorStoreProductDao myPdao = new FloorStoreProductDaoImpl();
        FloorStoreTaxDao myTdao = new FloorStoreTaxDaoImpl();
        FloorStoreServiceLayer myService = new FloorStoreServiceLayerImpl(myAuditDao, myDao, myPdao, myTdao);
        // Instantiate the Controller and wire the Service Layer into it
        FloorStoreController controller = new FloorStoreController(myView, myService);
        // Kick off the Controller
        controller.run();
    }


}