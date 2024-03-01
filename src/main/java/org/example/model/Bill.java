package org.example.model;

import java.sql.Date;

public record Bill(int orderId, String clientName, String productName, int quantity, Date date, int totalPrice) {
    /**
     * @return the content of the Bill in String-form
     */
    @Override
    public String toString() {
        return "A new order was finalized!\nBill:"+"\nOrder id: "+orderId+"\nClient name: "+clientName+"\nProduct name: "+productName+"\nQuantity: "+quantity+"\nDate: "+date.toString()+"\nTotal price: "+totalPrice;
    }
}
