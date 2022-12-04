package FloorStoreDao;

import FloorStoreDto.Products;

import java.util.List;

public interface FloorStoreProductDao {

    public Products unmarshallProducts(String productsAsText);

    public List<Products> loadProducts();
}
