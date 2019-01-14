/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientSystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jonbr
 */
public class Stock implements Serializable{
    private Medicine medicine;
    private int quantityInStock;
    private static ArrayList<Stock> listOfStock = new ArrayList<Stock>();
    private static int newQuantity;
    
    private Stock(Medicine medicine, int quantityInStock) {
        this.medicine = medicine;
        this.quantityInStock = quantityInStock;
    }

    public void createStock(Medicine medicine, int quantityInStock){
        Stock stock = new Stock(medicine, quantityInStock);
        listOfStock.add(stock);
    }
    
    public static void checkStock() {
        for(Stock s : listOfStock)
        {
            if (s.getQuantityInStock() <= 5)
            {
                String type = s.getMedicine().toString();
                String notification = type + " is running low on stock";
                Notifications notifications = new Notifications(notification);
                for(Secretary i : UsersSingleton.getInstance().getListOfSecretarys()) {
                    i.getNotifications().add(notifications);
                }
            }
        }
    }
    
    public static void updateStock(String type, Medicine medicine, int quantity) {
        //Updates the stock whether it's a collection or delivery, do switch statement for type.
        switch(type.toLowerCase()){
            case"add":
                //add medicine for all in listOfStock, if getMedicine = medicine update.

                for(Stock s : listOfStock) {
                    if(s.getMedicine() == medicine) {
                        newQuantity = s.getQuantityInStock() + quantity;
                        s.setQuantityInStock(newQuantity);
                    }
                }
                checkStock();
                break;
            case"remove":
                for(Stock s : listOfStock) {
                    if(s.getMedicine() == medicine) {
                        newQuantity = s.getQuantityInStock() - quantity;
                        s.setQuantityInStock(newQuantity);
                    }
                }
                checkStock();
                break;
        }
    }
       
    /*Setters*/
    public void setQuantityInStock(int quantityInStock) {
    this.quantityInStock = quantityInStock;
    }
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    public static void setListOfStock(ArrayList<Stock> listOfStock) {
        Stock.listOfStock = listOfStock;
    }
    
    
    
    /*Getters*/
    public Medicine getMedicine() {
        return medicine;
    }
    public int getQuantityInStock() {
        return quantityInStock;
    }
    public static ArrayList<Stock> getListOfStock() {
        return listOfStock;
    }
    
}