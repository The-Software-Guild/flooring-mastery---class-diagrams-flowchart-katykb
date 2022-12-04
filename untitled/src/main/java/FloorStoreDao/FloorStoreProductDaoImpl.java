package FloorStoreDao;

import FloorStoreDto.Products;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class FloorStoreProductDaoImpl implements FloorStoreProductDao {

    private final String PRODUCTS_FILE = "products.txt";
    private final String DELIMITER = ",";

    private Map<String, Products> productsMap = new HashMap<>();

    @Override
    //Wood,5.15,4.75
    public Products unmarshallProducts(String productsAsText) {
        String[] storedProducts = productsAsText.split(DELIMITER);
        Products productsFromFile = new Products();
        productsFromFile.setpProductType(storedProducts[0]);
        BigDecimal costPerSqFt = new BigDecimal(storedProducts[1]);
        productsFromFile.setPfCostPerSquareFoot(costPerSqFt);
        BigDecimal laborPerSqFt = new BigDecimal(storedProducts[2]);
        productsFromFile.setPfLaborCostPerSquareFoot(laborPerSqFt);
        return productsFromFile;
    }

    @Override
    public List<Products> loadProducts() {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String currentLine;
        Products currentProduct;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProducts(currentLine);
            productsMap.put(currentProduct.getPfProductType(), currentProduct);

        }
        scanner.close();
        //empty list of products to store stuff that will be returned
        //loop through the products map and get product
        //put in the empty list
        //return list with products we need
        List<Products> productsList = new ArrayList<>();
        for (Products product : productsMap.values()) {
            productsList.add(product);
        }
        return productsList;
    }
}

