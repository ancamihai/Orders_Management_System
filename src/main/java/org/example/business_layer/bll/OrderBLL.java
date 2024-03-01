package org.example.business_layer.bll;

import org.example.business_layer.table_generators.OrderTableGenerator;
import org.example.data_access.OrderDAO;
import org.example.model.Bill;
import org.example.model.Order;
import org.example.model.Product;

import java.sql.Date;
import java.util.List;

public class OrderBLL {

    private Order order;

    private OrderDAO orderDAO;

    private BillBLL billBLL;

    private OrderTableGenerator orderTableGenerator;

    private ProductBLL productBLL;

    public OrderBLL() {
        this.orderDAO = new OrderDAO();
        this.billBLL = new BillBLL();
        this.productBLL=new ProductBLL();
        this.orderTableGenerator = new OrderTableGenerator();
    }

    public void newOrder() {
        this.order = new Order();
    }

    /**
     * sets the client of the order
     * @param name the name of the client of the order
     */
    public void confirmClient(String name) {
        this.order.setClientName(name);
    }

    /**
     * generates the bill of the newly-confirmed order
     * @return the content of it, which will be displayed on the screen
     */
    private String generateBill() {
        List<Order> allOrders = orderDAO.findAll();
        Order order = allOrders.get(allOrders.size() - 1);
        Bill bill = new Bill(order.getId(), order.getClientName(), order.getProductName(), order.getQuantity(), order.getDate(), order.getTotalPrice());
        billBLL.insert(bill);
        return bill.toString();
    }


    /**
     * sets the product to be ordered and checks if the quantity required by the client is available; if not, the client will be informed of the under-stock
     * @param productName the name of the product selected
     * @param quantity the quantity ordered of the selected product
     * @return the content of the bill generated when a new order is confirmed
     */
    public String confirmOrder(String productName, int quantity, ProductBLL productBLL) throws Exception {
        java.util.Date dateUtil = new java.util.Date();
        Date date = new Date(dateUtil.getTime());
        this.order.setDate(date);
        this.order.setAvailable("Yes");
        if (quantity <= 0) {
            throw new Exception("Invalid quantity!");
        } else {
            Product product = productBLL.findProductByName(productName);
            if (quantity > product.getStock()) {
                throw new Exception("Under stock!");
            } else {
                product.setStock(product.getStock() - quantity);
                productBLL.updateProduct(product);
                order.setQuantity(quantity);
                order.setTotalPrice(quantity * product.getPrice());
                order.setProductName(productName);
                orderDAO.insert(order);
                return generateBill();
            }
        }
    }

    /**
     *
     * @return the header of the JTable generated when "View orders" button is pressed
     */
    public Object[] columnNames() {
        List allOrders = orderDAO.findAll();
        return orderTableGenerator.generateColumnNames(allOrders);
    }

    /**
     *
     * @return the data to populate the JTable generated when "View orders" button is pressed
     */
    public Object[][] rowData() {
        List allOrders = orderDAO.findAll();
        return orderTableGenerator.generateRowData(allOrders);
    }

    /**
     * cancels an available order by setting its available attribute from "Yes" to "No" and by putting back in the stock the products which were ordered
     * @param id the id of the order to be cancelled
     * @return 1 if the order requested is already cancelled, else 0
     */
    public int cancelOrder(int id) {
        Order order = orderDAO.findById(id);
        if (order.getAvailable().equals("No")) {
            return 1;
        } else {
            order.setAvailable("No");
        }
        Product product=productBLL.findProductByName(order.getProductName());
        product.setStock(product.getStock()+order.getQuantity());
        orderDAO.update(order);
        productBLL.updateProduct(product);
        return 0;
    }

}
