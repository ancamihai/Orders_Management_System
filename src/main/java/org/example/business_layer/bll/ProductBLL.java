package org.example.business_layer.bll;

import org.example.business_layer.table_generators.ProductTableGenerator;
import org.example.business_layer.validators.PriceValidator;
import org.example.business_layer.validators.StockValidator;
import org.example.data_access.ProductDAO;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {

    private ProductDAO productDAO;

    private ProductTableGenerator productTableGenerator;

    public ProductBLL() {
        this.productDAO = new ProductDAO();
        this.productTableGenerator = new ProductTableGenerator();
    }


    /**
     * checks if the newly-inserted product has an appropriate stock and price, and inserts it in the Product table of the database
     *
     * @param product the product to be inserted
     * @return 1 in case the product was successfully inserted, else 0
     */
    public int insertProduct(Product product) {
        StockValidator.validate(product);
        PriceValidator.validate(product);
        return productDAO.insert(product);
    }

    /**
     * checks if the product whose data was updated has an appropriate stock and price, and updates it in the Product table of the database
     *
     * @param product the product to be updated, identified by its id
     * @return in case the product was successfully updated, else 0
     */
    public int updateProduct(Product product) {
        StockValidator.validate(product);
        PriceValidator.validate(product);
        return productDAO.update(product);
    }

    /**
     * deletes a product from the Product table of the database
     *
     * @param product the product to be deleted, identified by its name
     * @return 1 in case the product was successfully deleted, else 0
     */
    public int deleteProduct(Product product) {
        return productDAO.delete(product);
    }

    /**
     * @return the header of the JTable generated when "View products" button is pressed
     */
    public Object[] columnNames() {
        List allProducts = productDAO.findAll();
        return productTableGenerator.generateColumnNames(allProducts);
    }

    /**
     * @return the data to populate the JTable generated when "View products" button is pressed
     */
    public Object[][] rowData() {
        List allProducts = productDAO.findAll();
        return productTableGenerator.generateRowData(allProducts);
    }

    /**
     * @return the names of the currently existing products in the Product table of the database
     */
    public Object[] productNames() {
        List<Product> allProducts = productDAO.findAll();
        ArrayList productNames = new ArrayList<>();
        for (int i = 0; i < allProducts.size(); i++) {
            productNames.add(allProducts.get(i).getName());
        }
        return productNames.toArray();
    }


    /**
     * finds a product based on the name selected
     * @param productName the name of the product to be found in the Product table of the database
     * @return the product found using the name selected
     */
    public Product findProductByName(String productName) {
        return productDAO.selectProductByName(productName);
    }

}
