/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author Jonbr
 */
public class Orders implements Serializable{
    private Medicine medicine;
    private int quantity;
    private static ArrayList<Orders> listOfOrders = new ArrayList<Orders>();
    
    private Orders(Medicine medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public void createOrders(){
        Orders order = new Orders(medicine, quantity);
        listOfOrders.add(order);
        write(order);

    }
    
    public void deliverOrder(String selectedMedicine, int quantity) {
        //Simulate a delivery, updates the stock using existing update method.
        for(Medicine m : Medicine.getListOfMedicine()) {
            if(m.getName() == selectedMedicine) {
            Stock.updateStock("add", m, quantity);
            }
        }
    }
    
    //Serialization
    public static void write(Orders order) {
        Serialiser.writeObject(order, "order_file.ser");
    }

    public static Serializable read(){
        Serializable order = null;
        try {
         FileInputStream fileRead = new FileInputStream("order_file.ser");
         ObjectInputStream in = new ObjectInputStream(fileRead);
         while(fileRead.available() > 0) {
            order = (Serializable) in.readObject();
            listOfOrders.add((Orders) order);

        }
         in.close();
         fileRead.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return order;
    } 
    
    /*Setters*/
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static void setListOfOrders(ArrayList<Orders> listOfOrders) {
        Orders.listOfOrders = listOfOrders;
    }

    /*getters*/
    public Medicine getMedicine() {
        return medicine;
    }
    public int getQuantity() {
        return quantity;
    }
    public static ArrayList<Orders> getListOfOrders() {
        return listOfOrders;
    }   
    
    
}
